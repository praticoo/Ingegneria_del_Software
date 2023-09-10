package Programmazione.Book;

import java.util.*;

/*Usando la programmazione funzionale di Java, dato una lista lib di istanza di Libro, implementate una classe con: 
1) un metodo che restituisce una lista dei pesi dei libri presenti in lib. 
2) un metodo  che restituisce la lista concatenata della somma dei costi dei libri di ciascun genere di libri presenti in lib. 
3) un metodo che restituisce il libro di genere Giallo, che ha il costo maggiore. 
4) un metodo che restitusisce la lista di autori delle istanze di lib senza ripetizione */

public class MainDue {
    public static Integer sommaPesi(List<book> lib) {
        return lib.stream()
                  .mapToInt(book::getPeso)
                  .sum();
    }

    public static Optional<book> gialloMax(List<book> lib) {
        return lib.stream()
                  .filter(x -> x.getGenere().equals("Fantasy"))
                  .max(Comparator.comparing(book::getCosto));
    }

    public static List<String> autori(List<book> lib) {
        return lib.stream()
                  .map(book::getAutore)
                  .distinct()
                  .toList();
    }

    /*public static void lista(List<book> lib) {
        lib.stream()
           .map(book::getGenere)
           .distinct()
           .peek(x -> System.out.print("\n" + x + ": "))
           .forEach(x -> {int som = lib.stream()
                                       .filter(r -> r.getGenere().equals(x))
                                       .mapToInt(book::getCosto)
                                       .sum(); 
                          System.out.print(som);});
    }*/

    public static void lista(List<book> lib) {
        lib.stream()
           .map(book::getGenere)
           .distinct()
           .peek(x -> System.out.print("\n" + x + ": "))
           .forEach(x -> lib.stream()
                            .filter(r -> r.getGenere().equals(x))
                            .mapToInt(book::getCosto)
                            .peek(System.out::print)
                            .sum());
    }

    public static void autor(List<book> lib) {
        lib.stream()
           .map(book::getGenere)
           .distinct()
           .peek(x -> System.out.print("\n" + x + " "))
           .forEach(x -> lib.stream()
                            .filter(r -> r.getGenere().equals(x))
                            .map(book::getAutore)
                            .distinct()
                            .forEach(r -> System.out.print(r + " ")));       
    }

    public static void main(String[]args) {
        List<book> lib = List.of (new book("Il signore degli anelli", 11, 2, "Fantasy", "J.R.R Tolkien"), 
                                 new book("Il Decameron", 9, 3, "Raccolta", "G. Boccaccio"),
                                 new book("Guerra e Pace",  14, 6, "Storico", "L. Tolstoj"),
                                 new book("Il Ritratto di Dorian Gray", 8, 3, "Fiction", "O. Wilde"),
                                 new book("Cime Tempestose", 8, 2, "Romanzo", "E. Bronte"),
                                 new book("Alice nel Paese delle Meraviglie",10, 4, "Fantasy", "L. Carroll"));
        
        //1
        System.out.println(sommaPesi(lib));

        //2
        lista(lib);
        System.out.println();
        System.out.println();

        //Per ogni genere, stampare gli autori
        autor(lib);
        System.out.println();
        System.out.println();

        //3
        if (gialloMax(lib).isPresent()) System.out.println(gialloMax(lib).get().getTitolo());
        System.out.println();

        //4
        for(String a : autori(lib)) System.out.print(a + " ");

    }
}