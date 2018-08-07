package tn.uma.ensi.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.ensi.entities.Departement;
import tn.uma.ensi.entities.Secretaire;

public interface SecretaireRepository  extends JpaRepository<Secretaire, String>{
	@Query("select s from Secretaire s  where s.login like :x ")
	//@Query("select r.role from Role r where r.secretaire.login like :x ")
	public Page<Secretaire> chercher(@ Param("x")String mc,Pageable pageable);


	
	@Query("select s from Secretaire s  where s.login like :x ")
	public Secretaire chercherS(@ Param("x")String mc);
	
	@Query("select role from Role r")
	public List<String> chercherRole();
	
	
	@Query("select d from Departement d")
	public List<Departement> chercherdept();
	
	
	@Query("select d from Departement d where d.id=:x" )
	public Departement chercher1dept(@ Param("x") int dp);
	
	/*
	@Query("INSERT INTO UserRole (login, role) VALUES (:x, :y)" )
	public void InsertR(@ Param("x") String a,@ Param("y")String b);
	*/
	
	
	
}

