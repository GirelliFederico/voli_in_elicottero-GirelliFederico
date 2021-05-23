/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.voli_in_elicottero.girellifederico;

import com.mycompany.voli_in_elicottero.girellifederico.time.*;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Federico Girelli
 */

public class Main 
{
 private static final String[] opzioni=new String[] 
 {
  "esci",
  "prenota",
  "annulla prenotazione",
  "lista prenotazioni per elicottero e data",
  "lista prenotazioni per cliente",
  "lista prenotazioni per data",
  "esporta come CSV",
 };
 
 public static void main(String[] args) throws IOException 
 {
  GestoreVoli gestoreVoli=new GestoreVoli(5);
  
  Scanner scanner=new Scanner(System.in);
  
  int selezione;
  while (true) 
  {
   for (int i=0; i<opzioni.length; i++)
        System.out.printf("%d. %s\n", i, opzioni[i]);
  
   do 
   {
    System.out.println("\nselezione: ");
    selezione=scanner.nextInt();
   } while (selezione<0 || selezione>=opzioni.length);

   switch (selezione) 
   {
    case 0 ->
    {
     System.exit(0);
    }

    case 1 ->
    {
     System.out.println("\ninserire nome e cognome:");
     String nome=scanner.next();
     String cognome=scanner.next();

     DataOra data=null;
     while (data==null) 
     {
      System.out.println("Inserire data (yy mm dd) e ora (hh):");
      int anno=scanner.nextInt();
      int mese=scanner.nextInt();
      int giorno=scanner.nextInt();
      int ora=scanner.nextInt();

      try { data=new DataOra(anno, mese, giorno, ora); }
      catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
     }

     System.out.println("inserire numero passeggeri:");
     int passeggeri=scanner.nextInt();

     System.out.println("trasporto merci? (true [si] o false [no])");
     boolean merci=scanner.nextBoolean();
     
     if(!gestoreVoli.prenota(nome, cognome, data, passeggeri, merci))
	System.out.println("\nnon è stato possibile effettuare la prenotazione nella fascia oraria inserita.\n");
     else
        System.out.println("\nprenotazione effettuata con successo.\n");

     scanner.nextLine();
    }
     
    case 2 -> 
    {
     System.out.println("\ninserire nome e cognome:");
     String nome=scanner.next();
     String cognome=scanner.next();

     ListaPrenotazioni prenotazioni=gestoreVoli.listaPrenotazioni(nome, cognome);
     
     if(prenotazioni.getDimensioni()==0) 
     {
      System.out.printf("\nnon esistono prenotazioni per %s %s.", nome, cognome);
      scanner.nextLine();
      System.out.println("\n");
      continue;
     }

     for (int i=0; i<prenotazioni.getDimensioni(); i++) 
     {
      System.out.println();
      System.out.print(i + ". ");
      System.out.println(prenotazioni.get(i));
     }

     selezione=-1;
     System.out.println("\nselezionare prenotazione: ");
     while(selezione<0 || selezione>=prenotazioni.getDimensioni())
     selezione=scanner.nextInt();

     Prenotazione prenotazione = prenotazioni.get(selezione);


     if(!gestoreVoli.annullaPrenotazione(nome, cognome, prenotazione.getDataOra()))
        System.out.println("\nnon è stato possibile annullare la prenotazione.\n");
     else
	System.out.println("\nprenotazione annullata con successo.\n");

     scanner.nextLine();
    }
    
    case 3 -> 
    {
     int elicottero=-1;
     System.out.printf("selezionare elicottero (0 - %d):", gestoreVoli.getNumElicotteri());
     while(elicottero<0 || elicottero >=gestoreVoli.getNumElicotteri())
     elicottero=scanner.nextInt();

     Data data=null;
     while (data==null) 
     {
      System.out.println("inserire data (yy mm dd):");
      int anno=scanner.nextInt();
      int mese=scanner.nextInt();
      int giorno=scanner.nextInt();

      try { data= ew Data(anno, mese, giorno); }
      catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
     }

     ListaPrenotazioni prenotazioni=gestoreVoli.listaPrenotazioni(elicottero, data);

     for (int i=0; i<prenotazioni.getDimensioni(); i++) 
     {
      System.out.print(i + ". ");
      System.out.println(prenotazioni.get(i)); 
     }

     scanner.nextLine();
    }
    
    case 4 -> 
    {
     System.out.println("\ninserire nome e cognome:");
     String nome=scanner.next();
     String cognome=scanner.next();

     ListaPrenotazioni prenotazioni=gestoreVoli.listaPrenotazioni(nome, cognome);

     for (int i=0; i<prenotazioni.getDimensioni(); i++)
     {
      System.out.println();
      System.out.print(i + ". ");
      System.out.println(prenotazioni.get(i));
      System.out.println();
     }

     scanner.nextLine();
    }
   }
  }
 }
}
