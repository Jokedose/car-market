package com.assignment.carmarket.service;

import com.assignment.carmarket.dto.ReqCarProfilePhotoDto;
import com.assignment.carmarket.entity.CarProfileEntity;
import com.assignment.carmarket.entity.CarProfilePhotoEntity;
import com.assignment.carmarket.repository.CarProfilePhotoRepository;
import com.assignment.carmarket.repository.CarProfileRepository;
import com.assignment.carmarket.utils.EncryptionUtils;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class CarProfilePhotoService {

    private final CarProfilePhotoRepository carProfilePhotoRepository;
    private final CarProfileRepository carProfileRepository;

    public CarProfilePhotoService(CarProfilePhotoRepository carProfilePhotoRepository,
                                  CarProfileRepository carProfileRepository) {
        this.carProfilePhotoRepository = carProfilePhotoRepository;
        this.carProfileRepository = carProfileRepository;
    }

    @Transactional
    public CarProfileEntity uploadCarProfilePhoto(ReqCarProfilePhotoDto dto) throws Exception {
        if (dto.getId() != null) {
            CarProfilePhotoEntity carProfilePhoto = this.carProfilePhotoRepository.findById(dto.getId()).orElseThrow(() -> new NotFoundHandler("Not fount car profile photo by id " + dto.getId()));
            byte[] encrypted = EncryptionUtils.encrypt(Base64.getDecoder().decode(dto.getImage()));
            String image = Base64.getEncoder().encodeToString(encrypted);
            carProfilePhoto.setImage(image);
            this.carProfilePhotoRepository.save(carProfilePhoto);
            return carProfileRepository.findById(dto.getCarProfileId()).orElse(null);
        }
        CarProfileEntity carProfile = carProfileRepository.findById(dto.getCarProfileId()).orElse(null);
        if (carProfile == null) {
            throw new NotFoundHandler("Not found car profile with id " + dto.getCarProfileId());
        }
        CarProfilePhotoEntity carProfilePhoto = !carProfile.getCarProfilePhotos().isEmpty()? carProfile.getCarProfilePhotos().getFirst() : null;
        byte[] encrypted = EncryptionUtils.encrypt(Base64.getDecoder().decode(dto.getImage()));
        String image = Base64.getEncoder().encodeToString(encrypted);
        if (carProfilePhoto == null) {
            carProfilePhoto = new CarProfilePhotoEntity();
            carProfilePhoto.setCarProfileId(dto.getCarProfileId());
            carProfilePhoto.setImage(image);
            carProfilePhoto.setCreatedBy(carProfile.getCreatedBy());
            carProfilePhoto.setUpdatedBy(carProfile.getCreatedBy());
        } else {
            carProfilePhoto.setImage(image);
            carProfilePhoto.setUpdatedBy(carProfile.getUpdatedBy());
        }
        this.carProfilePhotoRepository.save(carProfilePhoto);
        return carProfileRepository.findById(dto.getCarProfileId()).orElse(null);
    }
}
