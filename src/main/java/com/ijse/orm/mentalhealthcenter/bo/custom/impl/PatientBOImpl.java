package com.ijse.orm.mentalhealthcenter.bo.custom.impl;

import com.ijse.orm.mentalhealthcenter.bo.custom.PatientBO;
import com.ijse.orm.mentalhealthcenter.config.FactoryConfiguration;
import com.ijse.orm.mentalhealthcenter.dao.DAOFactory;
import com.ijse.orm.mentalhealthcenter.dao.DAOType;
import com.ijse.orm.mentalhealthcenter.dao.custom.PatientDAO;
import com.ijse.orm.mentalhealthcenter.dto.PatientDTO;
import com.ijse.orm.mentalhealthcenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOType.PATIENT);
//    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = new Patient();
            patient.setId(patientDTO.getId());
            patient.setName(patientDTO.getName());
            patient.setAge(patientDTO.getAge());
            patient.setGender(patientDTO.getGender());
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
            patient.setAddress(patientDTO.getAddress());
            patient.setEmail(patientDTO.getEmail());

            boolean isUpdated = patientDAO.update(patient,session);
            if (isUpdated) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Update failed");
        }finally {
            session.close();
        }
    }
    @Override
    public boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = new Patient();
            patient.setId(patientDTO.getId());
            patient.setName(patientDTO.getName());
            patient.setAge(patientDTO.getAge());
            patient.setGender(patientDTO.getGender());
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
            patient.setAddress(patientDTO.getAddress());
            patient.setEmail(patientDTO.getEmail());

            boolean isUpdated = patientDAO.save(patient,session);
            if (isUpdated) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Update failed");
        }finally {
            session.close();
        }
    }
    @Override
    public List<PatientDTO> getALL() throws Exception {
        List<Patient> patients = patientDAO.getAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getPhoneNumber(),
                    patient.getAddress(),
                    patient.getEmail()
            ));
        }
        return patientDTOS;
    }

    @Override
    public boolean deletePatient(String patientID) throws SQLException, ClassNotFoundException {
        try{
            return patientDAO.deleteByPk(patientID);
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("Delete failed");
        }
    }
    @Override
    public String getNextPatientID() {
        Optional<String> lastPkOptional = patientDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("P", "")) + 1;  // Extract number and increment
            return String.format("P%03d", nextId);
        } else {
            return "P001";
        }
    }
}
