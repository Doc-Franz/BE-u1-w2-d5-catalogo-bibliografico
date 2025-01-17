package org.example;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    public static Archivio archivio = new Archivio();
    public static int numeroLibri; // numero di libri da inserire nell'archivio
    public static int numeroRiviste; // numero di rivste da inserire nell'archivio
    public static int userInputISBN; // codice ISBN digitato dall'utente per effettuare la ricerca
    public static boolean continueToSearch;
    public static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) {
        updateArchivio();
        System.out.println(archivio.archivioList);
        searchForISBN();
    }

    // funzione che gestisce il riempimento iniziale dell'archivio
    public static void updateArchivio() {
        System.out.print("Quanti libri vuoi inserire all'interno dell'archivio? ");
        numeroLibri = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numeroLibri; i ++){
            try {archivio.addBook();} catch (DuplicatedCodeException ex){
                System.out.println(ex.getMessage());
            }

        }
        System.out.print("Quante riviste vuoi inserire all'interno dell'archivio? ");
        numeroRiviste = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numeroRiviste; i++){
            try {archivio.addMagazine();} catch (DuplicatedCodeException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void searchForISBN(){
        continueToSearch = true;
        while (continueToSearch) {
            System.out.print("Inserisci codice ISBN per ricercare articolo: ");
            try {
                userInputISBN = Integer.parseInt(sc.nextLine());
                if (archivio.archivioList.stream().anyMatch(article -> article.getISBN() == userInputISBN)) {
                    System.out.println(archivio.archivioList.stream().filter(article -> article.getISBN() == userInputISBN).toList());
                } else {
                    System.out.println("Articolo non trovato...");
                }

            } catch (NumberFormatException ex){
                System.out.println("Il valore inserito non è un numero valido...");
            }


            System.out.print("Vuoi continuare a cercare? Digita 1 per continuare oppure 0 per andare avanti: ");
            if (sc.nextLine().equals("0")){
                continueToSearch = false;
            }
        }


    }


}
