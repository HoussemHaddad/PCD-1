package tn.uma.ensi.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.ensi.entities.Sujet;

public interface SujetRepository extends JpaRepository<Sujet,Integer>{
	//aficher liste Groupe
		@Query("select s from Sujet s  where s.titreS like :x ")
		public Page<Sujet> chercher(@ Param("x")String mc,Pageable pageable);
		
		
		
		//aficher liste Groupe not null
				@Query("select s from Sujet s  where s.titreS like :x and Sujetens is not null ")
				public Page<Sujet> chercherListe(@ Param("x")String mc,Pageable pageable);
				//aficher liste Groupe not null
				@Query("select s from Sujet s  where s.titreS like :x and Sujetens is not null ")
				public List<Sujet> chercherL(@ Param("x")String mc);
				//chercher un suel sujet deja affecter a un enseignant
				@Query("select s from Sujet s  where s.idS like :x and Sujetens is not null ")
				public Sujet chercherSujet(@ Param("x")int idS);
				
				
		// chercher sujet par id
		@Query("select s from Sujet s  where s.idS=:x ")
		public Sujet chercherID(@ Param("x")int mc);
		//liste sujet qui on cluster ?
		@Query("select s from Sujet s  where s.cluster like '?' ")
		public List<Sujet> chercherParCluster();
		//liste sujet qui on cluster parametre
		@Query("select s from Sujet s  where s.cluster like :x ")
		public List<Sujet> chercherParNomCluster(@ Param("x")String mc);
		//liste sujet qui on cluster parametre et Sujetens is null
				@Query("select s from Sujet s  where s.cluster like :x and Sujetens is null")
				public List<Sujet> chercherParNomClusteretNull(@ Param("x")String mc);
}
