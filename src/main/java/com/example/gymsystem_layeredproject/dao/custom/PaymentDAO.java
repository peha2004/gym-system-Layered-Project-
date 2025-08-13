package com.example.gymsystem_layeredproject.dao.custom;

import com.example.gymsystem_layeredproject.dao.CrudDAO;
import com.example.gymsystem_layeredproject.entity.Payment;


import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment>  {
   // public  boolean save(Payment dto);
    public  String generatePaymentId() throws SQLException, ClassNotFoundException;
   // List<Payment> getAll();
}
