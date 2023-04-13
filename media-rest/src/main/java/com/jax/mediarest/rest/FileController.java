package com.jax.mediarest.rest;

import com.jax.mediarest.dvo.FileDVO;
import com.jax.mediarest.dvo.ResponseMessage;
import com.jax.mediarest.service.IFileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FileController {

    private final IFileService service;

    public FileController(IFileService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files")MultipartFile[] files) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file ->{
                service.save(file);
                fileNames.add(file.getOriginalFilename());
            });
            message = "Upload the files successfully: "+ fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileDVO>> getListFiles(){
        List<FileDVO> fileDVOS = service.loadAll()
                .map(path -> {
                    String fileName = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(this.getClass(), "getFile", path.getFileName().toString())
                            .build()
                            .toString();
                    return new FileDVO(fileName, url);
                }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileDVOS);
    }

    @GetMapping("files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = service.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFilename() + "\"")
                .body(file);
    }
}
