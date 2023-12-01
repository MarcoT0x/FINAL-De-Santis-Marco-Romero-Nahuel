package com.dh.clinica.controller;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.dto.TurnoDTO;
import com.dh.clinica.service.implementation.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins="*")
public class TurnoController {

    private TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService){
        this.turnoService = turnoService;
    }


    @PostMapping()
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws Exception {
        ResponseEntity<TurnoDTO> response = null;
        if (turnoDTO.getId()==null && turnoDTO.getPaciente().getDomicilio().getId()!=null){
            response = ResponseEntity.ok(turnoService.agregarTurno(turnoDTO));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscar(@PathVariable Long id) throws ResourceNotFoundException{
        TurnoDTO turnoDTO = turnoService.buscarPorId(id);
        return ResponseEntity.ok(turnoDTO);
    }

    @PutMapping()
    public ResponseEntity<TurnoDTO> actualizar(@RequestBody TurnoDTO turnoDTO) throws Exception{
        ResponseEntity<TurnoDTO> response = null;
        TurnoDTO turnoDTO1= turnoService.buscarPorId(turnoDTO.getId());
        if (turnoDTO.getId() != null &&
                turnoDTO.getPaciente().getId() != null &&
                turnoDTO.getPaciente().getDomicilio().getId() != null &&
                turnoDTO.getOdontologo().getId() != null &&
                turnoDTO1 != null &&
                turnoDTO1.getPaciente().getDomicilio().getId() == turnoDTO.getPaciente().getDomicilio().getId() &&
                turnoDTO1.getPaciente().getId() == turnoDTO.getPaciente().getId() &&
                turnoDTO1.getOdontologo().getId() == turnoDTO.getOdontologo().getId())
            response = ResponseEntity.ok(turnoService.actualizarTurno(turnoDTO));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado correctamente");
    }

    @GetMapping
    public ResponseEntity<Set<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

}
