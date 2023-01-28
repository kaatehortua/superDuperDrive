package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Admin;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FilesController {
    @Autowired
    private FileService fileService;

    @PostMapping("/files")
    public String saveFile(Authentication authentication, MultipartFile file) throws IOException{
        Admin admin=(Admin) authentication.getPrincipal();
        if(file== null){
            return "redirect:/result?error";
        }
        fileService.addFile(file,admin.getUserId());
        return "redirect:/result?success";
    }

    @GetMapping("/files/delete")
    public String delete(@RequestParam("id") int fileid){
        if(fileid>0){
            fileService.deleteFile(fileid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
