//package com.jax.mediarest.exception;
//
//import com.jax.mediarest.dvo.ResponseMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class FileUploadException extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<ResponseMessage> handMAxSizeException(MaxUploadSizeExceededException exception) {
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
//                .body(new ResponseMessage("One or more files are too large!"));
//    }
//}
