package org.sid.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Secretaire implements Serializable {
	@Id
	@Size(min=4,max=15)
	String login;
	@NotNull
	@Size(min=4,max=20)
	String motDePasse;
	@DecimalMin("0") @DecimalMax("1")
	int active;
	@NotNull
	String nom;
	@NotNull
	String prenom;
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
	
	public Secretaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Secretaire(String login, @NotNull String motDePasse, @DecimalMin("0") @DecimalMax("1") int active,
			@NotNull String nom, @NotNull String prenom, String email, @Size(min = 8, max = 15) int tel,
			List<Role> role) {
		super();
		this.login = login;
		this.motDePasse = motDePasse;
		this.active = active;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.roleu = role;
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
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
