package com.example.apijwt.repository;

import com.example.apijwt.entity.Plantacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantacionRepository extends JpaRepository<Plantacion, Long> {
}
