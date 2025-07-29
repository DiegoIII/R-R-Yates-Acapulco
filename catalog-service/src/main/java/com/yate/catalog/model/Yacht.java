package com.yate.catalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "yachts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Yacht {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre del yate es obligatorio")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @NotNull(message = "El precio por día es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerDay;
    
    @NotNull(message = "La capacidad es obligatoria")
    @Column(nullable = false)
    private Integer capacity;
    
    @Column(length = 100)
    private String location;
    
    @Column(length = 50)
    private String yachtType; // SAILING, MOTOR, CATAMARAN, etc.
    
    @Column
    private Double length; // Length in meters
    
    @Column
    private Integer cabins;
    
    @Column
    private Integer bathrooms;
    
    @Column
    private Integer crew;
    
    @ElementCollection
    @CollectionTable(name = "yacht_amenities", joinColumns = @JoinColumn(name = "yacht_id"))
    @Column(name = "amenity")
    private List<String> amenities;
    
    @ElementCollection
    @CollectionTable(name = "yacht_images", joinColumns = @JoinColumn(name = "yacht_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;
    
    @Column
    private Boolean available = true;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
