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
     System.out.println("inserire nome e cognome:");
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
    }
   }
  }
 }
}
