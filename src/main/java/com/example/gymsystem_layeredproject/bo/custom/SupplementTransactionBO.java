package com.example.gymsystem_layeredproject.bo.custom;

import com.example.gymsystem_layeredproject.bo.SuperBO;
import com.example.gymsystem_layeredproject.dto.SupplementTransactionDTO;
import com.example.gymsystem_layeredproject.dto.SupplementTransactionHistoryDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplementTransactionBO extends SuperBO {
    public  boolean saveTransaction(SupplementTransactionDTO dto) throws SQLException, ClassNotFoundException;
    public  String generateNextTransactionId() throws SQLException, ClassNotFoundException;
    public List<SupplementTransactionHistoryDTO> getAllTransactions() throws SQLException, ClassNotFoundException;
}
