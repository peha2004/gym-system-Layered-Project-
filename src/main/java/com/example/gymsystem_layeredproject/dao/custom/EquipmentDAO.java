package com.example.gymsystem_layeredproject.dao.custom;

import com.example.gymsystem_layeredproject.dao.CrudDAO;
import com.example.gymsystem_layeredproject.entity.Equipment;
//import org.example.gymsystem.entity.Equipment;
import com.example.gymsystem_layeredproject.entity.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDAO extends CrudDAO <Equipment> {

   // public  boolean save( Equipment dto) ;
   // public List<Equipment> getAll();
    public  String generateEquipmentId() throws SQLException, ClassNotFoundException;
 //   public  boolean update(Equipment dto) ;
  //  public  boolean delete(String equipmentId) ;
}
