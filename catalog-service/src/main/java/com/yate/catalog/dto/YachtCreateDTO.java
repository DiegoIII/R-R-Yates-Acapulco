package com.yate.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class YachtCreateDTO {
    
    @NotBlank(message = "El nombre del yate es obligatorio")
    private String name;
    
    @NotBlank(message = "La descripción es obligatoria")
    private String description;
    
    @NotNull(message = "El precio por día es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal pricePerDay;
    
    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacity;
    
    private String location;
    private String yachtType;
    private Double length;
    private Integer cabins;
    private Integer bathrooms;
    private Integer crew;
    private List<String> amenities;
    private List<String> imageUrls;
}
