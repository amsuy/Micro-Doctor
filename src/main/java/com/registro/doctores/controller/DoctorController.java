package com.registro.doctores.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.doctores.dto.DoctorDTO;
import com.registro.doctores.entidades.Doctor;
import com.registro.doctores.servicio.DoctorServicio;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorServicio doctorServicio;

    public DoctorController(DoctorServicio doctorServicio) {
        this.doctorServicio = doctorServicio;
    }

    // Listar todos los doctores
    @GetMapping("/consultar")
    public ResponseEntity<List<Doctor>> listarDoctores() {
        return ResponseEntity.ok(doctorServicio.consultarDoctores());
    }

    // Registrar un doctor
    @PostMapping("/ingresar")
    public ResponseEntity<Doctor> registrarDoctor(@RequestBody DoctorDTO doctorDto) {
        Doctor doctor = new Doctor(
                null,
                doctorDto.getColegiado(),
                doctorDto.getNombrecompleto(),
                doctorDto.getEspecialidad(),
                LocalDate.now(), // Se asigna automáticamente la fecha actual
                doctorDto.getDireccion(),
                doctorDto.getCentrohospitalario(),
                doctorDto.getEdad(),
                doctorDto.getObservacion(),
                "ACTIVO" // Estado siempre será ACTIVO al registrar
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorServicio.registrarDoctor(doctor));
    }

    // Consultar por ID
    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<Optional<Doctor>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(doctorServicio.buscarPorId(id));
    }

    // Consultar por nombre
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Doctor>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(doctorServicio.buscarPorNombre(nombre));
    }

    // Consultar por colegiado
    @GetMapping("/buscar/colegiado/{colegiado}")
    public ResponseEntity<List<Doctor>> buscarPorColegiado(@PathVariable String colegiado) {
        return ResponseEntity.ok(doctorServicio.buscarPorColegiado(colegiado));
    }

    // Consultar por especialidad
    @GetMapping("/buscar/especialidad/{especialidad}")
    public ResponseEntity<List<Doctor>> buscarPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(doctorServicio.buscarPorEspecialidad(especialidad));
    }

    // Consultar por dirección
    @GetMapping("/buscar/direccion/{direccion}")
    public ResponseEntity<List<Doctor>> buscarPorDireccion(@PathVariable String direccion) {
        return ResponseEntity.ok(doctorServicio.buscarPorDireccion(direccion));
    }

    // Consultar por centro hospitalario
    @GetMapping("/buscar/centrohospitalario/{centro}")
    public ResponseEntity<List<Doctor>> buscarPorCentroHospitalario(@PathVariable String centro) {
        return ResponseEntity.ok(doctorServicio.buscarPorCentroHospitalario(centro));
    }

    // Consultar por edad
    @GetMapping("/buscar/edad/{edad}")
    public ResponseEntity<List<Doctor>> buscarPorEdad(@PathVariable int edad) {
        return ResponseEntity.ok(doctorServicio.buscarPorEdad(edad));
    }

    // Consultar por fecha de registro
    @GetMapping("/buscar/fecha/{fecha}")
    public ResponseEntity<List<Doctor>> buscarPorFechaRegistro(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(doctorServicio.buscarPorFechaRegistro(fecha));
    }

    // Consultar por estado ACTIVO
    @GetMapping("/buscar/estado/activo")
    public ResponseEntity<List<Doctor>> buscarPorEstadoActivo() {
        return ResponseEntity.ok(doctorServicio.buscarPorEstadoActivo());
    }

    // Consultar por estado INACTIVO
    @GetMapping("/buscar/estado/inactivo")
    public ResponseEntity<List<Doctor>> buscarPorEstadoInactivo() {
        return ResponseEntity.ok(doctorServicio.buscarPorEstadoInactivo());
    }

    // Actualizar solo un campo específico
    @PutMapping("/actualizar/direccion/{id}/{direccion}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Long id, @PathVariable String direccion) {
        doctorServicio.actualizarDireccion(id, direccion);
        return ResponseEntity.ok("Dirección actualizada correctamente");
    }

    @PutMapping("/actualizar/centrohospitalario/{id}/{centro}")
    public ResponseEntity<?> actualizarCentroHospitalario(@PathVariable Long id, @PathVariable String centro) {
        doctorServicio.actualizarCentroHospitalario(id, centro);
        return ResponseEntity.ok("Centro hospitalario actualizado correctamente");
    }

    @PutMapping("/actualizar/especialidad/{id}/{especialidad}")
    public ResponseEntity<?> actualizarEspecialidad(@PathVariable Long id, @PathVariable String especialidad) {
        doctorServicio.actualizarEspecialidad(id, especialidad);
        return ResponseEntity.ok("Especialidad actualizada correctamente");
    }

    @PutMapping("/actualizar/colegiado/{id}/{colegiado}")
    public ResponseEntity<?> actualizarColegiado(@PathVariable Long id, @PathVariable String colegiado) {
        doctorServicio.actualizarColegiado(id, colegiado);
        return ResponseEntity.ok("Número de colegiado actualizado correctamente");
    }

    @PutMapping("/actualizar/edad/{id}/{edad}")
    public ResponseEntity<?> actualizarEdad(@PathVariable Long id, @PathVariable int edad) {
        doctorServicio.actualizarEdad(id, edad);
        return ResponseEntity.ok("Edad actualizada correctamente");
    }

    @PutMapping("/actualizar/nombre/{id}/{nombre}")
    public ResponseEntity<?> actualizarNombre(@PathVariable Long id, @PathVariable String nombre) {
        doctorServicio.actualizarNombre(id, nombre);
        return ResponseEntity.ok("Nombre actualizado correctamente");
    }

    @PutMapping("/actualizar/observacion/{id}/{observacion}")
    public ResponseEntity<?> actualizarObservacion(@PathVariable Long id, @PathVariable String observacion) {
        doctorServicio.actualizarObservacion(id, observacion);
        return ResponseEntity.ok("Observación actualizada correctamente");
    }

    // Borrado lógico
    @PutMapping("/borrar/{id}")
    public ResponseEntity<?> borrarDoctor(@PathVariable Long id) {
        doctorServicio.borrarDoctor(id);
        return ResponseEntity.ok("Doctor marcado como INACTIVO");
    }

    // Restaurar doctor
    @PutMapping("/restaurar/{id}")
    public ResponseEntity<?> restaurarDoctor(@PathVariable Long id) {
        doctorServicio.restaurarDoctor(id);
        return ResponseEntity.ok("Doctor restaurado a ACTIVO");
    }

    // Actualizar Doctor (excepto iddoctor y fecharegistro)
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDto) {
        doctorServicio.actualizarDoctor(id, doctorDto);
        return ResponseEntity.ok("Doctor actualizado correctamente");
    }

    // Método adicional específicamente diseñado para Feign Client (retorna DTO directo)

    @GetMapping("/buscar/resumen/{id}")
    public ResponseEntity<DoctorDTO> obtenerDoctorPorId(@PathVariable("id") Long id) {
        Optional<Doctor> doctorOptional = doctorServicio.buscarPorId(id);
    
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            DoctorDTO doctorDTO = new DoctorDTO(
                doctor.getColegiado(),
                doctor.getNombrecompleto(),
                doctor.getEspecialidad(),
                doctor.getDireccion(),
                doctor.getCentrohospitalario(),
                doctor.getEdad(),
                doctor.getObservacion()
            );
            return ResponseEntity.ok(doctorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
