package tn.uma.ensi.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.ensi.dao.EtudiantRepository;
import tn.uma.ensi.dao.GroupeRepository;

import tn.uma.ensi.entities.Classe;

import tn.uma.ensi.entities.Etudiant;
import tn.uma.ensi.entities.Filiere;
import tn.uma.ensi.entities.Groupe;

@Controller
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	//ajout
	@RequestMapping(value="/FormEtudiant",method=RequestMethod.GET)
	public String formSecretaire(Model model) {
		model.addAttribute("etudiant",new Etudiant());
		
		List<String> jr = new ArrayList<String>();
		for(int i=1;i<=31;i++)
		jr.add(""+i);
		
		
		model.addAttribute("jr", jr);
		
		
		List<String> mois = new ArrayList<String>();
		for(int i=1;i<=12;i++)
		mois.add(""+i);
		
		
		model.addAttribute("mois", mois);
		
		List<Classe> listeclasse = etudiantRepository.chercherClasse();
		model.addAttribute("classe", listeclasse);
		
		List<Filiere> listeF = etudiantRepository.chercherFiliere();
		model.addAttribute("filiere", listeF);
		
		return "EtudiantI/FormEtudiant";
	}
	
	

	//save pour ajout et msg
	@RequestMapping(value="/saveEtud",method=RequestMethod.POST)
	public String save(Model model, String nomC, int idf, String jr,String mois,String annee1,  @Valid  Etudiant etudiant,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors())
		{
			List<String> jr1 = new ArrayList<String>();
			for(int i=1;i<=31;i++)
			jr1.add(""+i);
			
			
			model.addAttribute("jr", jr1);
			
			
			List<String> mois1 = new ArrayList<String>();
			for(int i=1;i<=12;i++)
			mois1.add(""+i);
			
			
			model.addAttribute("mois", mois1);
			List<Classe> listeclasse = etudiantRepository.chercherClasse();
			model.addAttribute("classe", listeclasse);
			
			List<Filiere> listeF = etudiantRepository.chercherFiliere();
			model.addAttribute("filiere", listeF);
		
	        System.out.println( "There are errors! {}"+ bindingResult );
	      
			return "EtudiantI/FormEtudiant";
	        }
		System.out.println("+++++++++++++++++++++"+annee1);
		String datenaiss1=jr+"/"+mois+"/"+annee1;
		Filiere f=etudiantRepository.chercherFID(idf);
		etudiant.setEtudF(f);
		model.addAttribute("filiere", f.getNomF());
		Classe c=etudiantRepository.chercherCl(nomC);
		
		etudiant.setEtudC(c);
		etudiant.setDatenaiss(datenaiss1);
		
		model.addAttribute("classe", c.getNomC());
		
		etudiantRepository.save(etudiant);

		return "EtudiantI/ConfirmationEtudiant";
		
	}
	
	// msg etudiant
		@RequestMapping(value="/msjEtud",method=RequestMethod.POST)
		public String save(Model model, String nomC,int idf,  @Valid  Etudiant etudiant,
				BindingResult bindingResult) {
			if(bindingResult.hasErrors())
			{
				List<String> jr1 = new ArrayList<String>();
				for(int i=1;i<=31;i++)
				jr1.add(""+i);
				
				
				model.addAttribute("jr", jr1);
				
				
				List<String> mois1 = new ArrayList<String>();
				for(int i=1;i<=12;i++)
				mois1.add(""+i);
				
				
				model.addAttribute("mois", mois1);
				List<Classe> listeclasse = etudiantRepository.chercherClasse();
				model.addAttribute("classe", listeclasse);
				
				List<Filiere> listeF = etudiantRepository.chercherFiliere();
				model.addAttribute("filiere", listeF);
			
		        System.out.println( "There are errors! {}"+ bindingResult );
		      
				return "EtudiantI/EditEtudiant";
		        }
			
			
			Filiere f=etudiantRepository.chercherFID(idf);
			etudiant.setEtudF(f);
			model.addAttribute("filiere", f.getNomF());
			Classe c=etudiantRepository.chercherCl(nomC);
			
			etudiant.setEtudC(c);
		
			
			model.addAttribute("classe", c.getNomC());
			
			etudiantRepository.save(etudiant);

			return "EtudiantI/ConfirmationEtudiant";
			
		}
	
	//affichage
	@RequestMapping(value="/ListeEtudiant")
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="size",defaultValue="5")int s
			,@RequestParam(name="motCle",defaultValue="")String mc
			) {
		Page<Etudiant> pageEtudiant=		
				etudiantRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
		
		model.addAttribute("ListEtudiants",pageEtudiant.getContent());
		int[] pages= new int[pageEtudiant.getTotalPages()];
		
		
		
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pagecourante", p);
		model.addAttribute("motCle",mc);
		return "EtudiantI/ListeEtudiant";
	}
	
	
	//delete
	@RequestMapping(value="/deleteEtud",method=RequestMethod.GET)
	public String delete(int NumEtud,String motCle,int page,int size) {
		etudiantRepository.delete(NumEtud);
		
		return "redirect:/ListeEtudiant?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	
	//edit
	@RequestMapping(value="/EditEtudiant",method=RequestMethod.GET)
	public String edit(Model model,int NumEtud) {
		
		Etudiant e1=etudiantRepository.chercherEID(NumEtud);
		model.addAttribute("etudiant",e1);
		List<Classe> listeclasse = etudiantRepository.chercherClasse();
		model.addAttribute("classe", listeclasse);
		
		List<Filiere> listeF = etudiantRepository.chercherFiliere();
		model.addAttribute("filiere", listeF);
		
		
		return "EtudiantI/EditEtudiant";
	}
	
	
	//---------------------------------Gestion des groupes ----------------------------
	
	@RequestMapping(value="/AjouterGp")
	public String index(Model model) {
		List<Etudiant> ListEtudiant=etudiantRepository.chercherLstEtud();
		List<String> lstetd = new ArrayList<String>();
		for(int i = 0 ; i < ListEtudiant.size(); i++)
			   lstetd.add(ListEtudiant.get(i).getCin()+"-"+ListEtudiant.get(i).getNom()+" "+
					   ListEtudiant.get(i).getPrenom());
		model.addAttribute("Groupe",new Groupe());
		model.addAttribute("ListEtudiant", lstetd);
		
		return "EtudiantI/AjouterGP";
	}
	
	
	
	
	

	//save pour Groupe_d'etudiant
	@RequestMapping(value="/saveGP",method=RequestMethod.POST)
	public String save(Model model,  String etd1, String etd2, String etd3, String nomG) {
		String cin1=etd1.substring(0, 8) ;
		//System.out.println("ciin++++++++"+cin1);
	Etudiant e1= etudiantRepository.chercherECin(cin1);
	List<Etudiant> lstetd = new ArrayList<Etudiant>();
	lstetd.add(e1);
	Groupe G1= new Groupe(nomG,lstetd);
	
	if(!(etd2.equals("")))
			{
		String cin2=etd2.substring(0, 8) ;
		//System.out.println("ciin++++++++"+cin1);
	Etudiant e2= etudiantRepository.chercherECin(cin2);
	lstetd.add(e2);
	G1.setEtudGP(lstetd);
			}
	
	if(!(etd3.equals("")))
	{
String cin3=etd3.substring(0, 8) ;
//System.out.println("ciin++++++++"+cin1);
Etudiant e3= etudiantRepository.chercherECin(cin3);
lstetd.add(e3);
G1.setEtudGP(lstetd);
	}
	
	groupeRepository.save(G1);
	

		return "redirect:/ConsulterGp";
	}
	
	
	
	//affichage Groupes
	@RequestMapping(value="/ConsulterGp")
	public String ConsulterCi(Model model,
			@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="size",defaultValue="5")int s
			,@RequestParam(name="motCle",defaultValue="")String mc
			) {
		Page<Groupe> pageGp=		
				groupeRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
		
		model.addAttribute("Listgrp",pageGp.getContent());
		int[] pages= new int[pageGp.getTotalPages()];
		
		
		
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pagecourante", p);
		model.addAttribute("motCle",mc);
		return "EtudiantI/ConsulterGP";
	}	
	
	//delete groupe
	@RequestMapping(value="/deleteGP",method=RequestMethod.GET)
	public String deleteCi(int idG,String motCle,int page,int size) {
		groupeRepository.delete(idG);
		return "redirect:/ConsulterGp?page="+page+"&size="+size+"&motCle="+motCle;
	}
}
