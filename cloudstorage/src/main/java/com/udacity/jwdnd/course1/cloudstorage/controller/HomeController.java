package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Admin;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
        @Autowired
        private NotesService notesService;

        @Autowired
        private CredentialService credentialService;

         @Autowired
        private FileService fileService;

         @GetMapping(value = {"/","/home"})
        public ModelAndView getHomePage(Authentication authentication) throws Exception{
                 Admin admin = (Admin) authentication.getPrincipal();
                 ModelAndView modelAndView= new ModelAndView("home");
                 modelAndView.addObject("notes",notesService.getAllNotes(admin.getUserId()));
                 modelAndView.addObject("files",fileService.getAllFiles(admin.getUserId()));
                 modelAndView.addObject("credentials",credentialService.getAllCredential(admin.getUserId()));
                 return modelAndView;


         }
        }
