package com.registro.doctores.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddoctor")
    private Long iddoctor;
    
    @Column(name = "colegiado", length = 50, nullable = false)
    private String colegiado;
    
    @Column(name = "nombrecompleto", length = 500, nullable = false)
    private String nombrecompleto;
    
    @Column(name = "especialidad", length = 255, nullable = false)
    private String especialidad;
    
    @Column(name = "fecharegistro", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate fecharegistro;
    
    @Column(name = "direccion", length = 500)
    private String direccion;
    
    @Column(name = "centrohospitalario", length = 255)
    private String centrohospitalario;
    
    @Column(name = "edad", nullable = false)
    private int edad;
    
    @Column(name = "observacion", length = 500)
    private String observacion;

    @Column(name = "estado", length = 10, nullable = false)
    private String estado = "ACTIVO";
}
