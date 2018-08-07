package tn.uma.ensi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import tn.uma.ensi.entities.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe,Integer>{

	//aficher liste Groupe
	@Query("select g from Groupe g  where g.nomG like :x ")
	public Page<Groupe> chercher(@ Param("x")String mc,Pageable pageable);
	

	
	//aficher liste Groupe
		@Query("select g from Groupe g   ")
		public List<Groupe> chercherGp();
		
		//aficher un groupe par id
		@Query("select g from Groupe g  where g.idG=:x ")
		public Groupe chercherG(@ Param("x")int mc);
		
		
		//aficher un groupe par nom
				@Query("select g from Groupe g  where g.nomG=:x ")
				public Groupe chercherparNomG(@ Param("x")String mc);
		
		//aficher les groupe qui on ce num de sujet
				@Query("select g from Groupe g  where g.gpSujet.idS=:x ")
				public List<Groupe> chercherSjt(@ Param("x")int mc);
}
