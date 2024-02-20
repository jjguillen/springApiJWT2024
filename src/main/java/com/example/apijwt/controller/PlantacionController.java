package com.example.apijwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apijwt.service.PlantacionService;
import com.example.apijwt.entity.Plantacion;

import java.util.List;

@RestController
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
}
