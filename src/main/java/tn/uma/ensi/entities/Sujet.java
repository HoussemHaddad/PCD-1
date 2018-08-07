package tn.uma.ensi.entities;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Sujet {
	@Id @GeneratedValue
	int idS;
	String titreS;
	String motCle;
	String cluster;
	@OneToMany(mappedBy = "gpSujet")
	List<Groupe> SujetGroupe;
	@ManyToOne(fetch=FetchType.LAZY)
	CentreInteret CISujet;
	@ManyToOne(fetch=FetchType.LAZY)
	Enseignant Sujetens;
	public Sujet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sujet(String titreS, String motCle, List<Groupe> sujetGroupe) {
		super();
		this.titreS = titreS;
		this.motCle = motCle;
		SujetGroupe = sujetGroupe;
	}
	public Sujet(String titreS, String motCle) {
		super();
		this.titreS = titreS;
		this.motCle = motCle;
		
	}
	public int getIdS() {
		return idS;
	}
	public void setIdS(int idS) {
		this.idS = idS;
	}
	public String getTitreS() {
		return titreS;
	}
	public void setTitreS(String titreS) {
		this.titreS = titreS;
	}
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public List<Groupe> getSujetGroupe() {
		return SujetGroupe;
	}
	public void setSujetGroupe(List<Groupe> sujetGroupe) {
		SujetGroupe = sujetGroupe;
	}
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public Sujet(String titreS, String motCle, String cluster, List<Groupe> sujetGroupe) {
		super();
		this.titreS = titreS;
		this.motCle = motCle;
		this.cluster = cluster;
		SujetGroupe = sujetGroupe;
	}
	public CentreInteret getCISujet() {
		return CISujet;
	}
	public void setCISujet(CentreInteret cISujet) {
		CISujet = cISujet;
	}
	public Sujet(String titreS, String motCle, String cluster, List<Groupe> sujetGroupe, CentreInteret cISujet) {
		super();
		this.titreS = titreS;
		this.motCle = motCle;
		this.cluster = cluster;
		SujetGroupe = sujetGroupe;
		CISujet = cISujet;
	}
	public Sujet(String titreS, String motCle, String cluster, List<Groupe> sujetGroupe, CentreInteret cISujet,
			Enseignant sujetens) {
		super();
		this.titreS = titreS;
		this.motCle = motCle;
		this.cluster = cluster;
		SujetGroupe = sujetGroupe;
		CISujet = cISujet;
		Sujetens = sujetens;
	}
	public Enseignant getSujetens() {
		return Sujetens;
	}
	public void setSujetens(Enseignant sujetens) {
		Sujetens = sujetens;
	}
	

}
