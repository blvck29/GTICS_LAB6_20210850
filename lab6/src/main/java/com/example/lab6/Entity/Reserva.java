package com.example.lab6.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "inicio")
    private Date inicio;
    @Column(name = "fin")
    private Date fin;

    @ManyToOne
    @JoinColumn(name = "alumno")
    private Usuario alumno;

    @ManyToOne
    @JoinColumn(name = "dispositivo")
    private Dispositivo dispositivo;

    @ManyToOne
    @JoinColumn(name = "profesor")
    private Usuario profesor;

}
