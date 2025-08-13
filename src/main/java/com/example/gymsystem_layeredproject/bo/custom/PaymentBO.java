package com.example.gymsystem_layeredproject.bo.custom;

import com.example.gymsystem_layeredproject.bo.SuperBO;
import com.example.gymsystem_layeredproject.dto.PaymentDTO;


import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

//    public  boolean save();

    boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public  String generatePaymentId() throws SQLException, ClassNotFoundException;
    public List<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;
}
