package com.example.demo.Controller;

import com.example.demo.Model.Spitter;
import com.example.demo.Service.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


// Accept request parameter include
// 1.Query Parameter
// 2.Form Parameter
// 3.Path Variable

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterService spitterService;

    public SpitterController(SpitterService spitterService){
        this.spitterService=spitterService;
    }

    @GetMapping(value="/register")
    public String getRegister(Model model){
        model.addAttribute("spitters",new Spitter());
        return "registerForm";
    }

    //form parameter
    @PostMapping("/register")
    public String saveRegisterData( @Valid Spitter spitter,
                                    BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("spitters",new Spitter());
            return "registerForm";
        }
        spitterService.saveSpitter(spitter);
        return "redirect:/spitter/userName/" + spitter.getUsername();
    }

    @GetMapping("userName/{userName}")
    public String getSpitterByUserName(@PathVariable("userName") String userName,Model model){
        model.addAttribute("spitters",spitterService.findSpitterByUserName(userName));
        return "spitters";
    }
    @GetMapping("/spitters")
    public String getSpittles(Model model){
        model.addAttribute("spitters",spitterService.getAllSpitters());
        return "spitters";
    }

    // PathVariable is used as a resource
    @GetMapping("/{spittleId}")
    public String getSpittleBySpittleId(@PathVariable("spittleId") long spittleId,Model model){
        model.addAttribute("spitter",spitterService.getSpitterById(spittleId));
        return "spitter";
    }
    //page start with 0
    @GetMapping("/page")
    public String getSpittleByPage(@RequestParam(value = "page") int page,@RequestParam(value = "size") int size,Model model){
        model.addAttribute("spitters",spitterService.getSpittersByPage(page,size));
        return "spitters";
    }

}
