package com.example.gymsystem_layeredproject.bo.custom.impl;

import com.example.gymsystem_layeredproject.bo.custom.WorkoutPlanBO;
import com.example.gymsystem_layeredproject.dao.DAOFactory;
import com.example.gymsystem_layeredproject.dao.custom.impl.WorkoutPlanDAOImpl;
import com.example.gymsystem_layeredproject.entity.WorkoutPlan;
import com.example.gymsystem_layeredproject.dto.WorkoutDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanBOImpl implements WorkoutPlanBO {

   WorkoutPlanDAOImpl workoutPlanDAO = (WorkoutPlanDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.WORKOUTPLAN);
    @Override
    public boolean save(WorkoutDTO dto) throws SQLException, ClassNotFoundException {
        return workoutPlanDAO.save(new WorkoutPlan(dto.getPlanName(),
                dto.getDuration(),
                dto.getMemberId(),
                dto.getTrainerId(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getEmail()));
    }

    @Override
    public List<WorkoutDTO> getAllPlans() throws SQLException, ClassNotFoundException {
        List<WorkoutPlan> plans = workoutPlanDAO.getAll();
        List<WorkoutDTO> dtos = new ArrayList<>();

        for (WorkoutPlan p : plans) {
            dtos.add(new WorkoutDTO(
                    p.getPlanName(),
                    p.getDuration(),
                    p.getMemberId(),
                    p.getTrainerId(),
                    p.getStartDate(),
                    p.getEndDate(),
                    p.getEmail()
            ));
        }

        return dtos;
    }

    @Override
    public boolean update(WorkoutDTO dto) throws SQLException, ClassNotFoundException {
        return workoutPlanDAO.update(new WorkoutPlan( dto.getPlanName(),
                dto.getDuration(),
                dto.getMemberId(),
                dto.getTrainerId(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getEmail()));
    }

    @Override
    public boolean delete(String planName, String memberId, String trainerId, LocalDate startDate, String email) throws SQLException, ClassNotFoundException {
        return workoutPlanDAO.delete(planName, memberId, trainerId, startDate, email);
    }
}
