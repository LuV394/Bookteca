package com.aluracursos.Bookteca.Principal;

import com.aluracursos.Bookteca.model.Datos;
import com.aluracursos.Bookteca.model.DatosLibros;
import com.aluracursos.Bookteca.service.ConsumoAPI;
import com.aluracursos.Bookteca.service.CovierteDatos;
import com.aluracursos.Bookteca.service.IConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private ConsumoAPI consumoAPI= new ConsumoAPI();
    private CovierteDatos conversor = new CovierteDatos();
    private Scanner teclado = new Scanner(System.in);
    public void ejecutaMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        //Top libros mas descargados
        System.out.println("Top libros mas descargados");
        datos.libros().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        //Busqueda de Titulo
        System.out.println("Ingrese el nombre del titulo que desea buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBucado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBucado.isPresent()){
            System.out.println("Libro Encontrado");
            System.out.println(libroBucado.get());
        } else {
            System.out.println("Libro no encontrado");
        }

        //Trabajando con estadisticas

        DoubleSummaryStatistics est = datos.libros().stream()
                .filter(d -> d.numeroDeDescargas()> 0)
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
        System.out.println("Cantidad media de Descargas: " + est.getAverage());
        System.out.println("Cantidad maxima de descargas: " + est.getMax());
        System.out.println("Cantidad minima de descarfas: " + est.getMin());
        System.out.println("Cantidad de registros evaluados para calcular las estadisticas: " + est.getCount());


    }
}
