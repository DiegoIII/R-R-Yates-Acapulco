package com.yate.service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long yachtId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean paid;

    // Getters y Setters
}
