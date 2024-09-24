package com.assignment.carmarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "car_profile_photo")
public class CarProfilePhotoEntity extends BaseColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_profile_id")
    private Long carProfileId;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "car_profile_id", foreignKey = @ForeignKey(name = "fk_car_profile_photo_car_profile_id"), insertable = false, updatable = false)
    private CarProfileEntity carProfile;
}
