package tn.uma.ensi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
@Entity
public class Enseignant {
	@Id
	@GeneratedValue
	int idEns;
	@Column(unique=true)
	@Size(min=8,max=8)
	@Pattern(regexp = "[0-9]*")
	String nCin;
	@NotNull (message = "{nom.notnull}")
	String nom;
	@NotNull (message = "{prenom.notnull}")
	String prenom;
	String adresse;
	@Email
	String email;
	int tel;
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "CI_Enseignant",
            joinColumns = { @JoinColumn(name = "IdEns") },
            inverseJoinColumns = { @JoinColumn(name = "idCi") })

	List<CentreInteret> lstCi;
	
	@ManyToOne(fetch=FetchType.LAZY)
	Departement EnsDept;
	@OneToMany(mappedBy = "Sujetens")
	List<Sujet> Sujet;
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Enseignant(String nCin, String nom, String prenom, String adresse, String email, int tel,
			List<CentreInteret> lstCi
			, Departement ensDept) {
		super();
		this.nCin = nCin;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.lstCi = lstCi;
		EnsDept = ensDept;
	}

	public Enseignant(List<CentreInteret> lstCi
			) {
		super();
		
		this.lstCi = lstCi;
		
	}


	public Departement getEnsDept() {
		return EnsDept;
	}



	public void setEnsDept(Departement ensDept) {
		EnsDept = ensDept;
	}



	public int getIdEns() {
		return idEns;
	}

	public void setIdEns(int idEns) {
		this.idEns = idEns;
	}

	public String getnCin() {
		return nCin;
	}

	public void setnCin(String nCin) {
		this.nCin = nCin;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public List<CentreInteret> getLstCi() {
		return lstCi;
	}
	public void setLstCi(List<CentreInteret> lstCi) {
		this.lstCi = lstCi;
	}



	public Enseignant(String nCin, String nom, String prenom, String adresse, String email, int tel,
			List<CentreInteret> lstCi, Departement ensDept, List<tn.uma.ensi.entities.Sujet> sujet) {
		super();
		this.nCin = nCin;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.lstCi = lstCi;
		EnsDept = ensDept;
		Sujet = sujet;
	}



	public List<Sujet> getSujet() {
		return Sujet;
	}



	public void setSujet(List<Sujet> sujet) {
		Sujet = sujet;
	}
	
	

}
