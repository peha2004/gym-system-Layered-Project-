package com.example.gymsystem_layeredproject.bo.custom.impl;

import com.example.gymsystem_layeredproject.bo.custom.SupplementBO;
import com.example.gymsystem_layeredproject.dao.DAOFactory;
import com.example.gymsystem_layeredproject.dao.custom.impl.SupplementDAOImpl;
import com.example.gymsystem_layeredproject.entity.Supplement;
import com.example.gymsystem_layeredproject.dto.SupplementDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementBOImpl implements SupplementBO {

    SupplementDAOImpl supplementDAO = (SupplementDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLEMENT);
    @Override
    public List<SupplementDTO> getAllSupplements() throws SQLException, ClassNotFoundException {
        List<Supplement> supplements = supplementDAO.getAllSupplements();
        List<SupplementDTO> dtos = new ArrayList<>();
        for (Supplement s : supplements) {
            dtos.add(new SupplementDTO(
                    s.getSupplementId(),
                    s.getSupplemetName(),
                    s.getPrice(),
                    s.getQuantity()
            ));
        }
        return dtos;
    }
    @Override
    public boolean addSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException {
        Supplement s = new Supplement(
                dto.getSupplementId(),
                dto.getSupplemetName(),
                dto.getPrice(),
                dto.getQuantity()
        );
        return supplementDAO.addSupplement(s);
    }

    @Override
    public String generateNextSupplementId() throws SQLException, ClassNotFoundException {
        return supplementDAO.generateNextSupplementId();
    }

    @Override
    public boolean updateSupplementQuantity(String supplementId, int newQty) throws SQLException, ClassNotFoundException {
        return supplementDAO.updateSupplementQuantity(supplementId, newQty);
    }
}
