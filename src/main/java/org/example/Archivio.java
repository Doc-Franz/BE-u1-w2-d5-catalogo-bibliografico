package org.example;

import com.github.javafaker.Faker;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Archivio {

    private Libro libro;
    private Rivista rivista;
    public List<ArticoloBibliografico> archivioList = new ArrayList<>();
    boolean isDuplicated;

    Faker faker = new Faker(new Locale("it"));

    public Archivio(){
    };

    // funzione che verifica se il codice ISBN del nuovo elemento da inserire è già presente in archivioList
    public boolean checkDuplicatedCode(ArticoloBibliografico article){
        return archivioList.stream().anyMatch(element -> element.getISBN() == article.getISBN());
    }

    public void addBook() throws DuplicatedCodeException {
        libro = new Libro((int) (Math.random() * 200), getAnnoDiPubblicazione(), getNumeroDiPagine(), faker.book().author(), faker.book().genre());
        isDuplicated = checkDuplicatedCode(libro);
        if (!isDuplicated) {
            archivioList.add(libro);
        } else {
            throw new DuplicatedCodeException("L'articolo con codice " + libro.getISBN() + " è già presente in archivio...");
        }
    }

    public void addMagazine() throws DuplicatedCodeException {
        Periodicità periodicitàCasuale = Periodicità.values()[(int) (Math.random() * Periodicità.values().length)]; // valore randomico di periodicità della rivista
        rivista = new Rivista((int) (Math.random() * 200),getAnnoDiPubblicazione(), getNumeroDiPagine(), periodicitàCasuale);
        isDuplicated = checkDuplicatedCode(rivista);
        if(!isDuplicated) {
            archivioList.add(rivista);
        } else {
            throw new DuplicatedCodeException("L'articolo con codice " + rivista.getISBN() + " è già presente in archivio...");
        }
    }

    // funzione che calcola randomicamente l'anno di pubblicazione (a partire dal 1980 e fino al 2025)
    public int getAnnoDiPubblicazione(){
        return 1980 + (int) (Math.random() * 46);
    }

    // funzione che calcola randomicamente il numero di pagine (da 50 a 900)
    public int getNumeroDiPagine(){
        return 50 + (int) (Math.random() * 801);
    }
}
