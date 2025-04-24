package com.ijse.orm.mentalhealthcenter.bo.custom.impl;

import com.ijse.orm.mentalhealthcenter.bo.custom.TherapistBO;
import com.ijse.orm.mentalhealthcenter.config.FactoryConfiguration;
import com.ijse.orm.mentalhealthcenter.dao.DAOFactory;
import com.ijse.orm.mentalhealthcenter.dao.DAOType;
import com.ijse.orm.mentalhealthcenter.dao.custom.TherapistDAO;
import com.ijse.orm.mentalhealthcenter.dto.TherapistDTO;
import com.ijse.orm.mentalhealthcenter.entity.Therapist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);


    @Override
    public List<TherapistDTO> getALLTherapists() throws Exception {
        List<Therapist> therapists = therapistDAO.getAll();
        List<TherapistDTO> therapistDTOS = new ArrayList<>();

        for (Therapist therapist : therapists) {
            TherapistDTO therapistDTO = new TherapistDTO(
                    therapist.getId(),
                    therapist.getName(),
                    therapist.getAvailability(),
                    therapist.getDescription()
            );
            therapistDTOS.add(therapistDTO);
        }
        return therapistDTOS;
    }

    @Override
    public boolean saveTherapist(TherapistDTO therapistDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Therapist therapist = new Therapist();
            therapist.setId(therapistDTO.getId());
            therapist.setName(therapistDTO.getName());
            therapist.setAvailability(therapistDTO.getAvailability());
            therapist.setDescription(therapistDTO.getDescription());

            boolean isSaved =  therapistDAO.save(therapist,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else{
                transaction.rollback();
                return false;
            }

        } catch (HibernateException | SQLException e) {
            throw new RuntimeException("SQL error while saving therapist: " + e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = new Therapist();
            therapist.setId(therapistDTO.getId());
            therapist.setName(therapistDTO.getName());
            therapist.setAvailability(therapistDTO.getAvailability());
            therapist.setDescription(therapistDTO.getDescription());

            boolean isUpdated = therapistDAO.update(therapist,session);
            if (isUpdated) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Class not found Error");
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteTherapist(String therapistID) throws SQLException, ClassNotFoundException {
        try {
            return therapistDAO.deleteByPk(therapistID);
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error");
        }
    }

    @Override
    public String getNextTherapyID() {
        Optional<String> lastPkOptional = therapistDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("T", "")) + 1;
            return String.format("T%03d", nextId);
        } else {
            return "T001";  // Default if no records exist
        }
    }

    @Override
    public List<TherapistDTO> getTherapistsNames() throws Exception {
        List<Therapist> therapists = therapistDAO.getAll();
        List<TherapistDTO> docNames = new ArrayList<>();
        for (Therapist therapist : therapists) {
            TherapistDTO therapistDTO = new TherapistDTO(
                    therapist.getId(),
                    therapist.getName(),
                    therapist.getAvailability(),
                    therapist.getDescription()
            );
            docNames.add(therapistDTO);
        }
        return docNames;
    }
}
