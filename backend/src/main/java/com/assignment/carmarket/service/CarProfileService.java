package com.assignment.carmarket.service;

import com.assignment.carmarket.dto.ReqCreateCarProfileDTO;
import com.assignment.carmarket.dto.ReqFilterCarProfileDTO;
import com.assignment.carmarket.entity.CarProfileEntity;
import com.assignment.carmarket.entity.CarProfilePhotoEntity;
import com.assignment.carmarket.repository.CarProfileRepository;
import com.assignment.carmarket.utils.EncryptionUtils;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarProfileService {
    @Autowired
    private CarProfileRepository carProfileRepository;

    public Page<CarProfileEntity> getAllCarProfile(ReqFilterCarProfileDTO filter) throws Exception {
        Specification<CarProfileEntity> specification = filterCarProfile(filter);
        Sort sort = sortBy(filter);
        int pageNo = filter.getPageNo() != null ? filter.getPageNo() : 1;
        int pageSize = filter.getPageSize() != null ? filter.getPageSize() : 20;
        int countPage = pageNo - 1;
        Pageable pageable = PageRequest.of(countPage, pageSize, sort);
        Page<CarProfileEntity> carProfileEntityPage = this.carProfileRepository.findAll(specification, pageable);

        List<CarProfileEntity> carProfileEntities = carProfileEntityPage.getContent();
        for (CarProfileEntity carProfile : carProfileEntities) {
            if (carProfile.getCarProfilePhotos() != null && !carProfile.getCarProfilePhotos().isEmpty()) {
                for (CarProfilePhotoEntity carProfilePhoto : carProfile.getCarProfilePhotos()) {
                    if (StringUtils.isNotEmpty(carProfilePhoto.getImage())) {
                        carProfilePhoto.setImage(EncryptionUtils.decrypt(carProfilePhoto.getImage()));
                    }
                }
            }
        }
        return carProfileEntityPage;
    }

    private Sort sortBy(ReqFilterCarProfileDTO filter) {
        String sortBy = filter.getSortBy() != null ? filter.getSortBy() : "";
        return switch (sortBy) {
            case "priceAsc" -> Sort.by("price").ascending();
            case "priceDesc" -> Sort.by("isActive").descending();
            case "nameAsc" -> Sort.by("name").ascending();
            case "nameDesc" -> Sort.by("name").descending();
            case "createdAtAsc" -> Sort.by("createdAt").ascending();
            default -> Sort.by("updatedAt").descending();
        };
    }

    private Specification<CarProfileEntity> filterCarProfile(ReqFilterCarProfileDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getUserAccountId() != null && filter.getUserAccountId() > 0) {
                predicates.add(cb.equal(root.get("userAccountId"), filter.getUserAccountId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public CarProfileEntity getCarProfileById(Long id) throws Exception {
        CarProfileEntity carProfile = this.carProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundHandler("Not found car profile id : " + id));
        if (carProfile.getCarProfilePhotos() != null && !carProfile.getCarProfilePhotos().isEmpty()) {
            for(CarProfilePhotoEntity carProfilePhoto : carProfile.getCarProfilePhotos()) {
                carProfilePhoto.setImage(EncryptionUtils.decrypt(carProfilePhoto.getImage()));
            }
        }
        return carProfile;
    }

    public CarProfileEntity createCarProfile(ReqCreateCarProfileDTO dto) {
        CarProfileEntity carProfile = new CarProfileEntity();
        carProfile.setStatus("WAITING_FOR_SALE");
        BeanUtils.copyProperties(dto, carProfile);
        this.carProfileRepository.save(carProfile);
        return carProfile;
    }

    public CarProfileEntity updateCarProfile(Long id, ReqCreateCarProfileDTO dto) throws NotFoundHandler, BadRequestHandler {
        if (dto.getUserAccountId() == null) {
            throw new BadRequestHandler("required userAccountId.");
        }
        CarProfileEntity carProfile = this.carProfileRepository.findById(id).orElseThrow(() -> new NotFoundHandler("Not found car profile id : " + id));
        checkFieldUpdate(dto, carProfile);
        this.carProfileRepository.save(carProfile);
        return carProfile;
    }

    private void checkFieldUpdate(ReqCreateCarProfileDTO dto, CarProfileEntity carProfile) {
        if (StringUtils.isNotEmpty(dto.getCarBrand()))
            carProfile.setCarBrand(dto.getCarBrand());
        if (StringUtils.isNotEmpty(dto.getCarModel()))
            carProfile.setCarModel(dto.getCarModel());
        if (StringUtils.isNotEmpty(dto.getCarSubModel()))
            carProfile.setCarModel(dto.getCarSubModel());
        if (StringUtils.isNotEmpty(dto.getCarType()))
            carProfile.setCarModel(dto.getCarType());
        if (StringUtils.isNotEmpty(dto.getCarYear()))
            carProfile.setCarModel(dto.getCarYear());
        if (StringUtils.isNotEmpty(dto.getCarGear()))
            carProfile.setCarModel(dto.getCarGear());
        if (carProfile.getMileage() != null)
            carProfile.setMileage(dto.getMileage());
        if (carProfile.getCarScore() != null)
            carProfile.setCarScore(dto.getCarScore());
        if (carProfile.getCarPrice() != null)
            carProfile.setCarPrice(dto.getCarPrice());
    }
}
