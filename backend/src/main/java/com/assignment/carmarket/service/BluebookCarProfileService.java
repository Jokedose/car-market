package com.assignment.carmarket.service;

import com.assignment.carmarket.dto.ReqBluebookCarProfileDto;
import com.assignment.carmarket.entity.BluebookCarProfileEntity;
import com.assignment.carmarket.repository.BluebookCarProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BluebookCarProfileService {

    private final BluebookCarProfileRepository bluebookCarProfileRepository;

    public BluebookCarProfileService(BluebookCarProfileRepository bluebookCarProfileRepository) {
        this.bluebookCarProfileRepository = bluebookCarProfileRepository;
    }

    public List<String> getCarBrand() {
        return bluebookCarProfileRepository.findDistinctCarBranch();
    }

    public List<String> getCarModel(ReqBluebookCarProfileDto dto) {
        return bluebookCarProfileRepository.findDistinctCarModel(dto.getCarBrand());
    }

    public List<String> getCarYear(ReqBluebookCarProfileDto dto) {
        return bluebookCarProfileRepository.findDistinctCarYear(dto.getCarBrand(), dto.getCarModel());
    }

    public List<String> getCarSubModel(ReqBluebookCarProfileDto dto) {
        return bluebookCarProfileRepository.findDistinctSubModel(dto.getCarBrand(), dto.getCarModel(), dto.getCarYear());
    }

    public List<BluebookCarProfileEntity> getCarGear(ReqBluebookCarProfileDto dto) {
        return bluebookCarProfileRepository.findDistinctCarGear(dto.getCarBrand(), dto.getCarModel(), dto.getCarYear(), dto.getCarSubModel());
    }
}
