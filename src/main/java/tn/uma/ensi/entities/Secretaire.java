package tn.uma.ensi.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
@Entity
public class Secretaire {
	@Id
	@Size(min=4,max=15)
	String login;
	@NotNull
	@Size(min=4,max=20)
	String motDePasse;

	@NotNull
	String nom;
	@NotNull
	String prenom;
	@Email
	String email;
	
	int tel;

 	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "login") },
            inverseJoinColumns = { @JoinColumn(name = "role") })

List<Role> roleu;
 	
 	@ManyToOne(fetch=FetchType.LAZY)
 	Departement Dept;
	
	public Secretaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Departement getDept() {
		return Dept;
	}



	public void setDept(Departement dept) {
		Dept = dept;
	}



	public Secretaire(String login, String motDePasse,  String nom, String prenom, String email, int tel,
			List<Role> roleu, Departement dept) {
		super();
		this.login = login;
		this.motDePasse = motDePasse;
		
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.roleu = roleu;
		Dept = dept;
	}



	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
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
	public List<Role> getRole() {
		return roleu;
	}
	public void setRole(List<Role> role) {
		this.roleu = role;
	}
	
	
}
