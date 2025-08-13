package com.example.gymsystem_layeredproject.bo.custom;

import com.example.gymsystem_layeredproject.bo.SuperBO;
import com.example.gymsystem_layeredproject.dto.SupplementDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplementBO extends SuperBO {
    public List<SupplementDTO> getAllSupplements() throws SQLException, ClassNotFoundException;
    public  boolean addSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException;
    public  String generateNextSupplementId() throws SQLException, ClassNotFoundException;
    public  boolean updateSupplementQuantity(String supplementId, int newQty) throws SQLException, ClassNotFoundException;
}
