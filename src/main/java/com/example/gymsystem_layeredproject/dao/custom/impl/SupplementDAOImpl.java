package com.example.gymsystem_layeredproject.dao.custom.impl;

import com.example.gymsystem_layeredproject.dao.SQLUtil;
import com.example.gymsystem_layeredproject.dao.custom.SupplementDAO;
import com.example.gymsystem_layeredproject.db.DBConnection;
import com.example.gymsystem_layeredproject.entity.Supplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAOImpl implements SupplementDAO {
    public  List<Supplement> getAllSupplements() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Supplement");
        List<Supplement> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Supplement(
                    rs.getString("supplement_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
            ));
        }
        return list;
    }
    public  boolean addSupplement(Supplement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Supplement (supplement_id, name, price, quantity) VALUES (?, ?, ?, ?)", entity.getSupplementId(), entity.getSupplemetName(), entity.getPrice(), entity.getQuantity());
    }
    public  String generateNextSupplementId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT supplement_id FROM Supplement ORDER BY supplement_id DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextNum = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("S%03d", nextNum);
        } else {
            return "S001";
        }
    }
    public  boolean updateSupplementQuantity(String supplementId, int newQty) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Supplement SET quantity = ? WHERE supplement_id = ?", newQty, supplementId);
    }

    @Override
    public boolean save(Supplement dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Supplement> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update(Supplement dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
