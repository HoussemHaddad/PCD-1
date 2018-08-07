package org.sid.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Role  implements Serializable {
	@Id
	String role;
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "roleu")
	List<Secretaire> secretaire;
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(String role, List<Secretaire> secretaire) {
		super();
		this.role = role;
		this.secretaire = secretaire;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Secretaire> getSecretaire() {
		return secretaire;
	}
	public void setSecretaire(List<Secretaire> secretaire) {
		this.secretaire = secretaire;
	}
	
	
	
}
