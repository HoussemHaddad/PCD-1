package tn.uma.ensi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Soutenance {
	
	@Id @GeneratedValue
	int idSoutenance;
	String nomSujet;
	String infoJury;
	String nomGroupe;
	String listeEtudiants;
	String DateSoutenance;
	public Soutenance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Soutenance(String nomSujet, String infoJury, String nomGroupe, String listeEtudiants,
			String dateSoutenance) {
		super();
		this.nomSujet = nomSujet;
		this.infoJury = infoJury;
		this.nomGroupe = nomGroupe;
		this.listeEtudiants = listeEtudiants;
		DateSoutenance = dateSoutenance;
	}
	public int getIdSoutenance() {
		return idSoutenance;
	}
	public void setIdSoutenance(int idSoutenance) {
		this.idSoutenance = idSoutenance;
	}
	public String getNomSujet() {
		return nomSujet;
	}
	public void setNomSujet(String nomSujet) {
		this.nomSujet = nomSujet;
	}
	public String getInfoJury() {
		return infoJury;
	}
	public void setInfoJury(String infoJury) {
		this.infoJury = infoJury;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public String getListeEtudiants() {
		return listeEtudiants;
	}
	public void setListeEtudiants(String listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}
	public String getDateSoutenance() {
		return DateSoutenance;
	}
	public void setDateSoutenance(String dateSoutenance) {
		DateSoutenance = dateSoutenance;
	}
	
	
	

}
