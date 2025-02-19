package com.lbodaszsservidor.registroporpasos.Controller;

import com.lbodaszsservidor.registroporpasos.model.DatosFormulario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrincipalController {



    @GetMapping("/datos-personales")
    public String datosPersonales(HttpSession sesion, Model model) {

        //Obtengo la sesión de personales
        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("personales");

        // Si no existe, creo el objeto de datos formulario y su sesión respectivamente.
        if(datosFormulario == null) {
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("personales", datosFormulario);
        }

        //Lo añado al modelo
        model.addAttribute("personales", datosFormulario);
        System.out.println(sesion.getAttribute("personales"));

        return "datos-personales";
    }
    @GetMapping("/datos-profesionales")
    public String datosProfesionales(HttpSession sesion, Model model) {

        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("personales");
        if(datosFormulario == null) {
            System.out.println("No sesion");
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("personales", datosFormulario);
        }
        model.addAttribute("personales", datosFormulario);

        return "datos-profesionales";
    }
    @PostMapping("/guardar-datos-profesionales")
    public String guardarDatosProfesionales(@ModelAttribute DatosFormulario datosFormulario, HttpSession sesion) {
        sesion.setAttribute("personales", datosFormulario);
        return "redirect:/datos-profesionales";
    }


    @GetMapping("/datos-bancarios")
    public String datosBancarios(HttpSession sesion, Model model) {

        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("profesionales");
        if(datosFormulario == null) {
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("profesionales", datosFormulario);
        }
        model.addAttribute("profesionales", datosFormulario);
        return "datos-bancarios";
    }
    @PostMapping("/guardar-datos-bancarios")
    public String guardarDatosBancarios(@ModelAttribute DatosFormulario datosFormulario, HttpSession sesion) {
        sesion.setAttribute("profesionales", datosFormulario);
        return "redirect:/datos-bancarios";
    }

    @GetMapping("/resumen")
    public String resumen(@ModelAttribute DatosFormulario datosFormulario, HttpSession sesion, Model model) {
        return "resumen";
    }
}
