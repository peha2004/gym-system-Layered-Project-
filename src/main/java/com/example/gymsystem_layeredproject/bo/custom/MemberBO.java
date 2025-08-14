package com.example.gymsystem_layeredproject.bo.custom;

import com.example.gymsystem_layeredproject.bo.SuperBO;
import com.example.gymsystem_layeredproject.dto.MemberDTO;


import java.sql.SQLException;
import java.util.List;

public interface MemberBO extends SuperBO {
    public  String generateMemberId() throws SQLException, ClassNotFoundException;
    public  boolean save(MemberDTO dto) throws SQLException, ClassNotFoundException;
    public  List<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException;
    public  boolean update(MemberDTO dto) throws SQLException, ClassNotFoundException;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException;
    public  List<String> getAllMemberIds() throws SQLException, ClassNotFoundException;
}
