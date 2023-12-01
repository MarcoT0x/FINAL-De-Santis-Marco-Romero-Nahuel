package com.dh.clinica.service.implementation;


import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.dto.DomicilioDTO;
import com.dh.clinica.model.dto.OdontologoDTO;
import com.dh.clinica.model.dto.PacienteDTO;
import com.dh.clinica.model.dto.TurnoDTO;
import com.dh.clinica.model.entity.Domicilio;
import com.dh.clinica.model.entity.Odontologo;
import com.dh.clinica.model.entity.Paciente;
import com.dh.clinica.service.IOdontologoService;
import com.dh.clinica.service.IPacienteService;
import com.dh.clinica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class TurnoServiceTest {

    private static ITurnoService turnoService;
    private static IOdontologoService odontologoService;
    private static IPacienteService pacienteService;
    private static ObjectMapper mapper;
    @Autowired
    public void TurnoServiceTest(ITurnoService turnoService, IOdontologoService odontologoService, IPacienteService pacienteService, ObjectMapper mapper){
        this.turnoService= turnoService;
        this.odontologoService= odontologoService;
        this.pacienteService= pacienteService;
        this.mapper=mapper;
    }


    @Test
     public void buscarTodos() {
        Set<OdontologoDTO> listadoOdontologos= odontologoService.buscarTodos();

        assertTrue(listadoOdontologos.size() != 0);
    }

    @Test
     public void eliminarTurno() throws  Exception{
        DomicilioDTO domicilioDTO= new DomicilioDTO();
        domicilioDTO.setNumero("125");
        domicilioDTO.setProvincia("Catamaca");
        domicilioDTO.setLocalidad("Tinogasta");
        domicilioDTO.setCalle("Las Heras");

        PacienteDTO pacienteDTO= new PacienteDTO();
        pacienteDTO.setApellido("Juárez");
        pacienteDTO.setDni("27.241.991");
        pacienteDTO.setDomicilio(mapper.convertValue(domicilioDTO, Domicilio.class));
        pacienteDTO.setFechaIngreso(LocalDate.parse("2022-05-01"));
        pacienteDTO.setNombre("Marcela");

        PacienteDTO pacienteDTO1=pacienteService.agregarPaciente(pacienteDTO);

        OdontologoDTO odontologoDTO= new OdontologoDTO();
        odontologoDTO.setApellido("Ramirez");
        odontologoDTO.setMatricula("AD45824");
        odontologoDTO.setNombre("Francisco");

        OdontologoDTO odontologoDTO1= odontologoService.agregarOdontologo(odontologoDTO);

        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setOdontologo(mapper.convertValue(odontologoDTO1, Odontologo.class));
        turnoDTO.setPaciente(mapper.convertValue(pacienteDTO1, Paciente.class));
        turnoDTO.setFechaIngreso(LocalDate.parse("2022-10-01"));

        TurnoDTO turnoDTO1= turnoService.agregarTurno(turnoDTO);
        turnoService.eliminarTurno(turnoDTO1.getId());
        Exception exception =assertThrows(ResourceNotFoundException.class,() -> {
            turnoService.buscarPorId(turnoDTO1.getId());
        });

        assertNotNull(exception.getMessage());
    }

    @Test
     public void actualizarTurno() throws Exception {
        DomicilioDTO domicilioDTO= new DomicilioDTO();
        domicilioDTO.setNumero("368");
        domicilioDTO.setProvincia("Buenos Aires");
        domicilioDTO.setLocalidad("Bahía Blanca");
        domicilioDTO.setCalle("Manuel Belgrano");

        PacienteDTO pacienteDTO= new PacienteDTO();
        pacienteDTO.setApellido("Álvarez");
        pacienteDTO.setDni("27.241.991");
        pacienteDTO.setDomicilio(mapper.convertValue(domicilioDTO, Domicilio.class));
        pacienteDTO.setFechaIngreso(LocalDate.parse("2022-05-01"));
        pacienteDTO.setNombre("Marcela");

        PacienteDTO pacienteDTO1=pacienteService.agregarPaciente(pacienteDTO);

        OdontologoDTO odontologoDTO= new OdontologoDTO();
        odontologoDTO.setApellido("Andrade");
        odontologoDTO.setMatricula("AB47424");
        odontologoDTO.setNombre("Francisco");

        OdontologoDTO odontologoDTO1= odontologoService.agregarOdontologo(odontologoDTO);

        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setOdontologo(mapper.convertValue(odontologoDTO1, Odontologo.class));
        turnoDTO.setPaciente(mapper.convertValue(pacienteDTO1, Paciente.class));
        turnoDTO.setFechaIngreso(LocalDate.parse("2022-10-01"));


        TurnoDTO turnoDTO1= turnoService.agregarTurno(turnoDTO);
        turnoDTO1.setFechaIngreso(LocalDate.parse("2022-06-10"));

        TurnoDTO turnoDTO2= turnoService.actualizarTurno(turnoDTO1);

        Assertions.assertEquals( turnoService.buscarPorId(turnoDTO2.getId()).getFechaIngreso(), LocalDate.of(2022,06,10));
    }

    @Test
     public void agregarBuscarTurno() throws Exception {
        DomicilioDTO domicilioDTO= new DomicilioDTO();
        domicilioDTO.setNumero("358");
        domicilioDTO.setProvincia("San Juan");
        domicilioDTO.setLocalidad("San Juan");
        domicilioDTO.setCalle("Pueyrredón");

        PacienteDTO pacienteDTO= new PacienteDTO();
        pacienteDTO.setApellido("Gatica");
        pacienteDTO.setDni("38.241.991");
        pacienteDTO.setDomicilio(mapper.convertValue(domicilioDTO, Domicilio.class));
        pacienteDTO.setFechaIngreso(LocalDate.parse("2022-05-12"));
        pacienteDTO.setNombre("Manuel");

        PacienteDTO pacienteDTO1=pacienteService.agregarPaciente(pacienteDTO);

        OdontologoDTO odontologoDTO= new OdontologoDTO();
        odontologoDTO.setApellido("Migues");
        odontologoDTO.setMatricula("AC45662");
        odontologoDTO.setNombre("Florencia");

        OdontologoDTO odontologoDTO1= odontologoService.agregarOdontologo(odontologoDTO);

        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setOdontologo(mapper.convertValue(odontologoDTO1, Odontologo.class));
        turnoDTO.setPaciente(mapper.convertValue(pacienteDTO1, Paciente.class));
        turnoDTO.setFechaIngreso(LocalDate.parse("2022-08-13"));

        TurnoDTO turnoDTO1= turnoService.agregarTurno(turnoDTO);

        assertTrue(turnoService.buscarPorId(turnoDTO1.getId()) != null);


    }
}