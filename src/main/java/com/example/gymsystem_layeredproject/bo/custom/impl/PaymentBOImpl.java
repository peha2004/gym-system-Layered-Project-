package com.example.gymsystem_layeredproject.bo.custom.impl;


import com.example.gymsystem_layeredproject.dao.DAOFactory;
import com.example.gymsystem_layeredproject.dao.custom.impl.PaymentDAOImpl;
import com.example.gymsystem_layeredproject.bo.custom.PaymentBO;
import com.example.gymsystem_layeredproject.entity.Payment;
import com.example.gymsystem_layeredproject.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

//    @Override
//    public boolean save() {
//        return false;
//    }

    @Override
    public boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPaymentId(),dto.getMemberId(),dto.getPlanName(),dto.getPaymentMethod(),dto.getDate(),dto.getAmount()));
    }

    @Override
    public String generatePaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generatePaymentId();
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        List<Payment> paymentList = paymentDAO.getAll();
        List<PaymentDTO> dtoList = new ArrayList<>();

        for (Payment payment : paymentList) {
            dtoList.add(new PaymentDTO(
                    payment.getPaymentId(),
                    payment.getMemberId(),
                    payment.getPlanName(),
                    payment.getPaymentMethod(),
                    payment.getDate(),
                    payment.getAmount()
            ));
        }

        return dtoList;
    }
}
