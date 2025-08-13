package com.example.gymsystem_layeredproject.dao.custom;

import com.example.gymsystem_layeredproject.dao.CrudDAO;
import com.example.gymsystem_layeredproject.entity.Trainer;


import java.sql.SQLException;
import java.util.List;

public interface TrainerDAO extends CrudDAO<Trainer > {
    public  String generateNewId() throws SQLException, ClassNotFoundException;
    //public List<Trainer> getAll();
   // public  boolean save(Trainer dto);
    public  List<String> getAllIds() throws SQLException, ClassNotFoundException;
    //public  boolean update(Trainer dto);
   // public  boolean delete(String trainerId);
}
