/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.voli_in_elicottero.girellifederico.time;

import java.util.Objects;

/**
 *
 * @author Federico Girelli
 */

public class DataOra extends Data
{
 protected int ora;
 
 public DataOra(int anno, int mese, int giorno, int ora) 
 {
  super(anno, mese, giorno);

  if(ora>24 || ora<=0)
     throw new IllegalArgumentException(ora + " non è un valore valido per 'ora'.");

  this.ora=ora;
 }

 public int getOra() 
 {
  return ora;
 }

 public Data getData() 
 {
  return new Data(anno, mese, giorno);
 }
}
