package com.example.gymsystem_layeredproject.dao.custom;

import com.example.gymsystem_layeredproject.dao.CrudDAO;
import com.example.gymsystem_layeredproject.db.DBConnection;
import com.example.gymsystem_layeredproject.entity.SupplementTransaction;
import com.example.gymsystem_layeredproject.entity.SupplementTransactionHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface SupplementTransactionDAO  {

    public  boolean saveTransaction(SupplementTransaction dto) throws SQLException, ClassNotFoundException;
    public  String generateNextTransactionId() throws SQLException, ClassNotFoundException;
    public  List<SupplementTransactionHistory> getAllTransactions() throws SQLException, ClassNotFoundException;
}
