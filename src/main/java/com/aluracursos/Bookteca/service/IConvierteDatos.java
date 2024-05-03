package com.aluracursos.Bookteca.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
