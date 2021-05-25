
package com.mycompany.voli_in_elicottero.girellifederico;

import com.mycompany.voli_in_elicottero.girellifederico.time.*;

import java.io.*;
import java.nio.file.*;
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
 /**
  *metodo che restituisce il numero degli elicotteri.
  * @return elicotteri.length
  */
 public int getNumElicotteri() 
 {
  return elicotteri.length;
 }
 /**
  *metodo che consente l'azzeramento delle prenotazioni effettuate.
  * 
  */
 public void azzeraPrenotazioni() 
 {
  for(Elicottero elicottero : elicotteri)
      elicottero.azzeraPrenotazioni();
 }

 public boolean prenota(String nome, String cognome, DataOra dataOra, int passeggeri, boolean trasportoMerce) 
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

  return prenota(idPrenotazione++, nome, cognome, dataOra, passeggeri, elicottero, trasportoMerce);
 }

 private boolean prenota(int id, String nome, String cognome, DataOra dataOra, int passeggeri, int elicottero, boolean trasportoMerce) 
 {
  for(Elicottero eli : elicotteri) 
  {
   Prenotazione pren=eli.trovaPrenotazione(dataOra);
   if(pren==null) continue;

   if(pren.getNome().equals(nome) && pren.getCognome().equals(cognome))
      return false;
  }

  Prenotazione prenotazione=new Prenotazione(id, nome, cognome, dataOra, elicottero, passeggeri, trasportoMerce);
  elicotteri[elicottero].aggiungiPrenotazione(prenotazione);
  return true;
 }
 /**
  *metodo che consente l'annullamento di una prenotazione.
  * 
  */
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
 /**
  *metodo che contiene la lista della prenotazione in base alla data.
  * @return prenotazioni
  */
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
 /**
  *metodo che contiene la lista della prenotazione in base all'elicottero e alla data.
  * @return elicotteri[elicottero].getPrenotazioni(data)
  */	
 public ListaPrenotazioni listaPrenotazioni(int elicottero, Data data) 
 {
  if(elicottero<0 || elicottero>=elicotteri.length)
     throw new IllegalArgumentException("inserire un id elicottero valido.");

  return elicotteri[elicottero].getPrenotazioni(data);
 }
 /**
  *metodo che contiene la lista della prenotazione in base al nome e al cognome.
  * @return prenotazioni
  */
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
 /**metodo che consente l'esportazione della prenotazione nel file CSV.
  *
  * 
  */ 
 public void salvaCSV(String path) throws IOException 
 {
  FileWriter fileWriter=new FileWriter(path);

  for(Elicottero elicottero : elicotteri)
  {
   ListaPrenotazioni prenotazioni=elicottero.getPrenotazioni();
   for(int i=0; i<prenotazioni.getDimensioni(); i++) 
   {
    Prenotazione p=prenotazioni.get(i);

    String str=String.format("%d,%d,%d,%d,%d,%s,%s,%d,%d,%b,\n",
    p.getId(), p.getDataOra().getAnno(), p.getDataOra().getMese(), p.getDataOra().getGiorno(), p.getDataOra().getOra(),
    p.getNome(), p.getCognome(), p.getPasseggeri(), p.getElicottero(), p.getTrasportoMerce());

    fileWriter.write(str);
   }
  }
  fileWriter.flush();
  fileWriter.close();
 }
 /**
  *metodo che consente il caricamento della prenotazione salvata nel file CSV.
  *
  */
 public void caricaCSV(String path) throws IOException 
 {
  idPrenotazione=0;
  azzeraPrenotazioni();

  List<String> lines=Files.readAllLines(new File(path).toPath());

  for(String line : lines) 
  {
   String[] data=line.split(",");

   int id=Integer.parseInt(data[0]);

   int anno=Integer.parseInt(data[1]);
   int mese=Integer.parseInt(data[2]);
   int giorno=Integer.parseInt(data[3]);
   int ora=Integer.parseInt(data[4]);

   String nome=data[5];
   String cognome=data[6];

   int passeggeri=Integer.parseInt(data[7]);
   int elicottero=Integer.parseInt(data[8]);
   boolean trasportoMerce=Boolean.parseBoolean(data[9]);

   prenota(id, nome, cognome, new DataOra(anno, mese, giorno, ora), passeggeri, elicottero, trasportoMerce);
   if(id > idPrenotazione) idPrenotazione = id;
  }
 }
 /**
  *metodo che consente l'esportazione della prenotazione nel file binario.
  * 
  */
 public void salvaBin(String path) throws IOException 
 {
  ByteArrayOutputStream buffer=new ByteArrayOutputStream();

  for (Elicottero elicottero : elicotteri) 
  {
   ListaPrenotazioni prenotazioni=elicottero.getPrenotazioni();

   for (int i=0; i<prenotazioni.getDimensioni(); i++) 
   {
    Prenotazione p=prenotazioni.get(i);

    buffer.write(p.getId());
    buffer.write(p.getDataOra().getAnno());
    buffer.write(p.getDataOra().getMese());
    buffer.write(p.getDataOra().getGiorno()); 
    buffer.write(p.getDataOra().getOra());

    byte[] bytes=p.getNome().getBytes();
    buffer.write(bytes.length);
    buffer.write(bytes);

    bytes=p.getCognome().getBytes();
    buffer.write(bytes.length);
    buffer.write(bytes);

    buffer.write(p.getPasseggeri());
    buffer.write(p.getElicottero());
    buffer.write(p.getTrasportoMerce() ? 1 : 0);
   }
  }
  Files.write(Path.of(path), buffer.toByteArray());
 }
 /**
  *metodo che consente il caricamento della prenotazione salvata nel file binario.
  * 
  */
 public void caricaBin(String path) throws IOException 
 {
  azzeraPrenotazioni();
  byte[] bytes=Files.readAllBytes(Path.of(path));
  ByteArrayInputStream buffer=new ByteArrayInputStream(bytes);

  if(bytes.length==0)
     return;

  while (buffer.available()!=0) 
  {
   int id=buffer.read();
   int anno=buffer.read();
   int mese=buffer.read();
   int giorno=buffer.read();
   int ora=buffer.read();

   int len=buffer.read();
   String nome=new String(buffer.readNBytes(len));

   len=buffer.read();
   String cognome=new String(buffer.readNBytes(len));

   int passeggeri=buffer.read();
   int elicottero=buffer.read();
   boolean trasportoMerce=buffer.read()!=0;

   prenota(id, nome, cognome, new DataOra(anno, mese, giorno, ora), passeggeri, elicottero, trasportoMerce);
   if(id>idPrenotazione) idPrenotazione=id;
  }
 }
 }