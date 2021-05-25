
package com.mycompany.voli_in_elicottero.girellifederico;

import com.mycompany.voli_in_elicottero.girellifederico.time.DataOra;

/**
 *questa classe rappresenta una prenotazione. 
 * 
 * i suoi attributi sono:
 * id <br>
 * DataOra <br>
 * nome <br>
 * cognome <br>
 * passeggeri <br>
 * elicottero <br>
 * trasportoMerce <br>
 * 
 * @author Federico Girelli
 * @version 1.0
 */

public class Prenotazione 
{
 private int id;
 private DataOra dataOra;
 private String nome, cognome;

 private int passeggeri;
 private int elicottero;
 private boolean trasportoMerce;
 /**
  *costruttore della classe prenotazione, consente di instanziare una nuova prenotazione.
  * @param nome nome del cliente che effettua la prenotazione.
  * @param id codice identificativo della prenotazione.
  * @param dataOra data oraria della prenotazione.
  * @param cognome cognome del cliente che effettua la prenotazione.
  * @param passeggeri numero delle persone presenti sull'elicottero prenotato.
  * @param elicottero veicolo che dev'essere prenotato.
  * @param trasportoMerce possibilità o no che l'elicottero possa trasportare della merce.
  */
 public Prenotazione(int id, String nome, String cognome, DataOra dataOra, int elicottero, int passeggeri, boolean trasportoMerce) 
 {
  this.nome=nome;
  this.id=id;
  this.dataOra=dataOra;
  this.cognome=cognome;
  this.passeggeri=passeggeri;
  this.elicottero=elicottero;
  this.trasportoMerce=trasportoMerce;
 }
 /**
  *getter dell'attributo id, metodo che restituisce il valore dell'attributo id.
  * @return id
  */
 public int getId() 
 {
  return id;
 }
 /**
  *getter dell'attributo dataOra, metodo che restituisce il valore dell'attributo dataOra.
  * @return dataOra
  */
 public DataOra getDataOra() 
 {
  return dataOra;
 }
 /**
  *getter dell'attributo nome, metodo che restituisce il valore dell'attributo nome.
  * @return nome
  */
 public String getNome() 
 {
  return nome;
 }
 /**
  *getter dell'attributo cognome, metodo che restituisce il valore dell'attributo cognome.
  * @return cognome
  */
 public String getCognome() 
 {
  return cognome;
 }
 /**
  *getter dell'attributo passeggeri, metodo che restituisce il valore dell'attributo passeggeri.
  * @return passeggeri.
  */
 public int getPasseggeri() 
 {
  return passeggeri;
 }
 /**
  *getter dell'attributo elicottero, metodo che restituisce il valore dell'attributo elicottero.
  * @return elicottero
  */
 public int getElicottero() 
 {
  return elicottero;
 }
 /**
  *getter dell'attributo trasportoMerce, metodo che restituisce il valore dell'attributo trasportoMerce.
  * @return trasportoMerce
  */
 public boolean getTrasportoMerce() 
 {
  return trasportoMerce;
 }
 /**
  *metodo che restituisce una stringa rappresentante gli attributi della prenotazione.
  * @return stringa del nome, cognome, dataOra, elicottero, passeggeri e trasportoMerce
  */
 @Override
 public String toString() 
 {
  return String.format("[%s %s, %s] Elicottero: %d, Passeggeri: %d, Trasporto merci: %b",nome, cognome, dataOra, elicottero, passeggeri, trasportoMerce);
 }
}
