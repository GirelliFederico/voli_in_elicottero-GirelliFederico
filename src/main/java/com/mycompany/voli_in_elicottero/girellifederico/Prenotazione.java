/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.voli_in_elicottero.girellifederico;

import com.mycompany.voli_in_elicottero.girellifederico.time.DataOra;

/**
 *
 * @author Federico Girelli
 */

public class Prenotazione 
{
 private int id;
 private DataOra dataOra;
 private String nome, cognome;

 private int passeggeri;
 private int elicottero;
 private boolean trasportoMerce;

 public Prenotazione(int id, String nome, String cognome, DataOra dataOra, int elicottero, int passeggeri, boolean trasportoMerce) 
 {
  this.nome = nome;
  this.id = id;
  this.dataOra = dataOra;
  this.cognome = cognome;
  this.passeggeri = passeggeri;
  this.elicottero = elicottero;
  this.trasportoMerce = trasportoMerce;
 }

 public int getId() 
 {
  return id;
 }

 public DataOra getDataOra() 
 {
  return dataOra;
 }

 public String getNome() 
 {
  return nome;
 }

 public String getCognome() 
 {
  return cognome;
 }

 public int getPasseggeri() 
 {
  return passeggeri;
 }

 public int getElicottero() 
 {
  return elicottero;
 }

 public boolean getTrasportoMerce() 
 {
  return trasportoMerce;
 }

 @Override
 public String toString() 
 {
  return String.format("[%s %s, %s] Elicottero: %d, Passeggeri: %d, Trasporto merci: %b",nome, cognome, dataOra, elicottero, passeggeri, trasportoMerce);
 }
}
