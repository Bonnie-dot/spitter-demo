package com.example.demo.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//add notify for all controller
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(SpittleNotFoundException.class)
    public String duplicationSpittleException(Model model){
        model.addAttribute("type","SpittleNotFoundException");
        return "error";
    }

    //can handle the same controller throwing error
    @ExceptionHandler(StorageFileNotFoundException.class)
    public String handleStorageFileNotFound(Model model) {
        model.addAttribute("type","StorageFileNotFoundException");
        return "error";
    }
}
