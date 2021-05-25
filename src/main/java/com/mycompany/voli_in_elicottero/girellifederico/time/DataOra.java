
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
 
 @Override
 public boolean equals(Object o) 
 {
  if(this==o) return true;
  if(o==null || getClass()!=o.getClass()) return false;
 
  if(!super.equals(o)) return false;
      DataOra dataOra=(DataOra) o;
      
  return ora==dataOra.ora;
 }

 public boolean equals(DataOra o) 
 {
  return super.equals(o) && ora==o.ora;
 }

 @Override
 public String toString() 
 {
  return super.toString() + " : " + ora;
 }
}

