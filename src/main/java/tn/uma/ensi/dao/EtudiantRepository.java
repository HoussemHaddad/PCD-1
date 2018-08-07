package tn.uma.ensi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.ensi.entities.Classe;

import tn.uma.ensi.entities.Etudiant;
import tn.uma.ensi.entities.Filiere;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer>{
	
	//liste Classe
		@Query("select c from Classe c")
		public List<Classe> chercherClasse();

		
		//liste Filiere
				@Query("select f from Filiere f")
				public List<Filiere> chercherFiliere();
				
				
		//recherche filiere par id
			@Query("select f from Filiere f  where f.idF=:x ")
			public Filiere chercherFID(@ Param("x")int mc);
			
			
			//recherche par nom classe
			@Query("select c from Classe c  where c.nomC like :x ")
			public Classe chercherCl(@ Param("x")String mc);	
			
			
			
			//aficher liste etudiants
			@Query("select e from Etudiant e  where e.Cin like :x ")
			public Page<Etudiant> chercher(@ Param("x")String mc,Pageable pageable);
			

			//recherche par id
				@Query("select e from Etudiant e  where e.NumEtud=:x ")
				public Etudiant chercherEID(@ Param("x")int mc);
				
				
				//----------------Gestion Groupes ---------------------
				
				@Query("select e from Etudiant e  ")
				public List<Etudiant> chercherLstEtud();
				//recherche par cin
				@Query("select e from Etudiant e  where e.Cin=:x ")
				public Etudiant chercherECin(@ Param("x")String mc);
				
}
