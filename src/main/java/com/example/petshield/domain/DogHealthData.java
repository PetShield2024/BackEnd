package com.example.petshield.domain;

import com.example.petshield.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "dog_health_data")
public class DogHealthData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

    private Float temperature;

    private Integer heartRate;

    private Integer stepCount;

    private Float distanceTraveled;

    @Column(nullable = false)
    private String activityLevel;
}
