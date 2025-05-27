package com.registro.doctores.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.doctores.dto.DoctorDTO;
import com.registro.doctores.entidades.Doctor;
import com.registro.doctores.repositorio.DoctorRepositorio;

@Service
public class DoctorServicio {

    private final DoctorRepositorio doctorRepositorio;

    public DoctorServicio(DoctorRepositorio doctorRepositorio) {
        this.doctorRepositorio = doctorRepositorio;
    }

    // Listar todos los doctores
    public List<Doctor> consultarDoctores() {
        return doctorRepositorio.findAll();
    }

    // Registrar un doctor
    public Doctor registrarDoctor(Doctor doctor) {
        return doctorRepositorio.save(doctor);
    }

    // Consultar doctor por ID
    public Optional<Doctor> buscarPorId(Long iddoctor) {
        return doctorRepositorio.findByIddoctor(iddoctor);
    }

    // Consultas personalizadas
    public List<Doctor> buscarPorNombre(String nombrecompleto) {
        return doctorRepositorio.findByNombrecompletoContainingIgnoreCase(nombrecompleto);
    }

    public List<Doctor> buscarPorColegiado(String colegiado) {
        return doctorRepositorio.findByColegiado(colegiado);
    }

    public List<Doctor> buscarPorEspecialidad(String especialidad) {
        return doctorRepositorio.findByEspecialidadContainingIgnoreCase(especialidad);
    }

    public List<Doctor> buscarPorCentroHospitalario(String centrohospitalario) {
        return doctorRepositorio.findByCentrohospitalarioContainingIgnoreCase(centrohospitalario);
    }

    public List<Doctor> buscarPorDireccion(String direccion) {
        return doctorRepositorio.findByDireccionContainingIgnoreCase(direccion);
    }

    public List<Doctor> buscarPorEdad(int edad) {
        return doctorRepositorio.findByEdad(edad);
    }

    public List<Doctor> buscarPorFechaRegistro(LocalDate fecharegistro) {
        return doctorRepositorio.findByFecharegistro(fecharegistro);
    }

    // Consultar solo doctores activos
    public List<Doctor> buscarPorEstadoActivo() {
        return doctorRepositorio.findByEstado("ACTIVO");
    }

    // Consultar solo doctores inactivos
    public List<Doctor> buscarPorEstadoInactivo() {
        return doctorRepositorio.findByEstado("INACTIVO");
    }

    // Actualizar datos específicos
    @Transactional
    public void actualizarDireccion(Long iddoctor, String direccion) {
        doctorRepositorio.actualizarDireccion(iddoctor, direccion);
    }

    @Transactional
    public void actualizarCentroHospitalario(Long iddoctor, String centrohospitalario) {
        doctorRepositorio.actualizarCentroHospitalario(iddoctor, centrohospitalario);
    }

    @Transactional
    public void actualizarEspecialidad(Long iddoctor, String especialidad) {
        doctorRepositorio.actualizarEspecialidad(iddoctor, especialidad);
    }

    @Transactional
    public void actualizarColegiado(Long iddoctor, String colegiado) {
        doctorRepositorio.actualizarColegiado(iddoctor, colegiado);
    }

    @Transactional
    public void actualizarEdad(Long iddoctor, int edad) {
        doctorRepositorio.actualizarEdad(iddoctor, edad);
    }

    @Transactional
    public void actualizarNombre(Long iddoctor, String nombrecompleto) {
        doctorRepositorio.actualizarNombre(iddoctor, nombrecompleto);
    }

    @Transactional
    public void actualizarObservacion(Long iddoctor, String observacion) {
        doctorRepositorio.actualizarObservacion(iddoctor, observacion);
    }

    // Borrado lógico - cambiar estado a "INACTIVO"
    @Transactional
    public void borrarDoctor(Long iddoctor) {
        doctorRepositorio.borrarDoctor(iddoctor);
    }

    // Restaurar doctor - cambiar estado a "ACTIVO"
    @Transactional
    public void restaurarDoctor(Long iddoctor) {
        doctorRepositorio.restaurarDoctor(iddoctor);
    }

    // Actualizar Doctor pero no iddoctor ni fecharegistro ni estado
    @Transactional
    public void actualizarDoctor(Long iddoctor, DoctorDTO doctorDto) {
        Optional<Doctor> doctorOptional = doctorRepositorio.findById(iddoctor);

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();

            doctor.setColegiado(doctorDto.getColegiado());
            doctor.setNombrecompleto(doctorDto.getNombrecompleto());
            doctor.setEspecialidad(doctorDto.getEspecialidad());
            doctor.setDireccion(doctorDto.getDireccion());
            doctor.setCentrohospitalario(doctorDto.getCentrohospitalario());
            doctor.setEdad(doctorDto.getEdad());
            doctor.setObservacion(doctorDto.getObservacion());

            doctorRepositorio.save(doctor);
        } else {
            throw new RuntimeException("Doctor no encontrado con ID: " + iddoctor);
        }
    }
}
