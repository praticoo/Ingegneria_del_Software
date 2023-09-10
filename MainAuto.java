package Programmazione.Book;

import java.util.List;
import java.util.stream.Stream;

/*
    Dato il seguente record creare i seguenti metodi:
    * a) Creare due Liste a1 e a2 di tipo Auto messe come attributo della classe;
    * b) Creare un metodo che ritorni la lista di auto contenente le macchine delle due liste che hanno lo stesso colore; 
    * c) Creare un metodo che, avendo un parametro String col, ritorni le macchine che hanno lo stesso colore di col;
    * d) Creare un metodo che ritorni il numero di macchine presenti sia in a1 che in a2;
*/

public class MainAuto { 
    private static List<Auto> a1 = List.of( new Auto("A", 120, "BLU"),
                                            new Auto("B", 170, "ROSSO"),
                                            new Auto("C", 220, "VERDE"));
    
    private static List<Auto> a2 = List.of( new Auto("D", 120, "NERO"),
                                            new Auto("E", 170, "ROSSO"),
                                            new Auto("F", 220, "VERDE"),
                                            new Auto("G", 130, "GIALLO"));
                                            
    /*public static void coloreUguale(List<Auto> a1, List<Auto> a2) {
        a1.stream()
          .forEach(x -> a2.stream()
                          .filter(r -> r.Colore().equals(x.Colore()))
                          .forEach(r -> System.out.println(x + " " + r)));   
    }*/

    public static List<String> colori(List<Auto> esempio) {
        return esempio.stream()
                      .map(Auto::Colore)
                      .toList();
    }

    public static List<Auto> coloreUguale(List<Auto> a1, List<Auto> a2) {
        Stream<Auto> a12 = Stream.concat(a1.stream(), a2.stream());
        List<String> coloriA1 = colori(a1);
        List<String> coloriA2 = colori(a2);
        return a12.filter(x -> coloriA1.contains(x.Colore()) && coloriA2.contains(x.Colore()))
                  .toList();
    }

    public static List<Auto> metodoC (List<Auto> a1, List<Auto> a2, String col) {
        Stream<Auto> a12 = Stream.concat(a1.stream(), a2.stream());
        return a12.filter(x -> x.Colore().equals(col))
                  .toList();
    }

    public static long metodoD (List<Auto> a1, List<Auto> a2) {
        Stream<Auto> a12 = Stream.concat(a1.stream(), a2.stream());
        return a12.count();
    }

    public static void main(String[]args) {
        String col = "ROSSO";
        for (Auto a12 : metodoC(a1, a2, col)) System.out.println(a12);
        System.out.println(metodoD(a1, a2));
        //coloreUguale(a1, a2);
        for (Auto a12 : coloreUguale(a1, a2)) System.out.println(a12);
    }
}
