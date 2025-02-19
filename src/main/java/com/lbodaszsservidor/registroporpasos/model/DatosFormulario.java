package com.lbodaszsservidor.registroporpasos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatosFormulario {

    private String nombre;
    private String apellido;
    /*
    private LocalDate fechaNacimiento;
    private String genero;
    private ArrayList<String> nacionalidades;*/


    private String departamento;
    private double salario;

}
