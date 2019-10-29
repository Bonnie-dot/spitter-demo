package com.example.demo.Controller;

import com.example.demo.Service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileSystemStorageService  fssService;

    @PostMapping("/uploadFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        fssService.store(file);
        ////redirect pass parameter:addFlashAttribute() 会话
        redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/file/form";
    }

    @GetMapping("/form")
    public String getUploadFilePage(){
        return "file";
    }

    @GetMapping("/fileList")
    public String getFileList(Model model){
        model.addAttribute("file",
                fssService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileController.class,"serveFile",path.getFileName().toString())
                ).collect(Collectors.toList())
        );
        return "file";
    }

    //it can be download automatically in browser
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource resource=fssService.loadAsResource(filename);
        // content_disposition ---- multipartFile
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }
}
