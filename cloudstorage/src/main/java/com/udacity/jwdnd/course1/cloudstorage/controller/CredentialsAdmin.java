package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Admin;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialsAdmin {

    @Autowired
    private CredentialService credentialService;

    @PostMapping("/credentials")
    public String updateCredential(Authentication auth, Credentials credentials){
        Admin admin=(Admin) auth.getPrincipal();

        if(credentials.getId()>0){
            credentialService.updateCredentials(credentials);

        }
            credentialService.addCredentials(credentials,admin.getUserId());

        return "redirect:/result?success";
    }



    @PostMapping("/credentials/delete")
    public String deleteNote(@RequestParam("id") int credentialid){

        if(credentialid<=0){
            return "redirect:/result?error";
        }else{
            credentialService.deleteCredential(credentialid);
            return "redirect:/result?success";
        }

    }
}
