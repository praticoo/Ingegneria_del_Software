package Programmazione.Drive;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainDue {

    public static int gen(List<Libro> prodPool) {
        int i = (int)Math.round(Math.random() * (prodPool.size() - 1));
        return i;
    }

    // -	FANTASY -5%
	// -	CYBERPUNK -10%
	// -	THRILLER -50%
	// -	STORICO -10%
	// -	INFORMATICA (niente sconti per noi programmatori...) 
    public static int sconto(String category, int prezzo) {
        if (category == "FANTASY") return (prezzo * 5) / 100;
        else if (category == "INFORMATICA") return prezzo;
        else if (category == "CYBERPUNK" || category == "STORICO") return (prezzo * 10) / 100;
        else return (prezzo * 50) / 100;
    }
    public static void main(String[]args) {
        List<Libro> prodPool = List.of(
			new Libro("Leviathan", "FANTASY", 20),
			new Libro("Trono di Spade", "FANTASY", 40),
			new Libro("Signore degli Anelli", "FANTASY", 25),
			new Libro("Neuromante", "CYBERPUNK", 15),
			new Libro("Monnalisa", "CYBERPUNK", 12),
			new Libro("Mirrorshades", "CYBERPUNK", 10),
			new Libro("Profondo Blu", "THRILLER", 8),
			new Libro("Invasion", "THRILLER", 12),
			new Libro("Il Collezionista D'Ossa", "THRILLER", 18),
			new Libro("Harry Potter", "FANTASY", 60000),
			new Libro("Storia di una capinera", "STORICO", 5),
			new Libro("Design Pattern", "INFORMATICA", 35));

        //query0(); Stampare 10 libri (usando generate)
        Stream.generate(() -> gen(prodPool))
              .limit(3)
              .distinct()
              .forEach(x -> System.out.println(prodPool.get(x).getNome()));
              
        //query1(); Contare tutti i libri CYBERPUNK
        long conto = prodPool.stream()
                             .filter(x -> x.getCategoria().equals("CYBERPUNK"))
                             .count();  
        System.out.println(conto);

        //query2(); Lista dei titoli dei libri CYBERPUNK o FANTASY 
        List<String> title = prodPool.stream()
                                     .filter(x -> x.getCategoria().equals("CYBERPUNK") || x.getCategoria().equals("FANTASY"))
                                     .map(Libro::getNome)
                                     .distinct()
                                     .toList();
        for (String t : title) System.out.print(t + " ");
        System.out.println();

        //query3(); Somma dei costi di tutti i libri
        Integer somma = prodPool.stream()
                                .mapToInt(Libro::getPrezzo)
                                .sum();
        System.out.println(somma);

        //query4(); Somma in dollari dei costi di tutti i libri (1 EUR = 1.12 USD)
        /*Integer som = prodPool.stream()
                              .map(x -> new Libro(x.getNome(), x.getCategoria(),x.getPrezzo() + (int)(x.getPrezzo() * 0.12)))
                              .mapToInt(Libro::getPrezzo)
                              .sum();
        System.out.println(som + "$");*/
        Double som = prodPool.stream()
                             .map(Libro::getPrezzo)
                             .mapToDouble(x -> x + (x * 0.12))
                             .sum();
        System.out.println(som);
        //query5(); Stampa tutti i libri con prezzo compreso tra 10 e 15
        List<Libro> compreso = prodPool.stream()
                                       .filter(x -> x.getPrezzo() >= 10 && x.getPrezzo() <= 15)
                                       .toList();
        for (Libro c : compreso) System.out.println(c.getNome() + " " + c.getPrezzo());

        //query6(); Titolo del Libro meno caro (ma a partire da 12)
        Optional<String> minCaro = prodPool.stream()
                                 .filter(x -> x.getPrezzo() >= 12)
                                 .sorted(Comparator.comparing(Libro::getPrezzo))
                                 .map(Libro::getNome)
                                 .findAny();                    //ritorna un Optional
        if (minCaro.isPresent()) System.out.println(minCaro.get());
                                        
        //query7(); Stampa la lista dei libri ordinati per prezzo
       List<Libro> ordine = prodPool.stream()
                                    .sorted(Comparator.comparing(Libro::getPrezzo))
                                    .toList();
        for (Libro o : ordine) System.out.println(o.getNome() + " " + o.getPrezzo());

        //query9(); Stampa i libri raggruppati per categoria (senza groupingBy)
        prodPool.stream()
                .map(Libro::getCategoria)
                .distinct()
                .peek(x -> System.out.print("\n" + x + ": "))
                .forEach(x -> prodPool.stream()
                                      .filter(r -> r.getCategoria().equals(x))
                                      .forEach(r -> System.out.print(r.getNome() + " ")));      
          
        System.out.println();
        //query10(); Creare una lista di libri fantasy da "Harry Potter 1" a "Harry Potter 7", tutti da 15 euro 
        List<Libro> hp = IntStream.rangeClosed(1, 7)
                                  .mapToObj(x -> new Libro("Harry Potter " + x, "FANTASY", 15))
                                  .toList();
        for (Libro hapo : hp) System.out.println(hapo.getNome() + " " + hapo.getCategoria() + " " + hapo.getPrezzo());
        //query11(); Sfruttando il metodo precedente, ottenere la lista di libri e mescolarla in ordine casuale
        Stream.generate(() -> gen(hp))
              .distinct()
              .limit(hp.size() - 1)
              .forEach(x -> System.out.println(hp.get(x).getNome()));

        //query12(); Data una lista di libri, stampare il primo che ha un prezzo maggiore del precedente
        IntStream.rangeClosed(0, prodPool.size() - 2)
                 .filter(x -> prodPool.get(x + 1).getPrezzo() > prodPool.get(x).getPrezzo())
                 .peek(x -> System.out.println(prodPool.get(x + 1).getNome() + " " + prodPool.get(x + 1).getPrezzo()))
                 .findAny();
        
        //query14(); // Data una lista di libri, applicare ad ogni libro uno sconto che dipende dalla categoria
        List<Libro> sconti = prodPool.stream()
                                     .map(x -> new Libro(x.getNome(), x.getCategoria(), sconto(x.getCategoria(), x.getPrezzo())))
                                     .toList();
        for(Libro s : sconti) System.out.println(s.getNome() + " " + s.getPrezzo());                              
    }
}

