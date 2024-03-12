package com.example.apijwt.service;

import com.example.apijwt.entity.Plantacion;
import com.example.apijwt.entity.Sensor;
import com.example.apijwt.repository.PlantacionRepository;
import com.example.apijwt.repository.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private PlantacionRepository plantacionRepository;

    public List<Sensor> findAll() {  return this.sensorRepository.findAll();  }

    public Optional<Sensor> findById(Long id) { return this.sensorRepository.findById(id);  }

    public Sensor save(Sensor sensor, Long plantacionId) {
        Objects.requireNonNull(plantacionId);
        Plantacion plantacion = plantacionRepository.findById(plantacionId)
                .orElseThrow(() -> new EntityNotFoundException("Plantacion no encontrada"));
        sensor.setPlantacion(plantacion);
        return this.sensorRepository.save(sensor);
    };

    public void deleteById(Long id) { this.sensorRepository.deleteById(id); }
}
