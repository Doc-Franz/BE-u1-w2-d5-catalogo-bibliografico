package org.example;

public class Libro extends ArticoloBibliografico{
    private String autore;
    private String genere;

    public Libro(int ISBN, int annoDiPubblicazione, int numeroDiPagine, String autore, String genere){
        super(ISBN, annoDiPubblicazione, numeroDiPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore(){
        return autore;
    }

    public void setAutore(String autore){
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroDiPagine=" + numeroDiPagine +
                '}';
    }
}
