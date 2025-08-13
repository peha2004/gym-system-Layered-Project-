package com.example.gymsystem_layeredproject.bo.custom.impl;

import com.example.gymsystem_layeredproject.bo.custom.SupplementTransactionBO;
import com.example.gymsystem_layeredproject.dao.custom.impl.SupplementTransactionDAOImpl;
import com.example.gymsystem_layeredproject.entity.SupplementCartItem;
import com.example.gymsystem_layeredproject.entity.SupplementTransaction;
import com.example.gymsystem_layeredproject.entity.SupplementTransactionHistory;
import com.example.gymsystem_layeredproject.dto.SupplementTransactionDTO;
import com.example.gymsystem_layeredproject.dto.SupplementTransactionHistoryDTO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementTransactionBOImpl implements SupplementTransactionBO {
    SupplementTransactionDAOImpl transactionDAO = new SupplementTransactionDAOImpl();

    @Override
    public boolean saveTransaction(SupplementTransactionDTO dto) throws SQLException, ClassNotFoundException {
//        SupplementTransaction transaction = new SupplementTransaction(
//                dto.getTransactionId(),
//                dto.getMemberId(),
//                dto.getDate(),
//                dto.getTotalAmount(),
//                dto.getItems() // items are already in DTO form; used directly in Entity too
//        );
//        return transactionDAO.saveTransaction(transaction);
        List<SupplementCartItem> entityItems = new ArrayList<>();
        for (var dtoItem : dto.getItems()) {
            entityItems.add(new SupplementCartItem(
                    dto.getTransactionId(),
                    dtoItem.getSupplementId(),
                    dtoItem.getSupplementName(),
                    dtoItem.getQuantity(),
                    dtoItem.getPrice()
            ));
        }

        SupplementTransaction transaction = new SupplementTransaction(
                dto.getTransactionId(),
                dto.getMemberId(),
                dto.getDate(),
                dto.getTotalAmount(),
                entityItems
        );

        return transactionDAO.saveTransaction(transaction);
    }

    @Override
    public String generateNextTransactionId() throws SQLException, ClassNotFoundException {
        return transactionDAO.generateNextTransactionId();
    }

    @Override
    public List<SupplementTransactionHistoryDTO> getAllTransactions() throws SQLException, ClassNotFoundException {
        List<SupplementTransactionHistory> transactions = transactionDAO.getAllTransactions();
        List<SupplementTransactionHistoryDTO> dtos = new ArrayList<>();

        for (SupplementTransactionHistory t : transactions) {
            dtos.add(new SupplementTransactionHistoryDTO(
                    t.getTransactionId(),
                    t.getMemberId(),
                    t.getDate(),
                    t.getTotalAmount()
            ));
        }
        return dtos;
    }
}
