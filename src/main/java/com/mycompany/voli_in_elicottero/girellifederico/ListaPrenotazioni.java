/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.voli_in_elicottero.girellifederico;

import java.util.Arrays;

/**
 *
 * @author Federico Girelli
 */

public class ListaPrenotazioni 
{
 private int numPrenotazioni;
 private Prenotazione[] prenotazioni;

 public ListaPrenotazioni() 
 {
  numPrenotazioni=0;
  prenotazioni=new Prenotazione[8];
 }

 public Prenotazione get(int i) 
 {
  return prenotazioni[i];
 }

 public Prenotazione set(int i, Prenotazione prenotazione) 
 {
  return prenotazioni[i]=prenotazione;
 }

 public int getDimensioni() 
 {
  return numPrenotazioni;
 }

 public void aggiungi(Prenotazione prenotazione) 
 {
  if(numPrenotazioni==prenotazioni.length)
     prenotazioni=Arrays.copyOf(prenotazioni, numPrenotazioni*2);
  
  prenotazioni[numPrenotazioni++]=prenotazione;
 }

 public void aggiungi(Prenotazione[] prenotazioni) 
 {
  if((numPrenotazioni+prenotazioni.length)>=prenotazioni.length)
      this.prenotazioni=Arrays.copyOf(this.prenotazioni, Math.max(numPrenotazioni+prenotazioni.length, numPrenotazioni*2));

  for(Prenotazione prenotazione : prenotazioni)
      this.prenotazioni[numPrenotazioni++]=prenotazione;
 }

 public void aggiungi(ListaPrenotazioni prenotazioni) 
 {
  if((numPrenotazioni+prenotazioni.getDimensioni())>=prenotazioni.getDimensioni())
      this.prenotazioni=Arrays.copyOf(this.prenotazioni, Math.max(numPrenotazioni+prenotazioni.getDimensioni(), numPrenotazioni*2));

  for(int i=0; i<prenotazioni.getDimensioni();)
      this.prenotazioni[numPrenotazioni++]=prenotazioni.get(i++);
 }

 public void rimuovi(Prenotazione prenotazione) 
 {
  int indice=-1;
  for(int i=0; i<numPrenotazioni; i++)
  {
   if(prenotazioni[i].equals(prenotazione))
   {
    indice=i;
    break;
   }
  }

  if(indice<0) return;
  
  rimuovi(indice);
 }

 public void rimuovi(int indice) 
 {
  for(int i=indice+1; i<numPrenotazioni;)
  prenotazioni[indice++]=prenotazioni[i++];

  prenotazioni[--numPrenotazioni]=null;
 }

 public void azzera() 
 {
  numPrenotazioni=0;
  Arrays.fill(prenotazioni, null);
 }
}

