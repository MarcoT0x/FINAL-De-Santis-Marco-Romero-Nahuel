package com.dh.clinica.service.implementation;


import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.dto.OdontologoDTO;
import com.dh.clinica.model.entity.Odontologo;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository repository;
    private ObjectMapper mapper;


    @Autowired
    public void setRepository(IOdontologoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) { this.mapper = mapper; }

    @Override
    public OdontologoDTO buscarPorId(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologo = repository.findById(id);
        OdontologoDTO odontologoDTO = null;

        if(odontologo.isPresent()) {
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        }else {
            throw new ResourceNotFoundException("Odontólogo con id: "+id+", no encontrado.");
        }
        return odontologoDTO;
    }

    @Override
    public Set<OdontologoDTO> buscarTodos() {
        Set<OdontologoDTO> odontologoDTO = new HashSet<>();

        for(Odontologo odontologo : repository.findAll()) {
            odontologoDTO.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        }

        return odontologoDTO;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologo = repository.findById(id);
        if (odontologo.isPresent())
        repository.deleteById(id);
        else throw new ResourceNotFoundException("Odontólogo con id: "+id+", no encontrado.");
    }

    @Override
    public OdontologoDTO actualizarOdontologo(OdontologoDTO odontologoDTO) throws Exception{
        return agregarOdontologo(odontologoDTO);
    }

    @Override
    public OdontologoDTO agregarOdontologo(OdontologoDTO odontologoDTO) throws Exception {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);

        if (odontologo.getApellido() ==null || odontologo.getNombre()==null || odontologo.getMatricula()==null){
            throw new Exception();
        }else {
            odontologo =repository.save(odontologo);
        }

        return mapper.convertValue(odontologo, OdontologoDTO.class);
    }
}
