package com.ijse.orm.mentalhealthcenter.bo.custom;

import com.ijse.orm.mentalhealthcenter.bo.SuperBO;
import com.ijse.orm.mentalhealthcenter.dto.TherapyProgramDTO;

import java.util.List;

public interface TherapyProgramBO extends SuperBO {
    boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean updateTPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean deleteTProgram(String therapyProgramID);
    List<TherapyProgramDTO> getALLTPrograms() throws Exception;
    String getNextProgramID();
    List<TherapyProgramDTO> getALL () throws Exception;
}
