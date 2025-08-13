package com.example.gymsystem_layeredproject.bo.custom.impl;



import com.example.gymsystem_layeredproject.dao.DAOFactory;
import com.example.gymsystem_layeredproject.dao.custom.impl.EquipmentDAOImpl;
import com.example.gymsystem_layeredproject.bo.custom.EquipmentBO;
import com.example.gymsystem_layeredproject.entity.Equipment;
import com.example.gymsystem_layeredproject.dto.EquipmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentBOImpl implements EquipmentBO {
   EquipmentDAOImpl equipmentDAO = (EquipmentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EQUIPMENT);

    @Override
    public boolean save(EquipmentDTO dto) throws SQLException, ClassNotFoundException {
        Equipment entity = new Equipment(dto.getEquipmentId(), dto.getName(), dto.getStatus());
        return equipmentDAO.save(entity);
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() throws SQLException, ClassNotFoundException {
        List<Equipment> entities = equipmentDAO.getAll();
        List<EquipmentDTO> dtoList = new ArrayList<>();
        for (Equipment entity : entities) {
            dtoList.add(new EquipmentDTO(entity.getEquipmentId(), entity.getName(), entity.getStatus()));
        }
        return dtoList;
    }

    @Override
    public String generateEquipmentId() throws SQLException, ClassNotFoundException {
        return equipmentDAO.generateEquipmentId();
    }

    @Override
    public boolean update(EquipmentDTO dto) throws SQLException, ClassNotFoundException {
        Equipment entity = new Equipment(dto.getEquipmentId(), dto.getName(), dto.getStatus());
        return equipmentDAO.update(entity);
    }

    @Override
    public boolean delete(String equipmentId) throws SQLException, ClassNotFoundException {
        return equipmentDAO.delete(equipmentId);
    }
}
