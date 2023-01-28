package com.udacity.jwdnd.course1.cloudstorage.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;

import java.util.List;

@Mapper
@Repository
public interface FilesMapper {
    @Select("SELECT * FROM FILES  ")
    List<Files> findAll();
    @Select("SELECT fileid,filename,contenttpye,filedata " +
            "FROM FILES where fileid=#{id_file} ")
    public Files findOne(int id);
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    public List <Files> findByUserId(int userid);
    @Delete("DELETE FROM FILES WHERE fileid=#{id_file}")
    public int deleteFile(int id);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid)" +
            " VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    public int insertFile(Files file,int userid);



}
