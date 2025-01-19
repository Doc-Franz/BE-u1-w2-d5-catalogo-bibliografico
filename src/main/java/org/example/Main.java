package org.example;

import com.github.javafaker.Faker;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static Archivio archivio = new Archivio();
    public static int numeroLibri; // numero di libri da inserire nell'archivio
    public static int numeroRiviste; // numero di rivste da inserire nell'archivio
    public static int userInputForSearch; // codice ISBN digitato dall'utente per effettuare la ricerca
    public static String userInput; // valuta la risposta dell'utente se andare avanti o meno nel ciclo, utilizzato anche nel getByAuthor perchè serviva una String
    public static String valueToModify; // variabile che controlla la maodifica delle proprietà di un articolo
    public static boolean continueTheLoop;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        updateArchivio();
        System.out.println();

        System.out.println(archivio.archivioList);
        System.out.println();

        searchForISBN();
        System.out.println();

        removeElement();
        System.out.println();

        getByYear();
        System.out.println();

        getByAuthor();
        System.out.println();

        updateElement();
        System.out.println();

        printStats();
        System.out.println();

        System.out.println("La lettura dell'archivio è terminata...");
    }

    // funzione che gestisce il riempimento iniziale dell'archivio
    public static void updateArchivio() {
        System.out.print("Quanti libri vuoi inserire all'interno dell'archivio? ");
        numeroLibri = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numeroLibri; i++) {
            try {
                archivio.addBook();
            } catch (DuplicatedCodeException ex) {
                System.out.println(ex.getMessage());
            }

        }
        System.out.print("Quante riviste vuoi inserire all'interno dell'archivio? ");
        numeroRiviste = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numeroRiviste; i++) {
            try {
                archivio.addMagazine();
            } catch (DuplicatedCodeException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    // metodo per ricercare un valore tramite codice ISBN
    public static void searchForISBN() {
        continueTheLoop = true;
        while (continueTheLoop) {
            System.out.print("Inserisci codice ISBN per ricercare articolo: ");
            try {
                userInputForSearch = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getISBN() == userInputForSearch)) {
                    System.out.println(archivio.archivioList.stream().filter(article -> article.getISBN() == userInputForSearch).toList());
                } else {
                    throw new CodeNotFound("Articolo non trovato...");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Il valore inserito non è un numero valido...");
            } catch (CodeNotFound ex) {
                System.out.println(ex.getMessage());
            }

            do {
                try {
                    System.out.print("Vuoi continuare a ricercare? Digita 1 per continuare oppure 0 per andare avanti: ");
                    userInput = sc.nextLine();
                    if (userInput.equals("0")) {
                        continueTheLoop = false;
                    } else if (userInput.equals("1")) {
                        break;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Il valore inserito non è un numero valido...");
                }
            } while (!userInput.equals("0") && !userInput.equals("1")); // ripete il ciclo del try finchè l'utente non inserisce un valore valido per decidere cosa fare


        }
    }

    // metodo per rimuovere un elemento tramite codice ISBN
    public static void removeElement() {
        continueTheLoop = true;
        while (continueTheLoop) {
            System.out.print("Inserisci codice ISBN per eliminare un articolo: ");
            try {
                userInputForSearch = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getISBN() == userInputForSearch)) {
                    archivio.archivioList.removeIf(article -> article.getISBN() == userInputForSearch);
                    System.out.println("Articolo con ISBN " + userInputForSearch + " è stato rimosso");
                    System.out.println(archivio.archivioList);
                } else {
                    throw new CodeNotFound("Articolo non trovato...");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Il valore inserito non è un numero valido...");
            } catch (CodeNotFound ex) {
                System.out.println(ex.getMessage());
            }

            do {
                try {
                    System.out.print("Vuoi continuare a ricercare? Digita 1 per continuare oppure 0 per andare avanti: ");
                    userInput = sc.nextLine();
                    if (userInput.equals("0")) {
                        continueTheLoop = false;
                    } else if (userInput.equals("1")) {
                        break;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Il valore inserito non è un numero valido...");
                }
            } while (!userInput.equals("0") && !userInput.equals("1")); // ripete il ciclo del try finchè l'utente non inserisce un valore valido per decidere cosa fare
        }
    }

    // metodo per ricercare una serie di articoli per anno di pubblicazione
    public static void getByYear() {
        continueTheLoop = true;
        while (continueTheLoop) {
            System.out.print("Inserisci l'anno di pubblicazione per ricercare gli articoli: ");
            try {
                userInputForSearch = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getAnnoDiPubblicazione() == userInputForSearch)) {
                    System.out.println(archivio.archivioList.stream().filter(article -> article.getAnnoDiPubblicazione() == userInputForSearch).toList());
                } else {
                    throw new CodeNotFound("Nessun articolo pubblicato in questi anni...");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Il valore inserito non è un numero valido...");
            } catch (CodeNotFound ex) {
                System.out.println(ex.getMessage());
            }

            do {
                try {
                    System.out.print("Vuoi continuare a ricercare? Digita 1 per continuare oppure 0 per andare avanti: ");
                    userInput = sc.nextLine();
                    if (userInput.equals("0")) {
                        continueTheLoop = false;
                    } else if (userInput.equals("1")) {
                        break;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Il valore inserito non è un numero valido...");
                }
            } while (!userInput.equals("0") && !userInput.equals("1")); // ripete il ciclo del try finchè l'utente non inserisce un valore valido per decidere cosa fare
        }
    }

    // metodo per ricercare una serie di articoli per autore
    public static void getByAuthor() {
        continueTheLoop = true;
        while (continueTheLoop) {
            System.out.print("Inserisci l'autore per ricercare gli articoli: ");
            try {
                userInput = sc.nextLine();

                List<Libro> libriInArchivio = archivio.archivioList.stream().filter(article -> article instanceof Libro).map(article -> (Libro) article).filter(libro -> libro.getAutore().equals(userInput)).toList();
                if (!libriInArchivio.isEmpty()) {
                    System.out.println("I libri di questo autore sono: " + libriInArchivio);
                } else {
                    throw new CodeNotFound("Nessun libro di questo autore...");
                }

            } catch (CodeNotFound ex) {
                System.out.println(ex.getMessage());
            }

            do {
                try {
                    System.out.print("Vuoi continuare a ricercare? Digita 1 per continuare oppure 0 per andare avanti: ");
                    userInput = sc.nextLine();
                    if (userInput.equals("0")) {
                        continueTheLoop = false;
                    } else if (userInput.equals("1")) {
                        break;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Il valore inserito non è un numero valido...");
                }
            } while (!userInput.equals("0") && !userInput.equals("1")); // ripete il ciclo del try finchè l'utente non inserisce un valore valido per decidere cosa fare
        }
    }

    // metodo per modificare un elemento dato il codice ISBN
    public static void updateElement(){
        continueTheLoop = true;
        while (continueTheLoop) {
            System.out.print("Inserisci codice ISBN per aggiornare articolo: ");
            try {
                userInputForSearch = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getISBN() == userInputForSearch)) {
                    List<Libro> libroDaModificare = archivio.archivioList.stream().filter(article -> article.getISBN() == userInputForSearch && article instanceof Libro).map(article -> (Libro) article).toList();
                    List<Rivista> rivistaDaModificare = archivio.archivioList.stream().filter(article -> article.getISBN() == userInputForSearch && article instanceof Rivista).map(article -> (Rivista) article).toList();

                    if(!libroDaModificare.isEmpty()){
                        System.out.print("Digita 'a' per modificare l'autore, 'g' per modificare il genere, 'i' per modificare il codice ISBN, 'p' per modificare l'anno di pubblicazione, 'n' per modificare il numero di pagine: ");
                        userInput = sc.nextLine();
                        switch (userInput) {
                            case "a": {
                                System.out.print("Inserire il nuovo nome dell'autore: ");
                                valueToModify = sc.nextLine();
                                libroDaModificare.forEach(article -> article.setAutore(valueToModify));
                                System.out.println(libroDaModificare);
                                break;
                            }
                            case "g": {
                                System.out.print("Inserire il nuovo nome del genere: ");
                                valueToModify = sc.nextLine();
                                libroDaModificare.forEach(article -> article.setGenere(valueToModify));
                                System.out.println(libroDaModificare);
                                break;
                            }
                            case "i": {
                                System.out.print("Inserire il nuovo codice ISBN: ");
                                valueToModify = sc.nextLine();
                                libroDaModificare.forEach(article -> article.setISBN(Integer.parseInt(valueToModify)));
                                System.out.println(libroDaModificare);
                                break;
                            }
                            case "p": {
                                System.out.print("Inserire il nuovo anno di pubblicazione: ");
                                valueToModify = sc.nextLine();
                                libroDaModificare.forEach(article -> article.setAnnoDiPubblicazione(Integer.parseInt(valueToModify)));
                                System.out.println(libroDaModificare);
                                break;
                            }
                            case "n": {
                                System.out.print("Inserire il nuovo numero di pagine: ");
                                valueToModify = sc.nextLine();
                                libroDaModificare.forEach(article -> article.setNumeroDiPagine(Integer.parseInt(valueToModify)));
                                System.out.println(libroDaModificare);
                                break;
                            }
                            default: {
                                throw new NumberFormatException();
                            }
                        }

                    } else if (!rivistaDaModificare.isEmpty()) {
                        System.out.print("Digita 'i' per modificare il codice ISBN, 'p' per modificare l'anno di pubblicazione, 'n' per modificare il numero di pagine: ");
                        userInput = sc.nextLine();
                        switch (userInput) {
                            case "i": {
                                System.out.print("Inserire il nuovo codice ISBN: ");
                                valueToModify = sc.nextLine();
                                rivistaDaModificare.forEach(article -> article.setISBN(Integer.parseInt(valueToModify)));
                                System.out.println(rivistaDaModificare);
                                break;
                            }
                            case "p": {
                                System.out.print("Inserire il nuovo anno di pubblicazione: ");
                                valueToModify = sc.nextLine();
                                rivistaDaModificare.forEach(article -> article.setAnnoDiPubblicazione(Integer.parseInt(valueToModify)));
                                System.out.println(rivistaDaModificare);
                                break;
                            }
                            case "n": {
                                System.out.print("Inserire il nuovo numero di pagine: ");
                                valueToModify = sc.nextLine();
                                rivistaDaModificare.forEach(article -> article.setNumeroDiPagine(Integer.parseInt(valueToModify)));
                                System.out.println(rivistaDaModificare);
                                break;
                            }
                            default: {
                                throw new NumberFormatException();
                            }
                        }

                    }
                } else {
                    throw new CodeNotFound("Articolo non trovato...");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Il valore inserito non è un numero valido...");
            } catch (CodeNotFound ex) {
                System.out.println(ex.getMessage());
            }

            do {
                try {
                    System.out.print("Vuoi continuare a modificare? Digita 1 per continuare oppure 0 per andare avanti: ");
                    userInput = sc.nextLine();
                    if (userInput.equals("0")) {
                        continueTheLoop = false;
                    } else if (userInput.equals("1")) {
                        break;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Il valore inserito non è un numero valido...");
                }
            } while (!userInput.equals("0") && !userInput.equals("1")); // ripete il ciclo del try finchè l'utente non inserisce un valore valido per decidere cosa fare


        }
    }

    // metodo per stampare le statistiche del catalogo
    public static void printStats(){
        int numeroLibri = archivio.archivioList.stream().filter(article -> article instanceof Libro).toList().size();
        System.out.println("Il numero di libri presenti nel catalogo è " + numeroLibri);
        int numeroRiviste = archivio.archivioList.stream().filter(article -> article instanceof Rivista).toList().size();
        System.out.println("Il numero di riviste presenti nel catalogo è " + numeroRiviste);
        List<ArticoloBibliografico> articoloConPiùPagine = archivio.archivioList.stream().max(Comparator.comparing(ArticoloBibliografico::getNumeroDiPagine)).stream().toList();
        System.out.println("L'articolo con il maggior numero di pagine è " + articoloConPiùPagine);
        int mediaPagine = archivio.archivioList.stream().collect(Collectors.averagingInt(ArticoloBibliografico::getNumeroDiPagine)).intValue();
        System.out.println("La media delle pagine degli articoli è " + mediaPagine);
    }
}









