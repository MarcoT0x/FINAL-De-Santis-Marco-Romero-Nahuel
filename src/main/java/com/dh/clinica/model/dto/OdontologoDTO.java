package com.dh.clinica.model.dto;


import lombok.Getter;


@Getter
public class OdontologoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
