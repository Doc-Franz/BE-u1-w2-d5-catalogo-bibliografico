package org.example;

public class Rivista extends ArticoloBibliografico{ ;

    public Rivista(int ISBN, int annoDiPubblicazione, int numeroDiPagine){
        super(ISBN, annoDiPubblicazione, numeroDiPagine);
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "ISBN='" + ISBN + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroDiPagine=" + numeroDiPagine +
                '}';
    }
}
