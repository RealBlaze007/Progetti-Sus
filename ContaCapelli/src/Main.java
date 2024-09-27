import static Tools.Utility.*;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in);
        Random random = new Random();

        String[] opzioni = {"Menu", "1 Inserisci persona", "2 Visualizza persone", "3 Conta capelli", "4 Fine"};
        boolean fine = false;

        System.out.println("Quante persone vuoi prendere in considerazione?");
        final int nPersone = Integer.parseInt(tastiera.nextLine());
        int contaPersone = 0;
        Persona[] persone = new Persona[nPersone];

        boolean personaGiaPresente;

        String nomeAnalizzare, cognomeAnalizzare;
        int posizionePersona;

        do {
            try {
                switch (menu(opzioni, tastiera)) {
                    case 1 -> {
                        if (contaPersone < nPersone) {
                            personaGiaPresente = inserimentoPersona(persone, contaPersone, tastiera);
                            if (!personaGiaPresente) {
                                contaPersone++;
                            } else {
                                System.out.println("Persona già presente");
                            }
                        } else {
                            System.out.println("Hai raggiunto il limite di persone specificato (" + nPersone + ")");
                        }
                    }
                    case 2 -> visualizzaPersona(persone, contaPersone);
                    case 3 -> {
                        System.out.println("Inserisci il nome della persona da analizzare");
                        nomeAnalizzare = tastiera.nextLine();
                        System.out.println("Inserisci il cognome della persona da analizzare");
                        cognomeAnalizzare = tastiera.nextLine();
                        posizionePersona = trovaPersona(persone, nomeAnalizzare, cognomeAnalizzare, contaPersone);
                        if (posizionePersona >= 0) {
                            contaCapelli(persone, posizionePersona, random);
                        } else {
                            System.out.println("Persona non trovata");
                        }
                    }
                    case 4 -> {
                        System.out.println("Uscita programma");
                        fine = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Inserimento non valido");
                e.printStackTrace();
            }
        } while (!fine);
    }

    public static boolean inserimentoPersona(Persona[] persone, int contaPersone, Scanner tastiera) {
        String inserimentoNome, inserimentoCognome;
        persone[contaPersone] = new Persona();
        System.out.println("Inserisci il nome della " + (contaPersone + 1) + "ª" + " persona");
        inserimentoNome = tastiera.nextLine();
        System.out.println("Inserisci il cognome della " + (contaPersone + 1) + "ª" + " persona");
        inserimentoCognome = tastiera.nextLine();

        if (trovaPersona(persone, inserimentoNome, inserimentoCognome, contaPersone) != -1) {
            return true;
        }

        persone[contaPersone].nome = inserimentoNome;
        persone[contaPersone].cognome = inserimentoCognome;
        return false;
    }


    public static void visualizzaPersona(Persona[] persone, int contaPersone) {
        System.out.printf("%-10s %-10s %-10s\n", "Nome", "Cognome", "Numero Capelli");
        for (int i = 0; i < contaPersone; i++) {
            if (persone[i].capelli > -1) {
                System.out.printf("%-10s %-10s %-10d\n", persone[i].nome, persone[i].cognome, persone[i].capelli);
            } else {
                System.out.printf("%-10s %-10s %-10s\n", persone[i].nome, persone[i].cognome, "Capelli Ignoti");
            }
        }
    }

    public static void contaCapelli(Persona[] persone, int posizionePersona, Random random) {
        if (persone[posizionePersona].nome.equals("Emiliano") && persone[posizionePersona].cognome.equals("Spiller")) {
            persone[posizionePersona].capelli = 0;
        } else {
            persone[posizionePersona].capelli = random.nextInt(80000, 110000);
        }
    }

    public static int trovaPersona(Persona[] persone, String nomeAnalizzare, String cognomeAnalizzare, int contaPersone) {
        for (int i = 0; i < contaPersone; i++) {
            if (nomeAnalizzare.equals(persone[i].nome) && cognomeAnalizzare.equals(persone[i].cognome)) {
                return i;
            }
        }
        return -1;
    }
}