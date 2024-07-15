package com.alura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alura.literatura.modelos.DatosLibros;

public class ConvertirDatos implements IConvertirDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase){
        try{
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
