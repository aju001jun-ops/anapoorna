package com.anapoornabackend.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "orphanages")
public class Orphanage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    private Integer childrenCount;

    @Column(length = 300)
    private String foodRequirement;

    private String imagePath;

    private String status;

    private LocalDateTime createdAt;

    // Default Constructor
    public Orphanage() {
    }

    // Parameterized Constructor
    public Orphanage(Long id, String name, String address, String email, String phone,
                     Double latitude, Double longitude, Integer childrenCount,
                     String foodRequirement, String imagePath,
                     String status, LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.childrenCount = childrenCount;
        this.foodRequirement = foodRequirement;
        this.imagePath = imagePath;
        this.status = status;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = "Pending";
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getFoodRequirement() {
        return foodRequirement;
    }

    public void setFoodRequirement(String foodRequirement) {
        this.foodRequirement = foodRequirement;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}