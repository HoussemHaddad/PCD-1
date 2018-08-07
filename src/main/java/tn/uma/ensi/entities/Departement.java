package tn.uma.ensi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Departement {
	@Id @GeneratedValue
	int id;
	String Nom;
	@OneToMany(mappedBy = "Dept")
	List<Secretaire>Secr;
	
	
	@OneToMany(mappedBy = "EnsDept")
	List<Enseignant>Ensig;
	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Departement(String nom) {
		super();
	
		Nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public Departement(int id) {
		super();
		this.id = id;
	}
	
	

}
