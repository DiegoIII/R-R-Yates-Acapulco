package com.yate.catalog.repository;

import com.yate.catalog.model.Yacht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface YachtRepository extends JpaRepository<Yacht, Long> {
    
    // Buscar yates disponibles
    List<Yacht> findByAvailableTrue();
    
    // Buscar por tipo de yate
    List<Yacht> findByYachtTypeAndAvailableTrue(String yachtType);
    
    // Buscar por ubicación
    List<Yacht> findByLocationContainingIgnoreCaseAndAvailableTrue(String location);
    
    // Buscar por capacidad mínima
    List<Yacht> findByCapacityGreaterThanEqualAndAvailableTrue(Integer capacity);
    
    // Buscar por rango de precio
    List<Yacht> findByPricePerDayBetweenAndAvailableTrue(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Buscar por nombre (búsqueda parcial)
    List<Yacht> findByNameContainingIgnoreCaseAndAvailableTrue(String name);
    
    // Búsqueda combinada por filtros
    @Query("SELECT y FROM Yacht y WHERE " +
           "(:location IS NULL OR LOWER(y.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:yachtType IS NULL OR y.yachtType = :yachtType) AND " +
           "(:minCapacity IS NULL OR y.capacity >= :minCapacity) AND " +
           "(:maxPrice IS NULL OR y.pricePerDay <= :maxPrice) AND " +
           "y.available = true")
    List<Yacht> findYachtsWithFilters(
        @Param("location") String location,
        @Param("yachtType") String yachtType,
        @Param("minCapacity") Integer minCapacity,
        @Param("maxPrice") BigDecimal maxPrice
    );
    
    // Contar yates disponibles por tipo
    @Query("SELECT y.yachtType, COUNT(y) FROM Yacht y WHERE y.available = true GROUP BY y.yachtType")
    List<Object[]> countAvailableYachtsByType();
}
