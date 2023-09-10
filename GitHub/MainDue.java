package Programmazione.GitHub;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MainDue {

    public record Auto(String categoria, String colore, Double costo, String modello){}
    public record Figura(int l1, int l2, int l3, int l4, int a1, int a2, int a3, int a4){}
    public record Triangolo(int l1, int l2, int l3, int a1, int a2, int a3){}

    private static List<Auto> al = List.of( new Auto("A", "BLU", 120.0, "Berlina"),
                                            new Auto("B", "ROSSO", 170.0, "Sportiva"),
                                            new Auto("C", "VERDE", 220.0, "SUV"));

    private static List<Figura> figure = List.of( new Figura(1, 2, 3, 4, 5, 6, 7, 8),
                                                  new Figura(2, 3, 4, 5, 6, 7, 8, 9),
                                                  new Figura(3, 4, 5, 6, 7, 8, 9, 10));

    private static List<Triangolo> t = List.of( new Triangolo(1, 2, 3, 90, 6, 7),
                                                new Triangolo(2, 3, 4, 6, 7, 8),
                                                new Triangolo(3, 4, 5, 7, 8, 9));

    public static int area(int l1, int l2, int l3) {
        if (l1 > l2 && l1 > l3) return (l2 * l3) / 2;
        else if (l2 > l1 && l2 > l3) return (l1 * l3) / 2;
        else return (l1 * l2) / 2;
    }

    public static void main(String[] args) {
        //Incrementa il costo di ogni auto del 5%
        List<Auto> incremento = al.stream()
                                  .map(x -> new Auto(x.categoria(), x.colore(), x.costo() + (x.costo() * 0.05), x.modello()))
                                  .toList();
        for (Auto i : incremento) System.out.println(i);

        //Somma dei costi di tutte le auto
        Double somma = al.stream()
                         .mapToDouble(Auto::costo)
                         .sum();
        /*Double somma = al.stream()
                         .map(Auto::costo)
                         .reduce(0.0, Double::sum);*/
        System.out.println("Somma costi auto : " + somma);

        //Trovare il minimo dei costi delle istanze di auto
        /*Optional<Auto> minimo = al.stream()    
                                   .min(Comparator.comparing(Auto::costo));
        if (minimo.isPresent()) System.out.println("Costo minore : " + minimo.get());*/
        Optional<Double> minimo = al.stream()
                                    .map(Auto::costo)
                                    .min(Double::compare);
        if (minimo.isPresent()) System.out.println("Costo minore : " + minimo.get());

        //Ordinare una lista
        List<Auto> ordine = al.stream()
                              .sorted(Comparator.comparing(Auto::costo))
                              .toList();
        for (Auto o : ordine) System.out.println(o);

        //Auto di costo maggiore
        Optional<Auto> cMax = al.stream()
                      .max(Comparator.comparing(Auto::costo));
        System.out.println("Auto costo max : " + cMax.get());

        //Verificare che le auto siano ordinate per costo
        Double first = al.get(0).costo();
        boolean b = al.stream()
                      .map(Auto::costo)
                      .filter(x -> x < first)
                      .findAny()
                      .isEmpty();
        if (b == true) System.out.println("Lista ordinata per costo");
        else System.out.println("Lista non ordinata");

        //La lista dei lati maggiori per ogni figura
        List<Integer> lMax = figure.stream()
                                   .map(x -> List.of(x.l1(), x.l2(), x.l3(), x.l4()))
                                   .map(x -> x.stream().reduce(Integer::max).get())
                                   .toList();
        for (Integer lm : lMax) System.out.print(lm + " ");
        System.out.println();

        //La lista dell'angolo minore per ogni figura
        List<Integer> aMin = figure.stream()
                                   .map(x -> List.of(x.a1(), x.a2(), x.a3))
                                   .map(x -> x.stream().reduce(Integer::min).get())
                                   .toList();
        for(Integer aM : aMin) System.out.print(aM + " ");
        System.out.println();

        //Il perimetro maggiore dei triangoli
        Optional<Integer> perimetroMax = t.stream()
                                .map(x -> x.l1() + x.l2() + x.l3())
                                .reduce(Integer::max);

        if (perimetroMax.isPresent()) System.out.println(perimetroMax.get());

        //La somma dei perimetri delle istanze di t
        Integer sommaPerimetri = t.stream()
                                  .mapToInt(x -> x.l1() + x.l2() + x.l3())
                                  .sum();
        System.out.println(sommaPerimetri);

        //Le aree dei triangoli rettangoli
        List<Integer> aree = t.stream()
                              .filter(x -> x.a1() == 90 || x.a2() == 90 || x.a3() == 90)
                              .map(x -> area(x.l1(), x.l2(), x.l3()))
                              .toList();

        for (Integer a : aree) System.out.print(a + " ");
        System.out.println();

        //La lista dei lati minori delle istanze di t1
        List<Integer> lMin = t.stream()
                              .map(x -> List.of(x.l1(), x.l2(), x.l3()))
                              .map(x -> x.stream().reduce(Integer::min).get())
                              .toList();
        for (Integer lM : lMin) System.out.print(lM + " ");
        System.out.println();

        //Tornare una lista di tutti triangoli rettangoli a partire da t(triangoli rettangoli
        List<Triangolo> tRet = t.stream()
                                .filter(x -> x.a1() == 90 || x.a2() == 90 || x.a3() == 90)
                                .toList();
        for (Triangolo tr : tRet) System.out.println(tr);

        //Fornire una lista dove tutte le auto sono verdi a partire da al
        List<Auto> verdi = al.stream()
                             .map(x -> new Auto(x.categoria(), "VERDE", x.costo(), x.modello()))
                             .toList();
        for (Auto v : verdi) System.out.println(v);

        //Tornare una lista di figure copn perimetro doppio a partire da figure
        List<Figura> pDoppio = figure.stream()
                                     .map(x -> new Figura(x.l1() * 2, x.l2() * 2, x.l3() * 2, x.l4() * 2, x.a1(), x.a2(), x.a3(), x.a4))
                                     .toList();
        for (Figura pd : pDoppio) System.out.println(pd);
        
    }
}