package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FilesMapper filesMapper;

    public ResponseFiles getResponseFile(Files files){
        String byte64= Base64.getEncoder().encodeToString(files.getFiledata());
        String urldata="data: "+files.getContenttype()+";byte64,"+byte64;
        return ResponseFiles.builder().fileid(files.getFileid()).filename(files.getFilename()).dataURL(urldata).build();
    }
    public List<ResponseFiles> getAllFiles(int userid) throws IllegalArgumentException{
        List<Files>files = filesMapper.findByUserId(userid);
        if (files.isEmpty()){
            throw new IllegalArgumentException("here is not files");
        }
        return files.stream().map(this::getResponseFile).collect(Collectors.toList());
    }
    public void addFile(MultipartFile fileUpload,int userid) throws IOException {
        Files files=new Files();
        try {
            files.setContenttype(fileUpload.getContentType());
            files.setFiledata(fileUpload.getBytes());
            files.setFilename(fileUpload.getOriginalFilename());
            files.setFilesize(Long.toString(fileUpload.getSize()));
        }catch (Exception e){
            throw e;
        }
        filesMapper.insertFile(files,userid);
    }
    public void deleteFile(int fileid){
        filesMapper.deleteFile(fileid);
    }
}
