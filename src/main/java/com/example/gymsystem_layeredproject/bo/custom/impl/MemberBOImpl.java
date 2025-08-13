package com.example.gymsystem_layeredproject.bo.custom.impl;



import com.example.gymsystem_layeredproject.dao.DAOFactory;
import com.example.gymsystem_layeredproject.dao.custom.impl.MemberDAOImpl;
import com.example.gymsystem_layeredproject.bo.custom.MemberBO;
import com.example.gymsystem_layeredproject.entity.Member;
import com.example.gymsystem_layeredproject.dto.MemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {

  MemberDAOImpl memberDAO = (MemberDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEMBER);


    @Override
    public String generateMemberId() throws SQLException, ClassNotFoundException {
        return memberDAO.generateMemberId();
    }

    @Override
    public boolean save(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return  memberDAO.save(new Member(
                dto.getId(),
                dto.getName(),
                dto.getGender(),
                dto.getContact()));
    }

    @Override
    public List<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException {
        List<Member> members = memberDAO.getAll();
        List<MemberDTO> dtos = new ArrayList<>();

        for (Member m : members) {
            dtos.add(new MemberDTO(
                    m.getId(),
                    m.getName(),
                    m.getGender(),
                    m.getContact()
            ));
        }

        return dtos;
    }

    @Override
    public boolean update(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.update(new Member(
                dto.getId(),
                dto.getName(),
                dto.getGender(),
                dto.getContact()
        ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(id);
    }

    @Override
    public List<String> getAllMemberIds() throws SQLException, ClassNotFoundException {
        return memberDAO.getAllMemberIds();
    }
}
