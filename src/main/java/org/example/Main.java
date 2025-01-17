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
    public static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) {
    updateArchivio();
        System.out.println(archivio.archivioList);
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


}
