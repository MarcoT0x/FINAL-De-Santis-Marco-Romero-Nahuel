package com.dh.clinica.model.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;



@ToString
@Getter
@Entity
@Table( name = "turnos")
public class Turno {
    @Id
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Long id;
    private LocalDate fechaIngreso;


    @ManyToOne
    @JoinColumn(name ="odontologo_id",referencedColumnName = "id")
    private Odontologo odontologo;


    @ManyToOne
    @JoinColumn(name ="paciente_id",referencedColumnName = "id")
    private Paciente paciente;

    public Turno() {
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
