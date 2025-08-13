    package com.example.gymsystem_layeredproject.dao.custom.impl;



    import com.example.gymsystem_layeredproject.dao.SQLUtil;
    import com.example.gymsystem_layeredproject.dao.custom.MemberDAO;
    import com.example.gymsystem_layeredproject.db.DBConnection;
    import com.example.gymsystem_layeredproject.entity.Member;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class MemberDAOImpl implements MemberDAO {

        public  String generateMemberId() throws SQLException, ClassNotFoundException {
            ResultSet rst = SQLUtil.executeQuery("SELECT member_id FROM Members ORDER BY member_id DESC LIMIT 1");
            if (rst.next()) {
                String lastId = rst.getString("member_id");
                int newId = Integer.parseInt(lastId.replace("M", "")) + 1;
                return String.format("M%03d", newId);
            } else {
                return "M001";
            }

        }
        public  boolean save(Member entity) throws SQLException, ClassNotFoundException {
            return SQLUtil.executeUpdate("INSERT INTO Members (member_id, name, gender, contact) VALUES (?, ?, ?, ?)", entity.getId(), entity.getName(), entity.getGender(), entity.getContact()
            );
        }
        public  List<Member> getAll() throws SQLException, ClassNotFoundException {
            ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Members");
            ArrayList<Member> members = new ArrayList<>();
            while (rst.next()) {members.add(new Member(
                        rst.getString("member_id"),
                        rst.getString("name"),
                        rst.getString("gender"),
                        rst.getString("contact")
                ));
            }
            return members;
        }

        public  boolean update(Member entity) throws SQLException, ClassNotFoundException {
            return SQLUtil.executeUpdate("UPDATE Members SET name = ?, gender = ?, contact = ? WHERE member_id = ?", entity.getName(), entity.getGender(), entity.getContact(), entity.getId()
            );
        }
        public  boolean delete(String id) throws SQLException, ClassNotFoundException {
            return SQLUtil.executeUpdate("DELETE FROM Members WHERE member_id = ?", id);
        }


        public  List<String> getAllMemberIds() throws SQLException, ClassNotFoundException {
            List<String> ids = new ArrayList<>();
            ResultSet rst = SQLUtil.executeQuery("SELECT member_id FROM Members");
            while (rst.next()) {
                ids.add(rst.getString("member_id"));
            }
            return ids;
        }

    }
