package com.ijse.orm.mentalhealthcenter.bo.custom;

import com.ijse.orm.mentalhealthcenter.bo.SuperBO;
import com.ijse.orm.mentalhealthcenter.dto.PatientDTO;

import java.sql.SQLException;
import java.util.List;

public interface PatientBO extends SuperBO {
    boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    List<PatientDTO> getALL() throws Exception;
    boolean deletePatient(String patientID) throws SQLException, ClassNotFoundException;
    String getNextPatientID();
}
