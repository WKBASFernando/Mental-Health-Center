package com.ijse.orm.mentalhealthcenter.bo.custom.impl;

import com.ijse.orm.mentalhealthcenter.bo.custom.TherapyProgramBO;
import com.ijse.orm.mentalhealthcenter.config.FactoryConfiguration;
import com.ijse.orm.mentalhealthcenter.dao.DAOFactory;
import com.ijse.orm.mentalhealthcenter.dao.DAOType;
import com.ijse.orm.mentalhealthcenter.dao.custom.TherapyProgramDAO;
import com.ijse.orm.mentalhealthcenter.dto.TherapyProgramDTO;
import com.ijse.orm.mentalhealthcenter.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapyProgramBOImpl implements TherapyProgramBO {
    TherapyProgramDAO tProgramDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPY_PROGRAMS);

    @Override
    public boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram tPrograms = new TherapyProgram();
            tPrograms.setId(therapyProgramDTO.getId());
            tPrograms.setName(therapyProgramDTO.getName());
            tPrograms.setDuration(therapyProgramDTO.getDuration());
            tPrograms.setFee(therapyProgramDTO.getFee());

            boolean isSaved = tProgramDAO.save(tPrograms,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving therapy programs", e);
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateTPrograms(TherapyProgramDTO therapyProgramDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram tPrograms = new TherapyProgram();
            tPrograms.setId(therapyProgramDTO.getId());
            tPrograms.setName(therapyProgramDTO.getName());
            tPrograms.setDuration(therapyProgramDTO.getDuration());
            tPrograms.setFee(therapyProgramDTO.getFee());

            boolean isUpdated =  tProgramDAO.update(tPrograms,session);
            if (isUpdated) {
                transaction.commit();
                return true;
            }else{
                transaction.rollback();
                return false;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error while saving therapy programs", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error while saving therapy programs");
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteTProgram(String therapyProgramID) {
        try {
            return tProgramDAO.deleteByPk(therapyProgramID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error while saving therapy programs", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error while saving therapy programs");
        }
    }

    @Override
    public List<TherapyProgramDTO> getALLTPrograms() throws Exception {
        List<TherapyProgram> programList = tProgramDAO.getAll();
        List<TherapyProgramDTO> programDtos = new ArrayList<>();
        for (TherapyProgram tPrograms : programList) {
            programDtos.add(new TherapyProgramDTO(tPrograms.getId(), tPrograms.getName(), tPrograms.getDuration(), tPrograms.getFee()));
        }
        return programDtos;
    }

    @Override
    public String getNextProgramID() {
        Optional<String> lastPkOptional = tProgramDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("MT1", "")) + 1;  // Extract number and increment
            return String.format("MT1%03d", nextId);
        } else {
            return "MT1001";  // Default if no records exist
        }
    }

    @Override
    public List<TherapyProgramDTO> getALL() throws Exception {
        List<TherapyProgram> tPrograms = tProgramDAO.getAll();
        List<TherapyProgramDTO> dtos = new ArrayList<>();

        for (TherapyProgram programs : tPrograms) {
            TherapyProgramDTO dto = new TherapyProgramDTO(
                    programs.getId(),
                    programs.getName(),
                    programs.getDuration(),
                    programs.getFee()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
