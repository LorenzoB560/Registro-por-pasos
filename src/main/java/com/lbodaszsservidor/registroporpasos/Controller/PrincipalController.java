package com.lbodaszsservidor.registroporpasos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

    @GetMapping("/datos-personales")
    public String datosPersonales() {
        return "datos-personales";
    }
    @GetMapping("/datos-profesionales")
    public String datosProfesionales() {
        return "datos-profesionales";
    }
    @GetMapping("/datos-bancarios")
    public String datosBancarios() {
        return "datos-bancarios";
    }
    @GetMapping("/resumen")
    public String resumen() {
        return "resumen";
    }
}
