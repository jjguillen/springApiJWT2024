package com.example.apijwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincrement
    private Long id;

    private String ubicacionDescrip;

    private Double latitud;

    private Double longitud;

    private LocalDate fechaInstalacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_plantacion", foreignKey = @ForeignKey(name = "fk_plantacion"))
    @JsonIgnore
    private Plantacion plantacion;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.REMOVE,  orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Registro> registros = new ArrayList<>();

}
