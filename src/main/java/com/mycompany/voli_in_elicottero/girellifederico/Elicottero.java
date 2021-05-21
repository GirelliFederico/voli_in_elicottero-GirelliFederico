/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.voli_in_elicottero.girellifederico;

import com.mycompany.voli_in_elicottero.girellifederico.time.*;

/**
 *
 * @author Federico Girelli
 */

public class Elicottero 
{
 private ListaPrenotazioni prenotazioni;

 public Elicottero() 
 {
  prenotazioni=new ListaPrenotazioni();
 }

 public boolean aggiungiPrenotazione(Prenotazione prenotazione) 
 {
  DataOra data=prenotazione.getDataOra();

  if(trovaPrenotazione(data)!=null)
     return false;

  prenotazioni.aggiungi(prenotazione);
  return true;
 }

 public boolean rimuoviPrenotazione(DataOra dataOra) 
 {
  int indice=-1; 
  for(int i=0; i<prenotazioni.getDimensioni(); i++)
  {
   if(prenotazioni.get(i).getDataOra().equals(dataOra))
   {
    indice=i;
    break;
   }
  }
 
  if(indice<0) return false;

  prenotazioni.rimuovi(indice);
  return true;
 }

 public Prenotazione trovaPrenotazione(DataOra dataOra) 
 {
  for (int i=0; i<prenotazioni.getDimensioni(); i++) 
  {
   Prenotazione prenotazione=this.prenotazioni.get(i);
   if(dataOra.equals(prenotazione.getDataOra()))
      return prenotazione;
  }
  return null;
 }

 public void azzeraPrenotazioni() 
 {
  prenotazioni.azzera();
 }

 public ListaPrenotazioni getPrenotazioni() 
 {
  return prenotazioni;
 }

 public ListaPrenotazioni getPrenotazioni(Data data) 
 {
  ListaPrenotazioni prenotazioni=new ListaPrenotazioni();

  for (int i=0; i<this.prenotazioni.getDimensioni(); i++) 
  {
   Prenotazione prenotazione=this.prenotazioni.get(i);
   if(prenotazione.getDataOra().equals(data))
      prenotazioni.aggiungi(prenotazione);
  }
  return prenotazioni;
 }

 public ListaPrenotazioni getPrenotazioni(String nome, String cognome) 
 {
  ListaPrenotazioni prenotazioni=new ListaPrenotazioni();

  for (int i=0; i<this.prenotazioni.getDimensioni(); i++) 
  {
   Prenotazione prenotazione=this.prenotazioni.get(i);
   if(prenotazione.getNome().equals(nome) && prenotazione.getCognome().equals(cognome))
      prenotazioni.aggiungi(prenotazione);
  }
  return prenotazioni;
 }
}