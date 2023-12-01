package com.dh.clinica.controller;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.dto.PacienteDTO;
import com.dh.clinica.service.implementation.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins="*")
public class PacienteController {

    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }


    @PostMapping()
    public ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody PacienteDTO pacienteDTO) throws Exception{
        ResponseEntity<PacienteDTO> response = null;
        if (pacienteDTO.getId()==null && pacienteDTO.getDomicilio().getId()== null){
            response = ResponseEntity.ok(pacienteService.agregarPaciente(pacienteDTO));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id) throws ResourceNotFoundException{
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(pacienteDTO);
    }

    @PutMapping()
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO pacienteDTO) throws Exception{
        ResponseEntity<PacienteDTO> response = null;
        PacienteDTO pacienteDTO1= pacienteService.buscarPorId(pacienteDTO.getId());

        if (pacienteDTO.getId() != null && pacienteDTO.getDomicilio().getId() != null &&
                pacienteDTO1 != null &&
                pacienteDTO1.getDomicilio().getId() == pacienteDTO.getDomicilio().getId()) {
            response = ResponseEntity.ok(pacienteService.actualizarPaciente(pacienteDTO));
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
            pacienteService.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Paciente eliminado correctamente");
    }
    @GetMapping
    public ResponseEntity<Set<PacienteDTO>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
}
