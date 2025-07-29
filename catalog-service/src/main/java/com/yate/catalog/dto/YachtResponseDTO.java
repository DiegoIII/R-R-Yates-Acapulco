package com.yate.catalog.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class YachtResponseDTO {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal pricePerDay;
    private Integer capacity;
    private String location;
    private String yachtType;
    private Double length;
    private Integer cabins;
    private Integer bathrooms;
    private Integer crew;
    private List<String> amenities;
    private List<String> imageUrls;
    private Boolean available;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
