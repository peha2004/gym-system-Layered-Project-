package com.example.gymsystem_layeredproject.dao.custom.impl;



import com.example.gymsystem_layeredproject.dao.SQLUtil;
import com.example.gymsystem_layeredproject.dao.custom.PaymentDAO;
import com.example.gymsystem_layeredproject.db.DBConnection;
import com.example.gymsystem_layeredproject.entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    public  boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Payment (payment_id, member_id, plan_name, payment_method, date, amount) VALUES (?, ?, ?, ?, ?, ?)", entity.getPaymentId(), entity.getMemberId(), entity.getPlanName(), entity.getPaymentMethod(), entity.getDate(), entity.getAmount());
    }
    public  String generatePaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT payment_id FROM Payment ORDER BY payment_id DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString("payment_id");
            int num = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("P%03d", num);
        } else {
            return "P001";
        }
    }
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Payment");
        ArrayList<Payment> payments = new ArrayList<>();
        while (rst.next()) {
            payments.add(new Payment(
                    rst.getString("payment_id"),
                    rst.getString("member_id"),
                    rst.getString("plan_name"),
                    rst.getString("payment_method"),
                    rst.getDate("date").toLocalDate(),
                    rst.getBigDecimal("amount")
            ));
        }
        return payments;
    }

    @Override
    public boolean update(Payment dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
