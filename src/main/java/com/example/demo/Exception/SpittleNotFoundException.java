package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//define customer error
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Spitte not found")
public class SpittleNotFoundException extends RuntimeException {

}
