package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Admin;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NotesService notesService;
    private UserService userService;

    @PostMapping("/notes")
    public String noteCreateUpdate(Authentication auth,Notes notes){
        Admin user=(Admin) auth.getPrincipal();
        if(notes.getNoteid()>0){
            notesService.update(notes);
        }else{
            notesService.addNote(notes,user.getUserId());
        }

        return "redirect:result?success";

         }

         @GetMapping("/notes/delete")
        public String deleteNote(@RequestParam("id") int noteid){
        if(noteid>0){
            notesService.deleteNote(noteid);
            return "redirect:result?success";
        }else {
            return "redirect:result?error";
        }
         }
}
