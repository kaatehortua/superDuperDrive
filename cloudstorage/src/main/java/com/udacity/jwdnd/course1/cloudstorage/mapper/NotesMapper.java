package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotesMapper {
    @Select("SELECT * FROM NOTES")
    List<Notes> findAll();

    @Select("SELECT * FROM NOTES WHERE userid=#{userid}")
    public List<Notes> findByUserId(int userid);

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteid}")
    public Notes findByOne(int noteid);

    @Update("UPDATE NOTES SET notetitle=#{notetitle},notedescription=#{notedescription}" +
            "WHERE noteid=#{noteid}")
    public int updateNote(Notes note);
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    public int deleteNote(int noteid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
    public int insertNote(Notes note, int userid);


}
