package com.lbodaszsservidor.registroporpasos.model;


import com.lbodaszsservidor.registroporpasos.grupos.GrupoProfesional;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosFormulario {



    public interface GrupoPersonal {};
    // ** PASO 1 - DATOS PERSONALES **
    private String nombre;
    private String apellido;

    @NotNull(message = "La fecha de nacimiento no puede ser nula", groups = GrupoPersonal.class)
    @Past(message = "La feche debe ser pasada", groups = GrupoPersonal.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNacimiento;
    private String generoSeleccionado;


    @NotNull(message = "Debes seleccionar al menos dos nacionalidades.", groups = GrupoPersonal.class)
    @Size(min = 2, message = "Debes seleccionar al menos dos nacionalidades.", groups = GrupoPersonal.class)
    private List<String> nacionalidadSeleccionada;

    // ** PASO 2 - DATOS PROFESIONALES **
    private String departamentoSeleccionado;

    @NotNull(message = "El salario no puede ser nulo", groups = GrupoProfesional.class)
    @DecimalMin(value = "0.01", message = "El salario debe ser un número real positivo", groups = GrupoProfesional.class)
    private double salario;
    private String comentarios;

    // ** PASO 3 - DATOS BANCARIOS **
    private String bancoNombre;
    private int cuentaBancaria;


}
