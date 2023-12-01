package com.dh.clinica.model.dto;

import com.dh.clinica.model.entity.Odontologo;
import com.dh.clinica.model.entity.Paciente;
import lombok.Getter;

import java.time.LocalDate;



@Getter
public class TurnoDTO {

    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fechaIngreso;

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
