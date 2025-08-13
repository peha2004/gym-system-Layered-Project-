package com.example.gymsystem_layeredproject.bo.custom;

import com.example.gymsystem_layeredproject.bo.SuperBO;
import com.example.gymsystem_layeredproject.dto.WorkoutDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutPlanBO extends SuperBO {

    public  boolean save(WorkoutDTO dto) throws SQLException, ClassNotFoundException;
    public List<WorkoutDTO> getAllPlans() throws SQLException, ClassNotFoundException;
    public  boolean update(WorkoutDTO dto) throws SQLException, ClassNotFoundException;
    public  boolean delete(String planName, String memberId, String trainerId, LocalDate startDate , String email) throws SQLException, ClassNotFoundException;
}
