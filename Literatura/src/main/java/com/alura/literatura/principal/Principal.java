package com.alura.literatura.principal;

import com.alura.literatura.modelos.Libro;
import com.alura.literatura.service.ConsumirAPI;
import com.alura.literatura.modelos.DatosLibros;
import com.alura.literatura.service.ConvertirDatos;
import com.alura.literatura.repositorio.RespositorioLibros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumirAPI consumirApi = new ConsumirAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private ConvertirDatos conversor = new ConvertirDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private RespositorioLibros repositorio;
    private List<Libro> libros;


    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0){
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1.- Buscar libro por titulo");
            System.out.println("2.- Listar libros registrados");
            System.out.println("3.- Listar autores registrados");
            System.out.println("4.- Listar autores vivos en un determinado año");
            System.out.println("5.- Listar libros por idioma");
            System.out.println("0.- Salir");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarPorTitulo();
                    break;

                case 2:
                    LibrosRegistrados();
                    break;

                case 3:
                    AutoresRegistrados();
                    break;

                case 4:
                    AutoresVivos();
                    break;

                case 5:
                    Idioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;

                default:
                    System.out.println("Opción invalida");
            }
        }
    }

    private DatosLibros getDatosLibros(){
        System.out.println("Escribe el titulo del libro que deseas buscar:");
        var tituloLibro = teclado.nextLine();
        var json = consumirApi.obtenerDatos(URL_BASE + tituloLibro.replace(" ", "%20"));
        System.out.println(json);
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        return datos;
    }

    private void buscarPorTitulo(){
        DatosLibros datos = getDatosLibros();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);
    }

    private void LibrosRegistrados(){
        libros = repositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void AutoresRegistrados(){
        libros = repositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getAutores))
                .forEach(System.out::println);
    }

    private void AutoresVivos(){
        DatosLibros datos = getDatosLibros();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);
    }

    private void Idioma(){
        DatosLibros datos = getDatosLibros();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);
    }
}
