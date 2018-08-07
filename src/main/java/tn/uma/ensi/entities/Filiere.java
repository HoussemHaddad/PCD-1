package tn.uma.ensi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Filiere {
	@Id @GeneratedValue
	int idF;
	String nomF;
	@OneToMany(mappedBy = "etudF")
List<Etudiant> lstEF;
	public Filiere() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Filiere( String nomF, List<Etudiant> lstEF) {
		super();
		
		this.nomF = nomF;
		this.lstEF = lstEF;
	}
	public int getIdF() {
		return idF;
	}
	public void setIdF(int idF) {
		this.idF = idF;
	}
	public String getNomF() {
		return nomF;
	}
	public void setNomF(String nomF) {
		this.nomF = nomF;
	}
	public List<Etudiant> getLstEF() {
		return lstEF;
	}
	public void setLstEF(List<Etudiant> lstEF) {
		this.lstEF = lstEF;
	}
	
	
	
}
