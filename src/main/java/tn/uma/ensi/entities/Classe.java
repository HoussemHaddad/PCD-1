package tn.uma.ensi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Classe {
	@Id
	String nomC;
	@OneToMany(mappedBy = "etudC")
	List<Etudiant> lstEC;
	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Classe(String nomC, List<Etudiant> lstEC) {
		super();
		this.nomC = nomC;
		this.lstEC = lstEC;
	}
	public String getNomC() {
		return nomC;
	}
	public void setNomC(String nomC) {
		this.nomC = nomC;
	}
	public List<Etudiant> getLstEC() {
		return lstEC;
	}
	public void setLstEC(List<Etudiant> lstEC) {
		this.lstEC = lstEC;
	}
	
	
	
	

}
