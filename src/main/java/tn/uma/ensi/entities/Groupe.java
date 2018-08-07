package tn.uma.ensi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Groupe {
	@Id @GeneratedValue
	int idG;
	String nomG;
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "Groupe_Etudiant",
            joinColumns = { @JoinColumn(name = "idG") },
            inverseJoinColumns = { @JoinColumn(name = "NumEtud") })
	
	List<Etudiant> etudGP;
	
	@ManyToOne(fetch=FetchType.LAZY)
	Sujet gpSujet;
	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Groupe(String nomG, List<Etudiant> etudGP) {
		super();
		this.nomG = nomG;
		this.etudGP = etudGP;
	}
	public int getIdG() {
		return idG;
	}
	public void setIdG(int idG) {
		this.idG = idG;
	}
	public String getNomG() {
		return nomG;
	}
	public void setNomG(String nomG) {
		this.nomG = nomG;
	}
	public List<Etudiant> getEtudGP() {
		return etudGP;
	}
	public void setEtudGP(List<Etudiant> etudGP) {
		this.etudGP = etudGP;
	}
	public Sujet getGpSujet() {
		return gpSujet;
	}
	public void setGpSujet(Sujet gpSujet) {
		this.gpSujet = gpSujet;
	}
	public Groupe(String nomG, List<Etudiant> etudGP, Sujet gpSujet) {
		super();
		this.nomG = nomG;
		this.etudGP = etudGP;
		this.gpSujet = gpSujet;
	}
	
	
	
	

}
