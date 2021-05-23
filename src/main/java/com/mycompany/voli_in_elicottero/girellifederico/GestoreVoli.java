/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.voli_in_elicottero.girellifederico;

import com.mycompany.voli_in_elicottero.girellifederico.time.*;

import java.io.*;
import java.util.*;

/**
 *
 * @author Federico Girelli
 */

public class GestoreVoli 
{
 private int idPrenotazione;
 private Elicottero[] elicotteri;

 public GestoreVoli(int numElicotteri) 
 {
  idPrenotazione=0;
  elicotteri=new Elicottero[numElicotteri];
  for(int i=0; i<elicotteri.length; i++)
       elicotteri[i]=new Elicottero();
 }

 public int getNumElicotteri() 
 {
  return elicotteri.length;
 }

 public void azzeraPrenotazioni() 
 {
  for(Elicottero elicottero : elicotteri)
      elicottero.azzeraPrenotazioni();
 }

 public boolean prenota(String nome, String cognome, DataOra dataOra, int passegeri, boolean trasportoMerce) 
 {
  int elicottero=-1; 
  for(int i=0; i<elicotteri.length; i++) 
  {
   if(elicotteri[i].trovaPrenotazione(dataOra)==null)
   {
    elicottero=i;
    break;
   }
  }

  //nessun elicottero disponibile
  if(elicottero<0)
     return false;

  return prenota(idPrenotazione++, nome, cognome, dataOra, passegeri, elicottero, trasportoMerce);
 }

 private boolean prenota(int id, String nome, String cognome, DataOra dataOra, int passegeri, int elicottero, boolean trasportoMerce) 
 {
  for(Elicottero eli : elicotteri) 
  {
   Prenotazione pren=eli.trovaPrenotazione(dataOra);
   if(pren==null) continue;

   if(pren.getNome().equals(nome) && pren.getCognome().equals(cognome))
      return false;
  }

  Prenotazione prenotazione=new Prenotazione(id, nome, cognome, dataOra, elicottero, passegeri, trasportoMerce);
  elicotteri[elicottero].aggiungiPrenotazione(prenotazione);
  return true;
 }

 public boolean annullaPrenotazione(String nome, String cognome, DataOra dataOra) 
 {
  for(Elicottero eli : elicotteri) 
  {
   Prenotazione pren=eli.trovaPrenotazione(dataOra);
   if(pren==null) continue;

   if(pren.getNome().equals(nome) && pren.getCognome().equals(cognome))
   {
    eli.rimuoviPrenotazione(dataOra);
    return true;
   }
  }
  return false;
 }

 public ListaPrenotazioni listaPrenotazioni(Data data) 
 {
  ListaPrenotazioni prenotazioni=new ListaPrenotazioni();

  for(Elicottero eli : elicotteri) 
  {
   ListaPrenotazioni pren=eli.getPrenotazioni(data);
   prenotazioni.aggiungi(pren);
  }
  prenotazioni.ordina();

  return prenotazioni;
 }
	
 public ListaPrenotazioni listaPrenotazioni(int elicottero, Data data) 
 {
  if(elicottero<0 || elicottero>=elicotteri.length)
     throw new IllegalArgumentException("inserire un id elicottero valido.");

  return elicotteri[elicottero].getPrenotazioni(data);
 }

 public ListaPrenotazioni listaPrenotazioni(String nome, String cognome) 
 {
  ListaPrenotazioni prenotazioni=new ListaPrenotazioni();

  for(Elicottero eli : elicotteri) 
  {
   ListaPrenotazioni pren=eli.getPrenotazioni(nome, cognome);
   prenotazioni.aggiungi(pren);
  }

  return prenotazioni;
 }
}