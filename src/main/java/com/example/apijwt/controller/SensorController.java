package com.example.apijwt.controller;

import com.example.apijwt.entity.Plantacion;
import com.example.apijwt.entity.Sensor;
import com.example.apijwt.service.PlantacionService;
import com.example.apijwt.service.SensorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:9000")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/sensores")
    public ResponseEntity<List<Sensor>> findAll() {
        List<Sensor> sensores = this.sensorService.findAll();
        if (sensores.isEmpty())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( sensores );
    }

    @GetMapping("/sensores/{sensorId}")
    public ResponseEntity<Sensor> findBydId(@PathVariable Long sensorId) {
        Optional<Sensor> sensor = this.sensorService.findById(sensorId);
        if (!sensor.isPresent())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( sensor.get() );
    }

    //Inserta un sensor en una plantación, le pasamos en la ruta el id de plantación
    //http://localhost:8080/sensores/1 y Json con la info del sensor. Insertaría el sensor con la plantación 1
    @PostMapping("/sensores/{plantacionId}")
    public ResponseEntity<?> create(@RequestBody Sensor sensor, @PathVariable Long plantacionId) {
        try {
            this.sensorService.save(sensor, plantacionId);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>("Plantacion no encontrada con ID: " + plantacionId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(sensor);
    }

    @DeleteMapping("/sensores/{sensorId}")
    public ResponseEntity<Object> delete(@PathVariable Long sensorId) {
        return this.sensorService.findById(sensorId)
                .map( m -> {
                    System.out.println("Borrando sensor " + sensorId);
                    this.sensorService.deleteById(sensorId);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
