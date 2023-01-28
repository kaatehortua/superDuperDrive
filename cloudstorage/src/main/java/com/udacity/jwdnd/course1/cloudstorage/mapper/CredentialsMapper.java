package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS ")
    List<Credentials> findAll();

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{id}")
    public Credentials findOne(int id);

    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userid}")
    public List<Credentials> findByUserId(int userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) " +
            "VALUES (#{credential.url}, #{credential.username}," +
            " #{credential.key}, #{credential.password}, #{userid}))")
    public int insertCredentials(Credentials credential,int userid);

    @Update("UPDATE CREDENTIALS SET url= #{url}, username = #{username}, key = #{key}, password = #{password} " +
            "WHERE credentialid = #{credentialid}\") #{url}, username = #{username}," +
            " key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    public int updateCredentials(Credentials credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialid}")
    public int deleteCredential(int credential);

}
