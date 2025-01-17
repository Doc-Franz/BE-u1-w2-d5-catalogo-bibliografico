package org.example;

public abstract class ArticoloBibliografico {
    protected int ISBN;
    protected int annoDiPubblicazione;
    protected int numeroDiPagine;

    public ArticoloBibliografico(int ISBN, int annoDiPubblicazione, int numeroDiPagine){
        this.ISBN = ISBN;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroDiPagine = numeroDiPagine;
    }

    public int getISBN(){
        return ISBN;
    }

    public void setISBN(int ISBN){
        this.ISBN = ISBN;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroDiPagine() {
        return numeroDiPagine;
    }

    public void setNumeroDiPagine(int numeroDiPagine) {
        this.numeroDiPagine = numeroDiPagine;
    }

}
