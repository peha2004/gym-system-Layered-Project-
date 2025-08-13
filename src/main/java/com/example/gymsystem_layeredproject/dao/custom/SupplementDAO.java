package com.example.gymsystem_layeredproject.dao.custom;

import com.example.gymsystem_layeredproject.dao.CrudDAO;
import com.example.gymsystem_layeredproject.entity.Supplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplementDAO  {

    public  List<Supplement> getAllSupplements() throws SQLException, ClassNotFoundException;
    public  boolean addSupplement(Supplement dto) throws SQLException, ClassNotFoundException;
    public  String generateNextSupplementId() throws SQLException, ClassNotFoundException;
    public  boolean updateSupplementQuantity(String supplementId, int newQty) throws SQLException, ClassNotFoundException;
}
