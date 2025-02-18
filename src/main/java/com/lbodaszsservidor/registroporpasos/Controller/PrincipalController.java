package com.lbodaszsservidor.registroporpasos.Controller;

import com.lbodaszsservidor.registroporpasos.Coleccion;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PrincipalController {



    @GetMapping("/datos-personales")
    public String datosPersonales(@ModelAttribute Coleccion coleccion, HttpSession sesion, Model model) {

        Object v = sesion.getAttribute ("personales");
        if (v != null && v.equals(1)){
            coleccion = (Coleccion) sesion.getAttribute("personales");
            sesion.setAttribute("personales", coleccion);
        }


        return "datos-personales";
    }
    @GetMapping("/datos-profesionales")
    public String datosProfesionales(@ModelAttribute Coleccion coleccion, HttpSession sesion, Model model) {

        sesion.setAttribute("personales", coleccion);
        return "datos-profesionales";
    }
    @GetMapping("/datos-bancarios")
    public String datosBancarios(@ModelAttribute Coleccion coleccion, HttpSession sesion, Model model) {
        return "datos-bancarios";
    }
    @GetMapping("/resumen")
    public String resumen(@ModelAttribute Coleccion coleccion, HttpSession sesion, Model model) {
        return "resumen";
    }
}
