package Programmazione;
import java.util.*;

/* il "vertice" in un triangolo isoscele è l'angolo formato dai due lati uguali. In un triangolo isoscele, 
ci sono due lati della stessa lunghezza (chiamati lati "late" o lati obliqui) e un altro lato chiamato "base", 
che è di lunghezza diversa. L'angolo tra i due lati uguali è chiamato "vertice" del triangolo isoscele. */

public class EsercizioRipasso {

    public static int lato(int l1, int l2, int l3) {
        if (l1 == l2) return l1;
        else if (l2 == l3) return l2;
        else return l3;
    }

    public static int base(int l1, int l2, int l3) {
        if (l1 == l2) return l3;
        else if (l2 == l3) return l1;
        else return l2;
    }

    public static int vertice(int a1, int a2, int a3) {
        if (a1 > a2 && a1 > a3) return a1;
        else if (a2 > a1 && a2 > a3) return a2;
        else return a3;
    }

    public static List<TriIso> trIsosceli(List<Triangolo> tr) {
        return tr.stream()
                 .filter(x -> x.l1() == x.l2() || x.l1() == x.l3() || x.l2() == x.l3())
                 .map(x -> new TriIso(lato(x.l1(), x.l2(), x.l3()), base(x.l1(), x.l2(), x.l3()),vertice(x.a1(), x.a2(), x.a3())))
                 .toList();
    }

    public static List<Integer> perimetri(List<TriIso> triangIso) {
        return triangIso.stream()
                        .map(x -> (x.lato() * 2) + x.base())
                        .toList();
    }

    public static List<Integer> basi(List<TriIso> triangIso) {
        return triangIso.stream()
                        .map(TriIso::base)
                        .toList();
    }

    public static Integer vertMax(List<TriIso> triangIso) {
        return triangIso.stream()
                        .map(TriIso::vertice)
                        .reduce(0, Integer::max);
                        
    }

    public static Optional<Integer> vertMin(List<TriIso> triangIso) {
        return triangIso.stream()
                        .map(TriIso::vertice)
                        .reduce(Integer::min);
                        
    }

    public static void main(String[]args) {
        List<Triangolo> tr = List.of(new Triangolo(5, 5, 8, 45, 45, 80),
                                    new Triangolo(3, 6, 9, 30, 30, 120),
                                    new Triangolo(12, 15, 12, 45, 45, 90));
        //Trovare triangoli isosceli 
        List<TriIso> triangIso = trIsosceli(tr);
        for(TriIso ti : triangIso) System.out.println(ti);
        
        //Lista di perimetri 
        for(Integer p : perimetri(triangIso)) System.out.print(p + " ");
        System.out.println();
    
        //Lista di basi 
        for(Integer b : basi(triangIso)) System.out.print(b + " ");
        System.out.println();

        //Trovare vertice con valore massimo 
        System.out.println(vertMax(triangIso));
        if (vertMin(triangIso).isPresent()) System.out.println(vertMin(triangIso).get());
    } 
}