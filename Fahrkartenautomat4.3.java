/*
 * …
 * A4.3: Ticketgrenzen im Fahrkartenautomat
 */
import java.util.Scanner;

class Fahrkartenautomat {
    public static void main(String[] args) {

        Scanner tastatur = new Scanner(System.in);

        double ticketPreis = 0.0;
        double zuZahlenderBetrag;
        double eingezahlterGesamtbetrag;
        double eingeworfeneMuenze;
        double rueckgabebetrag;
        double nochZuZahlen;
        int ticketzahl;

        // Geldbetrag eingeben mit Überprüfung auf Negativität
        do {
            System.out.print("Ticketpreis (Euro): ");
            ticketPreis = tastatur.nextDouble();
            if (ticketPreis < 0) {
                System.out.println("Fehlerhafte Eingabe - Ticketpreis wird auf 1 gesetzt");
                ticketPreis = 1; // Standardwert setzen
            }
        } while (ticketPreis < 0);

        // Ticketzahl eingeben mit Überprüfung auf gültigen Bereich (1 bis 10)
        do {
            System.out.print("Anzahl der Tickets: ");
            ticketzahl = tastatur.nextInt();
            if (ticketzahl < 1 || ticketzahl > 10) {
                System.out.println("Fehlerhafte Eingabe - Ticketanzahl wird auf 1 gesetzt");
                ticketzahl = 1; // Standardwert setzen
            }
        } while (ticketzahl < 1 || ticketzahl > 10);

        // Gesamtbetrag berechnen
        zuZahlenderBetrag = ticketPreis * ticketzahl;

        // Geldeinwurf
        eingezahlterGesamtbetrag = 0.0;
        nochZuZahlen = zuZahlenderBetrag;
        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            System.out.printf("Noch zu zahlen: %.2f Euro%n", nochZuZahlen); // Noch zu zahlen: 2,50 Euro
            System.out.print("Eingabe (mind. 5 Cent, höchstens 2 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();
            eingezahlterGesamtbetrag += eingeworfeneMuenze;
            nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
        }

        // Fahrscheinausgabe
        System.out.println("\nFahrschein wird ausgegeben");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");

        // Rückgeldberechnung und Ausgabe
        rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
        if (rueckgabebetrag > 0.0) {
            System.out.printf("Der Rückgabebetrag in Höhe von %.2f Euro%n", rueckgabebetrag);
            System.out.println("wird in folgenden Münzen ausgezahlt:");

            while (rueckgabebetrag >= 1.0) {
                System.out.println("1 Euro");
                rueckgabebetrag = Math.round(rueckgabebetrag * 100.0) / 100.0;
                rueckgabebetrag -= 1.0;
            }
        }

        System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
                + "Wir wünschen Ihnen eine gute Fahrt.");

        tastatur.close();
    }
}
