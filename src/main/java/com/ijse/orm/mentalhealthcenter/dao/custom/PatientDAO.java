package com.ijse.orm.mentalhealthcenter.dao.custom;

import com.ijse.orm.mentalhealthcenter.dao.CrudDAO;
import com.ijse.orm.mentalhealthcenter.entity.Patient;

import java.util.List;

public interface PatientDAO extends CrudDAO<Patient, String> {
    List<Patient> searchPatientName(String searchByName);
    String search(String patientName);
}
