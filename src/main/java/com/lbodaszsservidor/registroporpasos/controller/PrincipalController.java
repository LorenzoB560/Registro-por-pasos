package com.lbodaszsservidor.registroporpasos.controller;


import com.lbodaszsservidor.registroporpasos.model.Coleccion;
import com.lbodaszsservidor.registroporpasos.model.DatosFormulario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrincipalController {

    @ModelAttribute //Cargo las colecciones una vez para añadirlas al modelo
    public void adicionColecciones(Model modelo, HttpServletRequest request) {
        modelo.addAttribute("listaGeneros", Coleccion.getListaGeneros());
        modelo.addAttribute("listaNacionalidades", Coleccion.getListaNacionalidades());
        modelo.addAttribute("listaDepartamentos", Coleccion.getListaDepartamentos());

    }

    @GetMapping("/datos-personales")
    public String datosPersonales(HttpSession sesion, Model model) {

        //Obtengo la sesión de personales
        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");

        // Si no existe, creo el objeto de datos formulario y su sesión respectivamente.
        if(datosFormulario == null) {
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("datos", datosFormulario);
        }

        //Lo añado al modelo
        model.addAttribute("datos", datosFormulario);

        return "datos-personales";
    }
    @PostMapping("/guardar-datos-personales")
    public String guardarDatosPersonales(
            @Validated(DatosFormulario.GrupoPersonal.class) @ModelAttribute("datos") DatosFormulario datosFormulario,
            BindingResult bindingResult,
            HttpSession sesion,
            Model model) {

        // Si hay errores, volver a la misma página
        if (bindingResult.hasErrors()) {
            model.addAttribute("datos", datosFormulario);
            model.addAttribute("mensajeNOK", "El formulario tiene errores");
            return "datos-personales";
        }

        DatosFormulario datosAnteriores = (DatosFormulario) sesion.getAttribute("datos");
        if (datosAnteriores != null) {
            actualizarDatos(datosAnteriores, datosFormulario);
        } else {
            datosAnteriores = datosFormulario;
        }

        sesion.setAttribute("datos", datosAnteriores);
        return "redirect:/datos-profesionales";
    }


    @GetMapping("/datos-profesionales")
    public String datosProfesionales(HttpSession sesion, Model model) {

        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");
        if(datosFormulario == null) {
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("datos", datosFormulario);
        }
        model.addAttribute("datos", datosFormulario);
        return "datos-profesionales";
    }
    @PostMapping("/guardar-datos-profesionales")
    public String guardarDatosProfesionales(
            @Validated(DatosFormulario.GrupoProfesional.class) @ModelAttribute("datos") DatosFormulario datosFormulario,
            BindingResult bindingResult,
            HttpSession sesion,
            Model model) {

        // Si hay errores, volver a la misma página
        if (bindingResult.hasErrors()) {
            model.addAttribute("datos", datosFormulario);
            model.addAttribute("mensajeNOK", "El formulario tiene errores");
            return "datos-profesionales";
        }

        DatosFormulario datosAnteriores = (DatosFormulario) sesion.getAttribute("datos");

        actualizarDatos(datosAnteriores, datosFormulario);

        sesion.setAttribute("datos", datosAnteriores);
        return "redirect:/datos-bancarios";
    }


    @GetMapping("/datos-bancarios")
    public String datosBancarios(HttpSession sesion, Model model) {

        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");
        if(datosFormulario == null) {
            datosFormulario = new DatosFormulario();
            sesion.setAttribute("datos", datosFormulario);
        }
        model.addAttribute("datos", datosFormulario);
        return "datos-bancarios";
    }
    @PostMapping("/guardar-datos-bancarios")
    public String guardarDatosBancarios(
            @Validated(DatosFormulario.GrupoBancario.class) @ModelAttribute("datos") DatosFormulario datosFormulario,
            BindingResult bindingResult,
            HttpSession sesion,
            Model model) {

        // Si hay errores, volver a la misma página
        if (bindingResult.hasErrors()) {
            model.addAttribute("datos", datosFormulario);
            model.addAttribute("mensajeNOK", "El formulario tiene errores");
            return "datos-bancarios";
        }

        DatosFormulario datosAnteriores = (DatosFormulario) sesion.getAttribute("datos");

        actualizarDatos(datosAnteriores, datosFormulario);

        sesion.setAttribute("datos", datosAnteriores);
        return "redirect:/resumen";
    }

    @GetMapping("/resumen")
    public String resumen(HttpSession sesion, Model model) {
        DatosFormulario datosFormulario = (DatosFormulario) sesion.getAttribute("datos");

        if (datosFormulario == null) {
            datosFormulario = new DatosFormulario();
        }

        model.addAttribute("datos", datosFormulario);
        return "resumen";
    }

    @GetMapping("volver-principio")
    public String volverPrincipio(HttpSession sesion, Model model) {
        sesion.invalidate();
        return "redirect:/datos-personales";
    }


    private void actualizarDatos(DatosFormulario sesionVieja, DatosFormulario sesionNueva) {
        if (sesionVieja == null) {
            sesionVieja = new DatosFormulario(); // Se crea una nueva instancia si la sesión ha expirado
        }

        if (sesionNueva == null) {
            return; // Si no hay nuevos datos, no hacemos nada
        }

        // Datos personales
        sesionVieja.setNombre(sesionNueva.getNombre() != null ? sesionNueva.getNombre() : sesionVieja.getNombre());
        sesionVieja.setApellido(sesionNueva.getApellido() != null ? sesionNueva.getApellido() : sesionVieja.getApellido());
        sesionVieja.setFechaNacimiento(sesionNueva.getFechaNacimiento() != null ? sesionNueva.getFechaNacimiento() : sesionVieja.getFechaNacimiento());
        sesionVieja.setGeneroSeleccionado(sesionNueva.getGeneroSeleccionado() != null ? sesionNueva.getGeneroSeleccionado() : sesionVieja.getGeneroSeleccionado());
        sesionVieja.setNacionalidadSeleccionada(sesionNueva.getNacionalidadSeleccionada() != null ? sesionNueva.getNacionalidadSeleccionada() : sesionVieja.getNacionalidadSeleccionada());

        // Datos profesionales
        sesionVieja.setDepartamentoSeleccionado(sesionNueva.getDepartamentoSeleccionado() != null ? sesionNueva.getDepartamentoSeleccionado() : sesionVieja.getDepartamentoSeleccionado());
        sesionVieja.setSalario(sesionNueva.getSalario() > 0 ? sesionNueva.getSalario() : sesionVieja.getSalario());
        sesionVieja.setComentarios(sesionNueva.getComentarios() != null ? sesionNueva.getComentarios() : sesionVieja.getComentarios());

        // Datos bancarios
        sesionVieja.setBancoNombre(sesionNueva.getBancoNombre() != null ? sesionNueva.getBancoNombre() : sesionVieja.getBancoNombre());
        sesionVieja.setCuentaBancaria(sesionNueva.getCuentaBancaria() != null ? sesionNueva.getCuentaBancaria() : sesionVieja.getCuentaBancaria());
    }

}
