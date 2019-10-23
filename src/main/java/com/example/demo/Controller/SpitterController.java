package com.example.demo.Controller;

import com.example.demo.Model.Spitter;
import com.example.demo.Service.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterService spitterService;

    public SpitterController(SpitterService spitterService){
        this.spitterService=spitterService;
    }

    @GetMapping(value="/register")
    public String getRegister(){
        return "registerForm";
    }

    @PostMapping("/submit")
    public String saveRegisterData( @Valid Spitter spitter,
                                    Errors errors){
        if(errors.hasErrors()){
            return "registerForm";
        }
        spitterService.saveSpitter(spitter);
        return "";
    }
    @GetMapping("/spitters")
    public String getSpittles(Model model){
        model.addAttribute("spitters",spitterService.getAllSpitters());
        return "spitters";
    }
}
