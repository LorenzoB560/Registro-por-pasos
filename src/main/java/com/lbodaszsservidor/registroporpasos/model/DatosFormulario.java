package com.lbodaszsservidor.registroporpasos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosFormulario {


    // ** PASO 1 - DATOS PERSONALES **
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String generoSeleccionado;
    private List<String> nacionalidadSeleccionada;

    // ** PASO 2 - DATOS PROFESIONALES **
    private String departamentoSeleccionado;
    private double salario;
    private String comentarios;

    // ** PASO 3 - DATOS BANCARIOS **
    private String bancoNombre;
    private int cuentaBancaria;


}
