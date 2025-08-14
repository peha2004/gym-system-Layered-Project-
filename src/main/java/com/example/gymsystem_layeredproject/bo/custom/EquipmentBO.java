package com.example.gymsystem_layeredproject.bo.custom;



import com.example.gymsystem_layeredproject.bo.SuperBO;
import com.example.gymsystem_layeredproject.dto.EquipmentDTO;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentBO extends SuperBO {

    public  boolean save( EquipmentDTO dto) throws SQLException, ClassNotFoundException;
    public List<EquipmentDTO> getAllEquipment() throws SQLException, ClassNotFoundException;
    public  String generateEquipmentId() throws SQLException, ClassNotFoundException;
    boolean update(EquipmentDTO dto) throws SQLException, ClassNotFoundException;
    public  boolean delete(String equipmentId) throws SQLException, ClassNotFoundException;
}
