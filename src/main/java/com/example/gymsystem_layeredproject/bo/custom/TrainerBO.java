package com.example.gymsystem_layeredproject.bo.custom;

import com.example.gymsystem_layeredproject.bo.SuperBO;
import com.example.gymsystem_layeredproject.dto.TrainerDTO;


import java.sql.SQLException;
import java.util.List;

public interface TrainerBO extends SuperBO {
    public  String generateNewId() throws SQLException, ClassNotFoundException;
    public List<TrainerDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(TrainerDTO dto) throws SQLException, ClassNotFoundException;
    public  List<String> getAllIds() throws SQLException, ClassNotFoundException;
    public  boolean update(TrainerDTO dto) throws SQLException, ClassNotFoundException;
    public  boolean delete(String trainerId) throws SQLException, ClassNotFoundException;
}
