package com.bit.springApp.business.abstracts;

import java.util.List;

import com.bit.springApp.dto.CarDefectDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface CarDefectService {

    List<CarDefectDTO> getCarDefects();
}
