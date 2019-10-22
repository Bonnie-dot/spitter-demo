package com.example.demo.Controller;

import com.example.demo.Model.Spitter;
import com.example.demo.Service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(value = "/test")
    public String getTest(@RequestParam(name="userName", required=false) String userName, Model model){
        Spitter spitter = this.spitterService.findSpitterByUserName(userName);
        model.addAttribute("username",spitter.getUsername());
        return "test";
    }

}
