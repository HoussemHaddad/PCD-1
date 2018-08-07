package tn.uma.ensi.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.ensi.entities.Soutenance;

public interface SoutenanceRepository   extends JpaRepository<Soutenance,Integer>
{
	@Query("select s from Soutenance s  where s.nomSujet like :x ")
	//@Query("select r.role from Role r where r.secretaire.login like :x ")
	public Page<Soutenance> chercher(@ Param("x")String mc,Pageable pageable);
	
	@Query("select s from Soutenance s  where s.idSoutenance like :x ")
	//@Query("select r.role from Role r where r.secretaire.login like :x ")
	public Soutenance chercherSout(@ Param("x")int mc);
}
