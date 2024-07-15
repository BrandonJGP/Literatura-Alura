package com.alura.literatura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros (
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") String autores,
    @JsonAlias("name") String nombres,
    @JsonAlias("languages") String idioma,
    @JsonAlias("birth_year") Integer nacimiento,
    @JsonAlias("death_year") Integer muerte){
}
