/*Usando la programmazione funzionale di Java, dato una lista lib di istanza di Libro, implementate una classe con: 
1) un metodo che restituisce una lista dei pesi dei libri presenti in lib. 
2) un metodo  che restituisce la lista concatenata della somma dei costi dei libri di ciascun genere di libri presenti in lib. 
3) un metodo che restituisce il libro di genere Giallo, che ha il costo maggiore. 
4) un metodo che restitusice la lista di autori delle istanze di lib senza ripetizione */
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