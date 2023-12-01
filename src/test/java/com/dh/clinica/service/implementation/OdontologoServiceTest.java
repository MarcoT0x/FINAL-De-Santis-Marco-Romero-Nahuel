package com.dh.clinica.service.implementation;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.dto.OdontologoDTO;
import com.dh.clinica.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OdontologoServiceTest {


    private static IOdontologoService odontologoService;
    @Autowired
    public OdontologoServiceTest (IOdontologoService odontologoService){
        this.odontologoService=odontologoService;
    }


    @Test
    public void crearYBuscarPorId() throws Exception{
        OdontologoDTO odontologoDTO= new OdontologoDTO();
        odontologoDTO.setApellido("Rodriguez");
        odontologoDTO.setMatricula("AB45962");
        odontologoDTO.setNombre("Lautaro");

        OdontologoDTO odontologoDTO1= odontologoService.agregarOdontologo(odontologoDTO);

        assertTrue(odontologoService.buscarPorId(odontologoDTO1.getId()) != null);

    }

    @Test
    public void actualizarOdontologo() throws Exception{
        OdontologoDTO odontologoDTO= new OdontologoDTO();
        odontologoDTO.setApellido("Martinez");
        odontologoDTO.setMatricula("AC52792");
        odontologoDTO.setNombre("Marcos");
        odontologoService.agregarOdontologo(odontologoDTO);
        odontologoDTO.setNombre("Matias");
        OdontologoDTO odontologoDTO1= odontologoService.actualizarOdontologo(odontologoDTO);

        assertTrue(odontologoService.buscarPorId(odontologoDTO1.getId()).getNombre() == "Matias");
    }

    @Test
    public void listarOdontologos() {
        Set<OdontologoDTO> listadoOdontologos= odontologoService.buscarTodos();

        assertTrue(listadoOdontologos.size() != 0);
    }

    @Test
    public void eliminarOdontologo() throws Exception, ResourceNotFoundException {
        OdontologoDTO odontologoDTO= new OdontologoDTO();
        odontologoDTO.setApellido("Sanchez");
        odontologoDTO.setMatricula("AD56992");
        odontologoDTO.setNombre("Maiano");
        OdontologoDTO odontologoDTO1= odontologoService.agregarOdontologo(odontologoDTO);
        odontologoService.eliminarOdontologo(odontologoDTO1.getId());
        Exception exception =assertThrows(ResourceNotFoundException.class,() -> {
            odontologoService.buscarPorId(odontologoDTO1.getId());
        });

        assertNotNull(exception.getMessage());

    }

}