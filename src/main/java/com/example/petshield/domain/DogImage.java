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
@Table(name = "DogImage")
public class DogImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogImageId;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @Column(name = "image_url")
    private String imageUrl;

    // Getters and Setters
}
