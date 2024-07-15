package com.alura.literatura.repositorio;

import com.alura.literatura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RespositorioLibros extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByAutores();
    List<Libro> findByNacimiento();
    List<Libro> findByIdioma();
    @Query("select s from Libro s WHERE s.autores <= :autores")
    List<Libro> LibrosPorAutor(String autores);
    @Query("select s from Libro s WHERE s.titulo <= :titulo")
    List<Libro> LibrosPorTitulo(String titulo);
    @Query("select s from Libro s WHERE s.nacimiento <= :nacimiento")
    List<Libro> LibrosPorNacimiento(String nacimiento);
}
