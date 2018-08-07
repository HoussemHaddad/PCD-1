package tn.uma.ensi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.ensi.entities.CentreInteret;

public interface CentreInteretRepository extends JpaRepository<CentreInteret,Integer> {
	
	//aficher liste enseignant
		@Query("select c from CentreInteret c  where c.ci like :x ")
		public Page<CentreInteret> chercher(@ Param("x")String mc,Pageable pageable);

		
		@Query("select c from CentreInteret c  ")
		public List<CentreInteret> chercherCI();
		
		@Query("select c from CentreInteret c  where c.ci like :x")
		public CentreInteret chercherCI(@ Param("x")String mc);
}
