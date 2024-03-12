package com.example.apijwt.controller;

import com.example.apijwt.entity.Plantacion;
import com.example.apijwt.entity.Registro;
import com.example.apijwt.entity.Sensor;
import com.example.apijwt.service.PlantacionService;
import com.example.apijwt.service.RegistroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:9000")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("/registros")
    public ResponseEntity<List<Registro>> findAll() {
        List<Registro> registros = this.registroService.findAll();
        if (registros.isEmpty())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( registros );
    }

    @GetMapping("/registros/{registroId}")
    public ResponseEntity<Registro> findBydId(@PathVariable Long registroId) {
        Optional<Registro> registro = this.registroService.findById(registroId);
        if (!registro.isPresent())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( registro.get() );
    }

    @PostMapping("/registros/{sensorId}")
    public ResponseEntity<?> create(@RequestBody Registro registro, @PathVariable Long sensorId) {
        try {
            this.registroService.save(registro, sensorId);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>("Sensor no encontrado con ID: " + sensorId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(registro);
    }

    @DeleteMapping("/registros/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return this.registroService.findById(id)
                .map( m -> {
                    this.registroService.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/registros")
    public ResponseEntity<Registro> update(@RequestBody Registro registro) {
        //Me falta pasarle el id del sensor, pero no lo tiene el registro ???
        this.registroService.save(registro, -1l);
        return ResponseEntity.ok(registro);
    }
}
