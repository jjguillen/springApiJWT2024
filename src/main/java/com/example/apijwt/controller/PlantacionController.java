package com.example.apijwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apijwt.service.PlantacionService;
import com.example.apijwt.entity.Plantacion;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:9000")
public class PlantacionController {

    @Autowired
    private PlantacionService plantacionService;

    @GetMapping("/plantaciones")
    public ResponseEntity<List<Plantacion>> findAll() {
        List<Plantacion> plantaciones = this.plantacionService.findAll();
        if (plantaciones.isEmpty())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( plantaciones );
    }

    @GetMapping("/plantaciones/{plantacionId}")
    public ResponseEntity<Plantacion> findBydId(@PathVariable Long plantacionId) {
        Optional<Plantacion> plantacion = this.plantacionService.findById(plantacionId);
        if (!plantacion.isPresent())
            return ResponseEntity.notFound().build();  //Devuelve 404 si no hay nada

        return ResponseEntity.ok( plantacion.get() );
    }

    @PostMapping("/plantaciones")
    public ResponseEntity<Plantacion> create(@RequestBody Plantacion plantacion) {
        this.plantacionService.save(plantacion);
        return ResponseEntity.ok(plantacion);
    }

    @DeleteMapping("/plantaciones/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return this.plantacionService.findById(id)
                .map( m -> {
                    this.plantacionService.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/plantaciones")
    public ResponseEntity<Plantacion> update(@RequestBody Plantacion plantacion) {
        this.plantacionService.save(plantacion);
        return ResponseEntity.ok(plantacion);
    }
}
