package com.lbodaszsservidor.registroporpasos.model;

import java.util.HashMap;
import java.util.Map;

public class Coleccion {
    private static Map<String, String> listaGeneros = new HashMap<String, String>();
    private static Map<String, String> listaNacionalidades = new HashMap<String, String>();

    static {
        listaGeneros.put("M", "Masculino");
        listaGeneros.put("F", "Femenino");
        listaGeneros.put("O", "Otros");

        listaNacionalidades.put("española", "Española");
        listaNacionalidades.put("francesa", "Francesa");
        listaNacionalidades.put("italiana", "Italiana");
        listaNacionalidades.put("portuguesa", "Portugesa");

    }

    public static Map<String, String> getListaGeneros() {
        return listaGeneros;
    }

    public static void setListaGeneros(Map<String, String> listaGeneros) {
        Coleccion.listaGeneros = listaGeneros;
    }

    public static Map<String, String> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public static void setListaNacionalidades(Map<String, String> listaNacionalidades) {
        Coleccion.listaNacionalidades = listaNacionalidades;
    }
}
