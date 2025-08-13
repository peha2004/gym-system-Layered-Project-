package com.example.gymsystem_layeredproject.dao.custom;

import com.example.gymsystem_layeredproject.dao.CrudDAO;
import com.example.gymsystem_layeredproject.entity.WorkoutPlan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface WorkoutPlanDAO extends CrudDAO<WorkoutPlan> {

  //  public  boolean save(WorkoutPlan dto) ;
   // public  List<WorkoutPlan> getAll() ;
  //  public  boolean update(WorkoutPlan dto) ;
    public  boolean delete(String planName, String memberId, String trainerId, LocalDate startDate , String email) throws SQLException, ClassNotFoundException;
}
