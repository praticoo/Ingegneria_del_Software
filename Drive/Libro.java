package Programmazione.Drive;

public class Libro {
    private String nome;
    private String categoria;
    private int prezzo;

    public Libro(String n, String c, int p) {
        nome = n;
        categoria = c;
        prezzo = p;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPrezzo() {
        return prezzo;
    }
} 
