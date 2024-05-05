package com.aluracursos.Bookteca.Principal;

import com.aluracursos.Bookteca.model.Datos;
import com.aluracursos.Bookteca.service.ConsumoAPI;
import com.aluracursos.Bookteca.service.CovierteDatos;
import com.aluracursos.Bookteca.service.IConvierteDatos;

public class Principal {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private ConsumoAPI consumoAPI= new ConsumoAPI();
    private CovierteDatos conversor = new CovierteDatos();
    public void ejecutaMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);
        
    }
}
