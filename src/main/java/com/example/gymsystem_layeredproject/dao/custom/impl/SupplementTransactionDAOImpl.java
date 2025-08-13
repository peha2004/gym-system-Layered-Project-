package com.example.gymsystem_layeredproject.dao.custom.impl;

import com.example.gymsystem_layeredproject.dao.SQLUtil;
import com.example.gymsystem_layeredproject.dao.custom.SupplementTransactionDAO;
import com.example.gymsystem_layeredproject.db.DBConnection;
import com.example.gymsystem_layeredproject.entity.SupplementCartItem;
import com.example.gymsystem_layeredproject.entity.SupplementTransaction;
import com.example.gymsystem_layeredproject.entity.SupplementTransactionHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplementTransactionDAOImpl implements SupplementTransactionDAO {

    public boolean saveTransaction(SupplementTransaction dto) throws SQLException, ClassNotFoundException {
        try {
            SQLUtil.beginTransaction();

            SQLUtil.executeUpdate("INSERT INTO SupplementTransaction VALUES (?, ?, ?, ?)",
                    dto.getTransactionId(), dto.getMemberId(), dto.getDate(), dto.getTotalAmount());

            for (SupplementCartItem item : dto.getItems()) {
                SQLUtil.executeUpdate("INSERT INTO SupplementTransactionItem VALUES (?, ?, ?, ?)",
                        dto.getTransactionId(), item.getSupplementId(), item.getQuantity(), item.getPrice());

                SQLUtil.executeUpdate("UPDATE Supplement SET quantity = quantity - ? WHERE supplement_id = ?",
                        item.getQuantity(), item.getSupplementId());
            }

            SQLUtil.commit();
            return true;

        } catch (Exception e) {
            SQLUtil.rollback();
            throw e;
        }
    }

    @Override
    public String generateNextTransactionId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery(
                "SELECT transaction_id FROM SupplementTransaction ORDER BY transaction_id DESC LIMIT 1"
        );
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextNum = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("T%03d", nextNum);
        } else {
            return "T001";
        }
    }

    @Override
    public List<SupplementTransactionHistory> getAllTransactions() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM SupplementTransaction");
        List<SupplementTransactionHistory> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new SupplementTransactionHistory(
                    rs.getString("transaction_id"),
                    rs.getString("member_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getDouble("total_amount")
            ));
        }
        return list;
    }

    @Override
    public boolean save(SupplementTransaction dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<SupplementTransaction> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update(SupplementTransaction dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
