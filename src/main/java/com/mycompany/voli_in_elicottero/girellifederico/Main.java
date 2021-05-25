
package com.mycompany.voli_in_elicottero.girellifederico;

import com.mycompany.voli_in_elicottero.girellifederico.time.*;

import java.io.*;
import java.nio.file.*;
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
  
  File db=new File("db.txt");
  if(!db.exists()) db.createNewFile();

  try { gestoreVoli.caricaBin("db.txt"); }
  catch (Exception e) { e.printStackTrace(); }
  
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
     try { gestoreVoli.salvaBin("db.txt"); }
     catch (IOException e) { e.printStackTrace(); }
     
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
     
     System.out.println("\nselezionare elicottero (0 - 5): ");
     System.out.printf("", gestoreVoli.getNumElicotteri());
     while(elicottero<0 || elicottero >=gestoreVoli.getNumElicotteri())
     elicottero=scanner.nextInt();

     Data data=null;
     while (data==null) 
     {
      System.out.println("\ninserire data (yy mm dd):");
      int anno=scanner.nextInt();
      int mese=scanner.nextInt();
      int giorno=scanner.nextInt();

      try { data=new Data(anno, mese, giorno); }
      catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
     }

     ListaPrenotazioni prenotazioni=gestoreVoli.listaPrenotazioni(elicottero, data);

     for (int i=0; i<prenotazioni.getDimensioni(); i++) 
     {
      System.out.println();
      System.out.print(i + ". ");
      System.out.println(prenotazioni.get(i));
      System.out.println();
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
    
    case 5 -> 
    {
     Data data=null;
     while (data==null) 
     {
      System.out.println("\ninserire data (yy mm dd):");
      int anno=scanner.nextInt();
      int mese=scanner.nextInt();
      int giorno=scanner.nextInt();

      try { data=new Data(anno, mese, giorno); }
      catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
     }

     ListaPrenotazioni prenotazioni=gestoreVoli.listaPrenotazioni(data);

     for(int i=0; i<prenotazioni.getDimensioni(); i++) 
     {
      System.out.println();
      System.out.print(i + ". ");
      System.out.println(prenotazioni.get(i));
      System.out.println();
     }

     scanner.nextLine();
    }
    
    case 6 -> 
    {
     scanner=new Scanner(System.in);
     System.out.println("\npath: (esempio: prenotazione.txt)");
     Path path=null;

     while(path==null)
     {
      path=Path.of(scanner.nextLine());
      try { Paths.get(String.valueOf(path)); }
      catch (InvalidPathException | NullPointerException ex) { path=null; }
     }

     try 
     {
      gestoreVoli.salvaCSV(path.toString());
      System.out.println("\nsalvataggio avvenuto con successo.\n");
     } 
     catch (IOException e) { System.out.println("\nsalvataggio fallito: " + e.getMessage()); }
     
    }
   }
  }
 }
}
