package tn.uma.ensi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
@Entity
public class Etudiant {
	@Id @GeneratedValue
	int NumEtud;
	@Column(unique=true)
	@Size(min=8,max=8)
	@Pattern(regexp = "[0-9]*")
	String Cin;
	String nom;
	@NotNull (message = "{prenom.notnull}")
	String prenom;
	String Datenaiss;
	String adresse;
	@Email
	String email;
	int tel;
	@ManyToOne(fetch=FetchType.LAZY)
	Filiere etudF;
	@ManyToOne(fetch=FetchType.LAZY)
	Classe etudC;
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "etudGP")
	List<Groupe> GpEtud;
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etudiant(String cin, String nom, String prenom, String datenaiss, String adresse, String email, int tel,
			Filiere etudF, Classe etudC) {
		super();
		Cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		Datenaiss = datenaiss;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.etudF = etudF;
		this.etudC = etudC;
	}
	
	
	public Etudiant(String cin, String nom, String prenom, String datenaiss, String adresse, String email, int tel,
			Filiere etudF, Classe etudC, List<Groupe> gpEtud) {
		super();
		Cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		Datenaiss = datenaiss;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.etudF = etudF;
		this.etudC = etudC;
		GpEtud = gpEtud;
	}
	
	
	public List<Groupe> getGpEtud() {
		return GpEtud;
	}
	public void setGpEtud(List<Groupe> gpEtud) {
		GpEtud = gpEtud;
	}
	public int getNumEtud() {
		return NumEtud;
	}
	public void setNumEtud(int numEtud) {
		NumEtud = numEtud;
	}
	public String getCin() {
		return Cin;
	}
	public void setCin(String cin) {
		Cin = cin;
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
	public String getDatenaiss() {
		return Datenaiss;
	}
	public void setDatenaiss(String datenaiss) {
		Datenaiss = datenaiss;
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
	public Filiere getEtudF() {
		return etudF;
	}
	public void setEtudF(Filiere etudF) {
		this.etudF = etudF;
	}
	public Classe getEtudC() {
		return etudC;
	}
	public void setEtudC(Classe etudC) {
		this.etudC = etudC;
	}

	
	
}
