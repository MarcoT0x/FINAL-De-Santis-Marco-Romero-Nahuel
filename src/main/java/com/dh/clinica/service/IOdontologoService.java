package com.dh.clinica.service;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.dto.OdontologoDTO;

import java.util.Set;

public interface IOdontologoService {

    OdontologoDTO buscarPorId(Long id) throws ResourceNotFoundException;
    Set<OdontologoDTO> buscarTodos();
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
    OdontologoDTO actualizarOdontologo(OdontologoDTO odontologoDTO) throws Exception;
    OdontologoDTO agregarOdontologo(OdontologoDTO odontologoDTO) throws Exception ;
}