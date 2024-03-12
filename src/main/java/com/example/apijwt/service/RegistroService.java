package com.example.apijwt.service;

import com.example.apijwt.entity.Plantacion;
import com.example.apijwt.entity.Registro;
import com.example.apijwt.entity.Sensor;
import com.example.apijwt.repository.PlantacionRepository;
import com.example.apijwt.repository.RegistroRepository;
import com.example.apijwt.repository.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository repository;

    @Autowired
    private SensorRepository sensorRepository;


    public List<Registro> findAll() {
        return this.repository.findAll();
    }

    public Optional<Registro> findById(Long id) {
        return this.repository.findById(id);
    }

    public Registro save(Registro registro, Long sensorId) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new EntityNotFoundException("Sensor no encontrado"));
        registro.setSensor(sensor);
        return this.repository.save(registro);
    };

    public void deleteById(Long id) { this.repository.deleteById(id);  }

}
