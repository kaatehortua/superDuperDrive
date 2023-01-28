package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {

    @Autowired
    private CredentialsMapper credentialsMapper;

    @Autowired
    private EncryptionService encryptionService;

    private Credentials encryption(Credentials credentials){
        String key =RandomStringUtils.random(8,true,true);
        credentials.setKey(key);
        credentials.setPassword(encryptionService.encryptValue((credentials.getPassword()),key));
        return  credentials;
    }

    public Credentials resolve(Credentials credentials){
        credentials.setPassword(encryptionService.decryptValue(credentials.getPassword(), credentials.getKey()));
        return credentials;
    }
    public List<Credentials>getAllCredential(int userid)throws Exception{
        List<Credentials> credentials=credentialsMapper.findByUserId(userid);
        if(credentials.isEmpty()){
            throw new Exception("No credentials here");
        }
        return credentials.stream().map(this::resolve).collect(Collectors.toList());
    }

    public void addCredentials(Credentials credentials, int userid){
        credentialsMapper.insertCredentials(encryption(credentials),userid);
    }

    public void updateCredentials(Credentials credentials){
        credentialsMapper.updateCredentials(encryption(credentials));

    }
    public void deleteCredential(int credentialid){
        credentialsMapper.deleteCredential(credentialid);
    }
}
