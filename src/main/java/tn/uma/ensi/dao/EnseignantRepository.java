package tn.uma.ensi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.ensi.entities.CentreInteret;
import tn.uma.ensi.entities.Departement;
import tn.uma.ensi.entities.Enseignant;


public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {

	//liste departement
	@Query("select d from Departement d")
	public List<Departement> chercherdept();
	
	//chercher un dept
	@Query("select d from Departement d where d.id=:x" )
	public Departement chercher1dept(@ Param("x") int dp);
	
	//aficher liste enseignant
	@Query("select e from Enseignant e  where e.nCin like :x ")
	public Page<Enseignant> chercher(@ Param("x")String mc,Pageable pageable);
	
	
	//recherche par cin
	@Query("select e from Enseignant e  where e.nCin like :x ")
	public Enseignant chercherE(@ Param("x")String mc);
	
	//recherche par id
		@Query("select e from Enseignant e  where e.idEns=:x ")
		public Enseignant chercherEID(@ Param("x")int mc);
		
		//----------------------Centre d'interet-----------------
		@Query("select e from Enseignant e  ")
		public List<Enseignant> chercherLstEns();
		
		@Query("select ci from CentreInteret ci  ")
		public List<CentreInteret> chercherLstCI();
		
		//chercher ci par id
		@Query("select ci from CentreInteret ci   where ci.idCi=:x ")
		public CentreInteret chercherCI(@ Param("x")int mc);
		
		
		//chercher ci par nom
		
		@Query("select ci from CentreInteret ci   where ci.ci like :x ")
		public CentreInteret chercherCIparN(@ Param("x")String mc);
}
