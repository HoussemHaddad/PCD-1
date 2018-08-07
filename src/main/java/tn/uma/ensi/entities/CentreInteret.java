package tn.uma.ensi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class CentreInteret {
	@Id @GeneratedValue
	int idCi;
	String ci;
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "lstCi")
	List<Enseignant> lstEnsignant;
	@OneToMany(mappedBy = "CISujet")
	List<Sujet> lstSujet;
	public CentreInteret() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CentreInteret(String ci,List<Enseignant> lstEnsignant
			) {
		super();
		this.ci = ci;
		this.lstEnsignant = lstEnsignant;
	}
	
	
	public CentreInteret(int idCi, String ci) {
		super();
		this.idCi = idCi;
		this.ci = ci;
	}
	public int getIdCi() {
		return idCi;
	}
	public void setIdCi(int id) {
		this.idCi = id;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public List<Enseignant> getLstEnsignant() {
		return lstEnsignant;
	}
	public void setLstEnsignant(List<Enseignant> lstEnsignant) {
		this.lstEnsignant = lstEnsignant;
	}
	public CentreInteret(String ci) {
		super();
		this.ci = ci;
	}
	public List<Sujet> getLstSujet() {
		return lstSujet;
	}
	public void setLstSujet(List<Sujet> lstSujet) {
		this.lstSujet = lstSujet;
	}
	public CentreInteret(String ci, List<Enseignant> lstEnsignant, List<Sujet> lstSujet) {
		super();
		this.ci = ci;
		this.lstEnsignant = lstEnsignant;
		this.lstSujet = lstSujet;
	}
	
	

}
