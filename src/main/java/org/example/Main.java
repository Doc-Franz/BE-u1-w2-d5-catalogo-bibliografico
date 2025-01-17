package org.example;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Archivio archivio = new Archivio();
    public static int numeroLibri; // numero di libri da inserire nell'archivio
    public static int numeroRiviste; // numero di rivste da inserire nell'archivio
    public static int userInputISBN; // codice ISBN digitato dall'utente per effettuare la ricerca
    public static String userInput; // valuta la risposta dell'utente se andare avanti o meno nel ciclo
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
                userInputISBN = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getISBN() == userInputISBN)) {
                    System.out.println(archivio.archivioList.stream().filter(article -> article.getISBN() == userInputISBN).toList());
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
                userInputISBN = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getISBN() == userInputISBN)) {
                    archivio.archivioList.removeIf(article -> article.getISBN() == userInputISBN);
                    System.out.println("Articolo con ISBN " + userInputISBN + " è stato rimosso");
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
    public static void getByYear(){
        continueTheLoop = true;
        while (continueTheLoop) {
            System.out.print("Inserisci l'anno di pubblicazione per ricercare gli articoli: ");
            try {
                userInputISBN = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getAnnoDiPubblicazione() == userInputISBN)) {
                    System.out.println(archivio.archivioList.stream().filter(article -> article.getAnnoDiPubblicazione() == userInputISBN).toList());
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
    }









