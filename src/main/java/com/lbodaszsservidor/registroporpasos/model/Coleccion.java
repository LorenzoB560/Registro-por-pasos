package com.lbodaszsservidor.registroporpasos.model;

import java.util.ArrayList;
import java.util.List;


public class Coleccion {
    private static List<String> listaGeneros = new ArrayList<>();
    private static List<String> listaNacionalidades = new ArrayList<>();

    static {
        listaGeneros.add("Masculino");
        listaGeneros.add("Femenino");
        listaGeneros.add("Otros");

        listaNacionalidades.add("Española");
        listaNacionalidades.add("Francesa");
        listaNacionalidades.add("Italiana");
        listaNacionalidades.add("Portugesa");

    }

    public static List<String> getListaGeneros() {
        return listaGeneros;
    }

    public static void setListaGeneros(List<String> listaGeneros) {
        Coleccion.listaGeneros = listaGeneros;
    }

    public static List<String> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public static void setListaNacionalidades(List<String> listaNacionalidades) {
        Coleccion.listaNacionalidades = listaNacionalidades;
    }
}