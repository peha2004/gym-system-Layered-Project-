package com.example.gymsystem_layeredproject.bo.custom.impl;



import com.example.gymsystem_layeredproject.dao.DAOFactory;
import com.example.gymsystem_layeredproject.dao.custom.impl.TrainerDAOImpl;
import com.example.gymsystem_layeredproject.bo.custom.TrainerBO;
import com.example.gymsystem_layeredproject.entity.Trainer;
import com.example.gymsystem_layeredproject.dto.TrainerDTO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerBOImpl implements TrainerBO {
    TrainerDAOImpl trainerDAO = (TrainerDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TRAINER);

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return trainerDAO.generateNewId();
    }

    @Override
    public List<TrainerDTO> getAll() throws SQLException, ClassNotFoundException {
       List<Trainer> trainerDTO = trainerDAO.getAll();
       List<TrainerDTO> trainerDTOList = new ArrayList<>();

       for (Trainer trainer : trainerDTO) {
           trainerDTOList.add(new TrainerDTO(
                   trainer.getTrainerId(),
                   trainer.getTrainerName(),
                   trainer.getTrainerPhone(),
                   trainer.getSpecialization()
           ));
       }

       return trainerDTOList;
    }

//    @Override
//    public boolean save() {
//        return false;
//    }

    @Override
    public boolean save(TrainerDTO dto) throws SQLException, ClassNotFoundException {
        return trainerDAO.save(new Trainer(dto.getTrainerId(),dto.getTrainerName(),dto.getTrainerPhone(),dto.getSpecialization()));
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return trainerDAO.getAllIds();
    }

    @Override
    public boolean update(TrainerDTO dto) throws SQLException, ClassNotFoundException {
        return trainerDAO.update(new Trainer(dto.getTrainerId(), dto.getTrainerName(), dto.getTrainerPhone(), dto.getSpecialization()));
    }

    @Override
    public boolean delete(String trainerId) throws SQLException, ClassNotFoundException {
        return trainerDAO.delete(trainerId);
    }
}
