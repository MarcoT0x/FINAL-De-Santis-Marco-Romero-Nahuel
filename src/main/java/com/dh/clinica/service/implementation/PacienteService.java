package com.dh.clinica.service.implementation;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.dto.PacienteDTO;
import com.dh.clinica.model.entity.Paciente;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository repository;
    private ObjectMapper mapper;




    @Autowired
    public void PacienteService(IPacienteRepository repository,ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public PacienteDTO buscarPorId(Long id) throws ResourceNotFoundException{
        Optional<Paciente> paciente = repository.findById(id);
        PacienteDTO pacienteDTO = null;
        if(paciente.isPresent()) {
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }else {
            throw new ResourceNotFoundException("Paciente con id: "+id+", no encontrado.");
        }
        return pacienteDTO;
    }

    @Override
    public Set<PacienteDTO> buscarTodos() {
        Set<PacienteDTO> pacienteDTO = new HashSet<>();
        for(Paciente paciente : repository.findAll()) {
            pacienteDTO.add(mapper.convertValue(paciente, PacienteDTO.class));
        }
        return pacienteDTO;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isPresent())
            repository.deleteById(id);
        else throw new ResourceNotFoundException("Paciente con id: "+id+", no encontrado.");
    }


    @Override
    public PacienteDTO actualizarPaciente(PacienteDTO pacienteDTO) throws Exception{
        return agregarPaciente(pacienteDTO);
    }


    @Override
    public PacienteDTO agregarPaciente(PacienteDTO pacienteDTO) throws Exception{
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);

        if (paciente.getDomicilio().getCalle() ==null ||
                paciente.getDomicilio().getLocalidad() ==null ||
                paciente.getDomicilio().getNumero() ==null ||
                paciente.getDomicilio().getProvincia() ==null ||
                paciente.getFechaIngreso()==null ||
                paciente.getNombre()== null ||
                paciente.getApellido()==null ||
                paciente.getDni()==null){
            throw new Exception();
        }else {
            paciente=repository.save(paciente);
        }

        return mapper.convertValue(paciente, PacienteDTO.class);
    }
}