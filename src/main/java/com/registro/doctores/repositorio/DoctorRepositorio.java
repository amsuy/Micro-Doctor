package com.registro.doctores.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.registro.doctores.entidades.Doctor;

@Repository
public interface DoctorRepositorio extends JpaRepository<Doctor, Long> {

    // Consultar todos los doctores
    @SuppressWarnings("null")
    @Nullable
    List<Doctor> findAll();

    // Consultar por ID de doctor
    Optional<Doctor> findByIddoctor(Long iddoctor);

    // Consultar por nombre completo
    List<Doctor> findByNombrecompletoContainingIgnoreCase(String nombrecompleto);

    // Consultar por colegiado
    List<Doctor> findByColegiado(String colegiado);

    // Consultar por especialidad
    List<Doctor> findByEspecialidadContainingIgnoreCase(String especialidad);

    // Consultar por centro hospitalario
    List<Doctor> findByCentrohospitalarioContainingIgnoreCase(String centrohospitalario);

    // Consultar por dirección
    List<Doctor> findByDireccionContainingIgnoreCase(String direccion);

    // Consultar por edad
    List<Doctor> findByEdad(int edad);

    // Consultar por fecha de registro
    List<Doctor> findByFecharegistro(LocalDate fecharegistro);

    // Actualizar todos los datos excepto iddoctor y fecharegistro
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.colegiado = :colegiado, d.nombrecompleto = :nombre, d.especialidad = :especialidad, d.direccion = :direccion, d.centrohospitalario = :centrohospitalario, d.edad = :edad, d.observacion = :observacion WHERE d.iddoctor = :iddoctor")
    void actualizarDoctor(Long iddoctor, String colegiado, String nombre, String especialidad, String direccion,
            String centrohospitalario, int edad, String observacion);

    // Actualizar solo la dirección
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.direccion = :direccion WHERE d.iddoctor = :iddoctor")
    void actualizarDireccion(Long iddoctor, String direccion);

    // Actualizar solo el centro hospitalario
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.centrohospitalario = :centrohospitalario WHERE d.iddoctor = :iddoctor")
    void actualizarCentroHospitalario(Long iddoctor, String centrohospitalario);

    // Actualizar solo la especialidad
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.especialidad = :especialidad WHERE d.iddoctor = :iddoctor")
    void actualizarEspecialidad(Long iddoctor, String especialidad);

    // Actualizar solo el colegiado
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.colegiado = :colegiado WHERE d.iddoctor = :iddoctor")
    void actualizarColegiado(Long iddoctor, String colegiado);

    // Actualizar solo la edad
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.edad = :edad WHERE d.iddoctor = :iddoctor")
    void actualizarEdad(Long iddoctor, int edad);

    // Actualizar solo el nombre
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.nombrecompleto = :nombre WHERE d.iddoctor = :iddoctor")
    void actualizarNombre(Long iddoctor, String nombre);

    // Actualizar solo la observación
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.observacion = :observacion WHERE d.iddoctor = :iddoctor")
    void actualizarObservacion(Long iddoctor, String observacion);

    // Consultar por estado "ACTIVO"
    List<Doctor> findByEstado(String estado);

    // Consultar por estado "INACTIVO"
    List<Doctor> findByEstadoNot(String estado);

    // Borrado lógico - cambiar estado a "INACTIVO"
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.estado = 'INACTIVO' WHERE d.iddoctor = :iddoctor")
    void borrarDoctor(Long iddoctor);

    // Restaurar doctor - cambiar estado a "ACTIVO"
    @Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.estado = 'ACTIVO' WHERE d.iddoctor = :iddoctor")
    void restaurarDoctor(Long iddoctor);

}
