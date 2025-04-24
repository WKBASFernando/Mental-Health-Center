package com.ijse.orm.mentalhealthcenter.bo.custom;

import com.ijse.orm.mentalhealthcenter.bo.SuperBO;
import com.ijse.orm.mentalhealthcenter.dto.TherapistDTO;

import java.sql.SQLException;
import java.util.List;

public interface TherapistBO extends SuperBO {
    List<TherapistDTO> getALLTherapists() throws Exception;
    boolean saveTherapist(TherapistDTO therapistDTO) throws Exception;
    boolean updateTherapist(TherapistDTO therapistDTO);
    boolean deleteTherapist(String therapistID) throws SQLException, ClassNotFoundException;
    String getNextTherapyID();
    List<TherapistDTO>getTherapistsNames() throws Exception;
}
