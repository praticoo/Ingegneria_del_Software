package Programmazione.Book;

public class book {
    private String titolo;
    private int peso;
    private int costo;
    private String genere;
    private String autore; 

    public book(String t, int p, int c, String g, String a) {
        titolo = t;
        peso = p;
        costo = c;
        genere = g;
        autore = a;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getPeso() {
        return peso;
    }

    public int getCosto() {
        return costo;
    }

    public String getGenere() {
        return genere;
    }

    public String getAutore() {
        return autore;
    }
}
