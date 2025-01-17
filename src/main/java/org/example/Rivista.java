package org.example;

public class Rivista extends ArticoloBibliografico{ ;

    Periodicità periodicità;
    public Rivista(int ISBN, int annoDiPubblicazione, int numeroDiPagine, Periodicità periodicità){
        super(ISBN, annoDiPubblicazione, numeroDiPagine);
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicità=" + periodicità +
                ", ISBN=" + ISBN +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroDiPagine=" + numeroDiPagine +
                '}';
    }
}
