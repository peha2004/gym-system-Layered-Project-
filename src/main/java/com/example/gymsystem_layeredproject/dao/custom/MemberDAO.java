package com.example.gymsystem_layeredproject.dao.custom;

import com.example.gymsystem_layeredproject.dao.CrudDAO;
import com.example.gymsystem_layeredproject.entity.Member;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface MemberDAO extends CrudDAO<Member> {
    public  String generateMemberId()throws SQLException, ClassNotFoundException ;
    //public  boolean save(Member dto) ;
    //public  List<Member> getAll() ;
// public List<Member> getAllMembers();
   // public  boolean update(Member dto) ;
  //  public  boolean delete(String id) ;


    public  List<String> getAllMemberIds() throws SQLException, ClassNotFoundException;
}
