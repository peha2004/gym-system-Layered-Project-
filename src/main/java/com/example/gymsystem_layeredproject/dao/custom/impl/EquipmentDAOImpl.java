package com.example.gymsystem_layeredproject.dao.custom.impl;



import com.example.gymsystem_layeredproject.dao.SQLUtil;
import com.example.gymsystem_layeredproject.dao.custom.EquipmentDAO;
import com.example.gymsystem_layeredproject.db.DBConnection;
import com.example.gymsystem_layeredproject.entity.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {

    public  boolean save(Equipment entity) throws SQLException, ClassNotFoundException {
       // String sql = "INSERT INTO Equipment (equipment_id, name, status) VALUES (?, ?, ?)";
        return SQLUtil.executeUpdate("INSERT INTO Equipment (equipment_id, name, status) VALUES (?, ?, ?)",entity.getEquipmentId(),entity.getName(),entity.getStatus());
    }
    public List<Equipment> getAll() throws SQLException, ClassNotFoundException {
        List<Equipment> list = new ArrayList<>();
        String sql = "SELECT * FROM Equipment";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Equipment(
                        rs.getString("equipment_id"),
                        rs.getString("name"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
//        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Members");
//        ArrayList<Equipment> equipment = new ArrayList<>();
//        while (rst.next()) {equipment.add(new Equipment(
//                rst.getString("equipment_id"),
//                rst.getString("name"),
//                rst.getString("status")
//        ));
//        }
//        return equipment;
    }
    public  String generateEquipmentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery(
                "SELECT equipment_id FROM Equipment ORDER BY equipment_id DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString("equipment_id");
            int num = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("E%03d", num);
        } else {
            return "E001";
        }
    }
    public  boolean update(Equipment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Equipment SET name = ?, status = ? WHERE equipment_id = ?", entity.getName(), entity.getStatus(), entity.getEquipmentId());
    }
    public  boolean delete(String equipmentId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "DELETE FROM Equipment WHERE equipment_id = ?", equipmentId);
    }
}
