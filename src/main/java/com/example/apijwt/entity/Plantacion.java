package com.example.apijwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plantacion {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincrement
    private Long id;

    private String nombre;

    private Double latitud;

    private Double longitud;

    @OneToMany(mappedBy = "plantacion", cascade = CascadeType.REMOVE,  orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Sensor> sensores = new ArrayList<>();


}
