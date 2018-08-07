package tn.uma.ensi.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.ensi.dao.GroupeRepository;
import tn.uma.ensi.dao.SujetRepository;

import tn.uma.ensi.entities.Groupe;
import tn.uma.ensi.entities.Sujet;
@Controller
public class SujetController {

	@Autowired
	private SujetRepository sujetRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	
	//ajout
	@RequestMapping(value="/FormSujet",method=RequestMethod.GET)
	public String formSecretaire(Model model) {
		model.addAttribute("sujet",new Sujet());
		
		List<Groupe> listegp = groupeRepository.chercherGp();
		
		List<String> lstetd = new ArrayList<String>();
		String g1;
		String g2 = "(";
		for(int i = 0 ; i < listegp.size(); i++)
		{
			 g1 = listegp.get(i).getIdG()+":"+listegp.get(i).getNomG()+" ";
			 for(int j = 0 ; j < listegp.get(i).getEtudGP().size(); j++)
			 
			 g2=g2+listegp.get(i).getEtudGP().get(j).getCin()+" "
			 +listegp.get(i).getEtudGP().get(j).getNom()+" "
			 +listegp.get(i).getEtudGP().get(j).getPrenom()+",";
			 
			 
			 g1=g1+g2+")";
			 g2 = "(";
			   lstetd.add(g1);
		
	}
		
		for(int i = 0 ; i < lstetd.size(); i++)
			System.out.println(lstetd.get(i));
		
		model.addAttribute("listegp", lstetd);
		
		
		//------------------------------liste 2
	List<Groupe> listegp1 = groupeRepository.chercherGp();
		
		List<String> lstetd1 = new ArrayList<String>();
		String g11;
		String g21 = "(";
		for(int i = 0 ; i < listegp1.size(); i++)
		{
			 g11 = listegp1.get(i).getIdG()+":"+listegp1.get(i).getNomG()+" ";
			 for(int j = 0 ; j < listegp1.get(i).getEtudGP().size(); j++)
			 
			 g21=g21+listegp1.get(i).getEtudGP().get(j).getCin()+" "
			 +listegp1.get(i).getEtudGP().get(j).getNom()+" "
			 +listegp1.get(i).getEtudGP().get(j).getPrenom()+",";
			 
			 
			 g11=g11+g21+")";
			 g21 = "(";
			   lstetd1.add(g11);
		
	}
		
		for(int i = 0 ; i < lstetd1.size(); i++)
			System.out.println(lstetd1.get(i));
		
		model.addAttribute("listegp1", lstetd1);
		
		//----------------------------------liste 3
		
List<Groupe> listegp2 = groupeRepository.chercherGp();
		
		List<String> lstetd2 = new ArrayList<String>();
		String g12;
		String g22 = "(";
		for(int i = 0 ; i < listegp2.size(); i++)
		{
			 g12 = listegp2.get(i).getIdG()+":"+listegp2.get(i).getNomG()+" ";
			 for(int j = 0 ; j < listegp2.get(i).getEtudGP().size(); j++)
			 
			 g22=g22+listegp2.get(i).getEtudGP().get(j).getCin()+" "
			 +listegp2.get(i).getEtudGP().get(j).getNom()+" "
			 +listegp2.get(i).getEtudGP().get(j).getPrenom()+",";
			 
			 
			 g12=g12+g22+")";
			 g22 = "(";
			   lstetd2.add(g12);
		
	}
		
		for(int i = 0 ; i < lstetd2.size(); i++)
			System.out.println(lstetd2.get(i));
		
		model.addAttribute("listegp2", lstetd2);
		
		
		
		
		
		return "Sujet/FormSujet";
	}
	
	//--------Save
	//save pour Groupe_d'etudiant
		@RequestMapping(value="/saveSujet",method=RequestMethod.POST)
		public String save(Model model,  String titreS, String motCle, String gp1, String gp11, String gp12) {
			Sujet sujet=new Sujet(titreS,motCle);
			List<Groupe> lstGP = new ArrayList<Groupe>();
			
			
			String[] idG1 = gp1.split(":");
			Groupe Grp1 =groupeRepository.chercherG(Integer.parseInt(idG1[0]));
			Grp1.setGpSujet(sujet);
			
			lstGP.add(Grp1);
			
			
			if(!(gp11.equals("")))
			{
				String[] idG11 = gp11.split(":");
				Groupe Grp11 =groupeRepository.chercherG(Integer.parseInt(idG11[0]));
				Grp11.setGpSujet(sujet);
				
				lstGP.add(Grp11);
				
			}
			if(!(gp12.equals("")))
			{
				String[] idG12 = gp12.split(":");
				Groupe Grp12 =groupeRepository.chercherG(Integer.parseInt(idG12[0]));
				Grp12.setGpSujet(sujet);
				groupeRepository.save(Grp12);
				lstGP.add(Grp12);
				
			}
	
			sujet.setSujetGroupe(lstGP);
			sujet.setCluster("?");
			sujetRepository.save(sujet);
			
			groupeRepository.save(Grp1);
			
			if(!(gp11.equals("")))
			{String[] idG11 = gp11.split(":");
			Groupe Grp11 =groupeRepository.chercherG(Integer.parseInt(idG11[0]));
			Grp11.setGpSujet(sujet);
				groupeRepository.save(Grp11);}
			
			if(!(gp12.equals("")))
			{
				String[] idG12 = gp12.split(":");
				Groupe Grp12 =groupeRepository.chercherG(Integer.parseInt(idG12[0]));
				Grp12.setGpSujet(sujet);
				groupeRepository.save(Grp12);
				
				
			}
	
			return "redirect:/ListeSujet";
		}
	
		//affichage Liste sujets
		@RequestMapping(value="/ListeSujet")
		public String ConsulterCi(Model model,
				@RequestParam(name="page",defaultValue="0") int p,
				@RequestParam(name="size",defaultValue="5")int s
				,@RequestParam(name="motCle",defaultValue="")String mc
				) {
			Page<Sujet> pageSujet=		
					sujetRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
			
			model.addAttribute("Listsjt",pageSujet.getContent());
			int[] pages= new int[pageSujet.getTotalPages()];
			
			
			
			model.addAttribute("pages", pages);
			model.addAttribute("size", s);
			model.addAttribute("pagecourante", p);
			model.addAttribute("motCle",mc);
			return "Sujet/ListeSujet";
		}	
		
		//delete groupe
		@RequestMapping(value="/deleteSjt",method=RequestMethod.GET)
		public String deleteCi(int idS,String motCle,int page,int size) {
			List<Groupe> lstgp=groupeRepository.chercherSjt(idS);
			for(int i = 0 ; i < lstgp.size(); i++)
			{
			lstgp.get(i).setGpSujet(null);	
			groupeRepository.save(lstgp.get(i));
			}
			
				
				
			sujetRepository.delete(idS);
			return "redirect:/ListeSujet?page="+page+"&size="+size+"&motCle="+motCle;
		}
}
