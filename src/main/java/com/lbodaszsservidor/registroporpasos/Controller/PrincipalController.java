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
        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");

        // Si no existe, creo el objeto de datos formulario y su sesión respectivamente.
        if(datosFormulario == null) {
            System.out.println("No sesión datos personales");
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("datos", datosFormulario);
        }

        //Lo añado al modelo
        model.addAttribute("datos", datosFormulario);
        System.out.println(sesion.getAttribute("datos"));

        return "datos-personales";
    }
    @PostMapping("/guardar-datos-personales")
    public String guardarDatosPersonales(@ModelAttribute DatosFormulario datosFormulario, HttpSession sesion) {
        sesion.setAttribute("datos", datosFormulario);
        return "redirect:/datos-profesionales";
    }

    @GetMapping("/datos-profesionales")
    public String datosProfesionales(HttpSession sesion, Model model) {

        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");
        System.out.println(datosFormulario);
        if(datosFormulario == null) {
            System.out.println("No sesión datos profesionales");
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("datos", datosFormulario);
        }
        model.addAttribute("datos", datosFormulario);
        return "datos-profesionales";
    }
    @PostMapping("/guardar-datos-profesionales")
    public String guardarDatosProfesionales(@ModelAttribute DatosFormulario datosFormulario, HttpSession sesion) {

        DatosFormulario datosAnteriores = (DatosFormulario) sesion.getAttribute("datos");

        actualizarDatos(datosAnteriores, datosFormulario);

        sesion.setAttribute("datos", datosFormulario);
        return "redirect:/datos-bancarios";
    }


    @GetMapping("/datos-bancarios")
    public String datosBancarios(HttpSession sesion, Model model) {

        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");
        System.out.println(datosFormulario);
        if(datosFormulario == null) {
            System.out.println("No sesión datos bancarios");
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("datos", datosFormulario);
        }
        model.addAttribute("datos", datosFormulario);
        return "datos-bancarios";
    }
    @PostMapping("/guardar-datos-bancarios")
    public String guardarDatosBancarios(@ModelAttribute DatosFormulario datosFormulario, HttpSession sesion) {

        DatosFormulario datosAnteriores = (DatosFormulario) sesion.getAttribute("datos");

        actualizarDatos(datosAnteriores, datosFormulario);

        sesion.setAttribute("datos", datosFormulario);
        return "redirect:/resumen";
    }

    @GetMapping("/resumen")
    public String resumen(HttpSession sesion, Model model) {
        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");

        if (datosFormulario == null) {
            System.out.println("No sesión resumen");
            datosFormulario = new DatosFormulario();
        }

        model.addAttribute("datos", datosFormulario);
        System.out.println(sesion.getAttribute("datos"));
        return "resumen";  // No vuelvas a guardar en la sesión
    }


    private void actualizarDatos(DatosFormulario sesionVieja, DatosFormulario sesionNueva) {
        if (sesionVieja.getNombre() != null){
            sesionNueva.setNombre(sesionVieja.getNombre());
        }
        if (sesionVieja.getApellido() != null){
            sesionNueva.setApellido(sesionVieja.getApellido());
        }
        if (sesionVieja.getDepartamento() != null){
            sesionNueva.setDepartamento(sesionVieja.getDepartamento());
        }
        if (sesionVieja.getSalario() > 0){
            sesionNueva.setSalario(sesionVieja.getSalario());
        }
        if (sesionVieja.getBancoNombre() != null) {
            sesionNueva.setBancoNombre(sesionVieja.getBancoNombre());
        }

        if (sesionVieja.getCuentaBancaria() > 0){
            sesionNueva.setCuentaBancaria(sesionVieja.getCuentaBancaria());
        }


    }
}
