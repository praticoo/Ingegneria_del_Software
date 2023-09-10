package Programmazione.Telegram;

import java.util.Optional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RipassoDue {

    public static List<Persona> comprese(List<Persona> p, int min, int max) {
        return p.stream()
                .filter(x -> x.age() > min && x.age() < max)
                .toList();
    }
    public static void main(String[]args) {
        List<Persona> p = List.of(new Persona("Kent", 29, "CTO"),
                          new Persona("Luigi", 25, "Programmer"),
                          new Persona("Andrea", 26, "GrLeader"),
                          new Persona("Sofia", 26, "Programmer"),
                          new Persona("Alfio", 63, "Programmer"));

        //Data una lista di persone, trovare i nomi dei programmatori con età minore di 30 anni.
        List<String> nomi = p.stream()
                             .filter(x -> x.role().equals("Programmer") && x.age() < 30)
                             .map(Persona::name)
                             .toList();
        for (String n : nomi) System.out.print(n + " ");
        System.out.println();

        //Data una lista di istanze di Persona trovare i diversi ruoli.
        List<String> ruoli = p.stream()
                              .map(Persona::role)
                              .distinct()
                              .toList();
        for (String r : ruoli) System.out.print(r + " ");
        System.out.println();

        //Data una lista di stringhe, produrre una lista che contiene solo le stringhe che cominciano con un certo prefisso noto.
        List<String> s = List.of("author", "auto","autocorrect", "begin","big", "bigger", "biggish");
        String prex = "au";
        List<String> noto = s.stream()
                             //.filter(x -> x.startsWith(prex))
                             .filter(x -> x.substring(0, 2).equals(prex))
                             .toList();
        for (String n : noto) System.out.print(n + " ");
        System.out.println();

        //Data una lista di stringhe, produrre una stringa contenente le iniziali di ciascuna stringa della lista.
        String iniziali = s.stream()
                           //.map(x -> x.substring(0, 1))
                           //.reduce("", String::concat);
                           .reduce("", (accum, v) -> accum + v.substring(0, 1));
        System.out.println(iniziali);

        /* Data una lista di terne di numeri interi, per ciascuna terna verificare se essa costituisce un triangolo. 
        Restituire la lista dei perimetri per le terne che rappresentano triangoli.
        In un triangolo, ciascun lato è minore della somma degli altri due.
        Si può rappresentare la terna come un array di tre elementi interi */ 
        List<List<Integer>> t = List.of(List.of(3, 4, 5), List.of(3, 4, 6),
                                List.of(3, 4, 7), List.of(3, 4, 8));

        List<Integer> perimetri = t.stream()
                                   .filter(x -> x.get(0) <= x.get(1) + x.get(2))
                                   .filter(x -> x.get(1) <= x.get(0) + x.get(2))
                                   .filter(x -> x.get(2) <= x.get(1) + x.get(0))
                                   .map(x -> x.get(0) + x.get(1) + x.get(2))
                                   .toList();
        for (Integer pm : perimetri) System.out.print(pm + " ");
        System.out.println();

        /* Data una lista di numeri interi positivi, verificare se la lista è ordinata.
           Suggerimenti:
           Si generano gli indici da 0 a n-1
           Per ciascun valore dell'indice i, si confrontano l'elemento con indice i ed il successivo, 
           se il secondo è minore del primo la lista non è ordinata e si può fermare la verifica */

        List<Integer> crescente = List.of(1, 2, 3, 4, 5);
        List<Integer> decrescente = List.of(5, 4, 3, 2, 1);

        Boolean b = IntStream.rangeClosed(0, crescente.size() - 1)
                             .filter(x -> crescente.get(0) > crescente.get(1))
                             .findAny()
                             .isEmpty();

        System.out.println(b);

        Boolean c = IntStream.rangeClosed(0, decrescente.size() - 1)
                             .filter(x -> decrescente.get(0) > decrescente.get(1))
                             .findAny()
                             .isEmpty();

        System.out.println(c);

        List<Integer> prova = IntStream.rangeClosed(0, 4)
                                   .boxed()
                                   .toList();
        for (Integer i : prova) System.out.print(i + " ");
        System.out.println();

        //Data una lista di prodotti, restituire il costo totale dei prodotti che hanno un prezzo maggiore di 10.
        List<Prodotto> prod = List.of(new Prodotto("p1", 5.0), new Prodotto("p2", 10.0),
                            new Prodotto("p3", 15.0), new Prodotto("p4", 20.0));
        Double tot = prod.stream()
                       .map(Prodotto::prezzo)
                       .filter(x -> x > 10)
                       .reduce(0.0, Double::sum);
        System.out.println(tot);

        //Restituire il prodotto più economico
        Optional<Prodotto> economico = prod.stream()
                                          .sorted(Comparator.comparing(Prodotto::prezzo))
                                          .findFirst();
        System.out.println(economico.get());

        Optional<Prodotto> eco = prod.stream()
                           .min(Comparator.comparing(Prodotto::prezzo));
        System.out.println(eco.get());

        //Restituire la somma totale dei costi dei 2 prodotti meno cari
        /*Double s2 = prod.stream()
                        .sorted(Comparator.comparing(Prodotto::prezzo))
                        .limit(2)
                        .mapToDouble(Prodotto::prezzo)
                        .sum();*/
        Double s2 = prod.stream()
                        .mapToDouble(Prodotto::prezzo)
                        .sorted()
                        .limit(2)
                        .sum();
        System.out.println(s2);

        //Produrre una lista contenente i primi n multipli di 7.
        int n = 10;
        List<Integer> multipli = IntStream.rangeClosed(0, n - 1)
                                          .mapToObj(x -> x * 7)
                                          //.map(x -> x * 7)
                                          //.boxed()
                                          .toList();
        for (Integer m : multipli) System.out.print(m + " ");
        System.out.println();

        List<Figura> f = List.of(new Figura(12, 12, 12, 12, 45, 45, 135, 135),
                         new Figura(2, 2, 2, 2, 90, 90, 90, 90),
                         new Figura(1, 2, 1, 2, 90, 90, 90, 90));

        //Restituire tutte le figure che siano rettangoli o quadrati (tutti gli angoli uguali)
        List<Figura> uguali = f.stream()
                               .filter(x -> x.a1() == x.a2() && x.a1() == x.a3() && x.a1() == x.a4() && x.a2() == x.a3() && x.a2() == x.a4() && x.a3() == x.a4())
                               .toList();
        for (Figura u : uguali) System.out.println(u);

        //Restituire uno stream che contenga il lato minore per ogni figura
        Stream<Integer> lMin = f.stream()
                                .map(x -> List.of(x.l1(), x.l2(), x.l3(), x.l4()))
                                .map(x -> x.stream().reduce(Integer::min).get());

        lMin.forEach(x -> System.out.print(x + " "));
        System.out.println();

        //Restituire il perimetro minore tra tutte le figure
        Optional<Integer> perimetro = f.stream()
                             .map(x -> x.l1() + x.l2() + x.l3() + x.l4())
                             .reduce(Integer::min);
        System.out.println(perimetro.get());

        //Ottenere la somma del valore del perimetro di tutte le figure
         Integer somma = f.stream()
                          .mapToInt(x -> x.l1() + x.l2() + x.l3() + x.l4())
                          .sum();
        System.out.println(somma);

        List<Triangolo> T = List.of(new Triangolo(3, 4, 5, 30, 60, 90),
                            new Triangolo(4, 5, 4, 30, 30, 120),
                            new Triangolo(13, 5, 12, 30, 60, 90),
                            new Triangolo(17, 15, 8, 30, 60, 90));

        //Restituire la lista di lati maggiori dei triangoli.
        List<Integer> lMax = T.stream()
                              .map(x -> List.of(x.l1(), x.l2(), x.l3()))
                              .map(x -> x.stream().reduce(Integer::max).get())
                              .toList();
        for (Integer lM : lMax) System.out.print(lM + " ");
        System.out.println();

        //Restituire una lista di triangoli isosceli. Un triangolo è isoscele se due suoi lati sono uguali.
        List<Triangolo> isosceli = T.stream()
                                    .filter(x -> x.l1() == x.l2() || x.l1() == x.l3() || x.l2() == x.l3())
                                    .toList();
        for (Triangolo i : isosceli) System.out.println(i);

        //Creare un metodo che prende in ingresso due parametri, min e max, 
        //e restituisce una lista di istanze di persona costituita da elementi di gente che hanno età compresa fra min e max.
        int min = 25; int max = 30;
        List<Persona> risultato = comprese(p, min, max);
        for(Persona r : risultato) System.out.println(r);

        //Calcolare la somma delle età di tutte le persone nella lista.
        long sumAge = p.stream()
                       .mapToInt(Persona::age)
                       .sum();
        System.out.println(sumAge);

        //Restituire una mappa contenente le coppie (nome persone - ruolo).
        List<String[]> coppie = p.stream()
                                 .map(x -> new String[]{x.name(), x.role()})
                                 .toList();
        for (String[] cp : coppie) System.out.println(cp[0] + " " + cp[1]);

        /*Data una lista di terne di numeri interi 
          • Per ciascuna terna verificare se essa costituisce un triangolo 
          • Restituire la lista dei perimetri per le terne che rappresentano triangoli */

        List<int[]> tre = List.of(new int[]{2, 4, 5}, new int[]{3, 4, 7}, new int[]{1, 2, 4});
        List<Integer> peri = tre.stream()
                                     .filter(x -> x[0] < x[1] + x[2])
                                     .filter(x -> x[1] < x[0] + x[2])
                                     .filter(x -> x[2] < x[1] + x[0])
                                     .map(x -> x[0] + x[1] + x[2])
                                     .toList();
        for (Integer pM : peri) System.out.print(pM + " ");
        System.out.println();

        /* • Data una lista di numeri interi 
           • Verificare se ciascuna terna formata prendendo dalla lista tre numeri contigui costituisce un triangolo 
           • Esempio: lista {2, 3, 5, 7, 8}, terne {2, 3, 5}, {3, 5, 7}, {5, 7, 8} 
           • Restituire la lista delle terne che rappresentano triangoli 
           • Esempio: terne {3, 5, 7}, {5, 7, 8} */
        List<int[]> ter = IntStream.rangeClosed(0, crescente.size() - 3)
                                   .mapToObj(x -> new int[]{crescente.get(x), crescente.get(x + 1), crescente.get(x + 2)})
                                   .filter(x -> x[0] < x[1] + x[2])
                                   .filter(x -> x[1] < x[0] + x[2])
                                   .filter(x -> x[2] < x[1] + x[0])
                                   .toList();
        for (int[] tr : ter) System.out.println(tr[0] + " " + tr[1] + " " + tr[2]);     

        Stream<Integer> ll = f.stream()
                              .map(x -> List.of(x.l1(), x.l2(), x.l3(), x.l4()))
                              .map(x -> x.stream().reduce(Integer::min).get());
        ll.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }     
}

