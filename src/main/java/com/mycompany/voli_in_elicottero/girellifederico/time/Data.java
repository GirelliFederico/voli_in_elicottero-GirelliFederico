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

public class Data 
{
 protected int anno, mese, giorno;

 public Data(int anno, int mese, int giorno) 
 {
  if (mese>12 || mese<=0) throw new IllegalArgumentException(mese + " non è un valore valido per 'mese'.");
  if (giorno>31 || giorno<=0) throw new IllegalArgumentException(giorno + " non è un valore valido per 'giorno'.");

  this.anno=anno;
  this.mese=mese;
  this.giorno=giorno;
 }

 public int getAnno() 
 {
  return anno;
 }

 public int getMese() 
 {
  return mese;
 }

 public int getGiorno() 
 {
  return giorno;
 }
 
 public boolean equals(Object o) 
 {
  if (this==o) return true;
  if (o==null || getClass()!=o.getClass()) return false;
  return equals((Data) o);
 }

 public boolean equals(Data o) 
 {
  return anno==o.anno && mese==o.mese && giorno==o.giorno;
 }

 @Override
 public String toString()
 {
  return anno + "/" + mese + "/" + giorno;
 }
}
