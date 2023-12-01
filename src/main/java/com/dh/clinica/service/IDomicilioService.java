package com.dh.clinica.service;


import com.dh.clinica.model.dto.DomicilioDTO;

import java.util.Set;

public interface IDomicilioService {
    DomicilioDTO buscarPorId(Long id);
    Set<DomicilioDTO> buscarTodos();
    void eliminarDomicilio(Long id);
    DomicilioDTO actualizarDomicilio(DomicilioDTO domicilioDTO);
    DomicilioDTO agregarDomicilio(DomicilioDTO domicilioDTO);
}
