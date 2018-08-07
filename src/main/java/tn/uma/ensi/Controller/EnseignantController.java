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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.ensi.dao.CentreInteretRepository;
import tn.uma.ensi.dao.EnseignantRepository;
import tn.uma.ensi.entities.CentreInteret;
import tn.uma.ensi.entities.Departement;
import tn.uma.ensi.entities.Enseignant;

import tn.uma.ensi.entities.foo;

@Controller
public class EnseignantController {

	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private CentreInteretRepository CIRepository;
	
	//ajout
			@RequestMapping(value="/FormEnseignant",method=RequestMethod.GET)
			public String formSecretaire(Model model) {
				model.addAttribute("enseignant",new Enseignant());
				
				List<Departement> listedept = enseignantRepository.chercherdept();
				model.addAttribute("dept", listedept);
				
				
				return "EnseignantI/FormEnseignant";
			}
			
			
			
			
			//save pour ajout et msg
			@RequestMapping(value="/saveEns",method=RequestMethod.POST)
			public String save(Model model,  int id,   @Valid  Enseignant enseignant,
					BindingResult bindingResult) {
				if(bindingResult.hasErrors())
				{
					List<Departement> listedept = enseignantRepository.chercherdept();
					model.addAttribute("dept", listedept);
				
			        System.out.println( "There are errors! {}"+ bindingResult );
			      
					return "EnseignantI/FormEnseignant";
			        }
				
			Departement d= enseignantRepository.chercher1dept(id);
				model.addAttribute("nomdept", d.getNom());
				
				enseignant.setEnsDept(d);
	
				enseignantRepository.save(enseignant);
	
				return "EnseignantI/ConfirmationEnseignant";
			}
			
			
			//affichage
			@RequestMapping(value="/ListeEnseignant")
			public String index(Model model,
					@RequestParam(name="page",defaultValue="0") int p,
					@RequestParam(name="size",defaultValue="5")int s
					,@RequestParam(name="motCle",defaultValue="")String mc
					) {
				Page<Enseignant> pageEnseignant=		
						enseignantRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
				
				model.addAttribute("ListEnseignants",pageEnseignant.getContent());
				int[] pages= new int[pageEnseignant.getTotalPages()];
				
				
				
				model.addAttribute("pages", pages);
				model.addAttribute("size", s);
				model.addAttribute("pagecourante", p);
				model.addAttribute("motCle",mc);
				return "EnseignantI/ListeEnseignant";
			}
			
			//delete
			@RequestMapping(value="/deleteEns",method=RequestMethod.GET)
			public String delete(int idEns,String motCle,int page,int size) {
				enseignantRepository.delete(idEns);
				
				return "redirect:/ListeEnseignant?page="+page+"&size="+size+"&motCle="+motCle;
			}
			
			//edit
			@RequestMapping(value="/EditEnseignant",method=RequestMethod.GET)
			public String edit(Model model,int idEns) {
				
				Enseignant e1=enseignantRepository.chercherEID(idEns);
				model.addAttribute("enseignant",e1);
				List<Departement> listedept = enseignantRepository.chercherdept();
				model.addAttribute("dept", listedept);
				
				
				
				return "EnseignantI/EditEnseignant";
			}
			//------------------------Centre d'interet---------------
			
			@RequestMapping(value="/AjouterCi")
			public String index(Model model) {
				List<Enseignant> ListEnseignant=enseignantRepository.chercherLstEns();
				List<String> lstens = new ArrayList<String>();
				for(int i = 0 ; i < ListEnseignant.size(); i++)
					   lstens.add(ListEnseignant.get(i).getnCin()+"-"+ListEnseignant.get(i).getNom()+" "+
							   ListEnseignant.get(i).getPrenom());
				
				
				List<CentreInteret> ListCi=enseignantRepository.chercherLstCI();
				
				foo foo = new foo();
				  List<String> checkedItems = new ArrayList<String>();
				/*for(int i = 0 ; i < ListCi.size(); i++)
					checkedItems.add(ListCi.get(i).getCi());
					*/
				 foo.setCheckedItems(checkedItems);
				  model.addAttribute("foo", foo);
				
				model.addAttribute("ListEnseignants",lstens);
				model.addAttribute("ListCi",ListCi);
				return "EnseignantI/AjouterCI";
			}
			
			
			
			//save pour Ens_CI
			@RequestMapping(value="/saveCI",method=RequestMethod.POST)
			public String save(Model model,  String ens ,@ModelAttribute foo foo) {
				List<CentreInteret> lstCi = new ArrayList<CentreInteret>();
				String Ncin=ens.substring(0, 8) ;
				System.out.println("ciin++++++++"+Ncin);
			Enseignant s= enseignantRepository.chercherE(Ncin);
				List<String> checkedItems  = foo.getCheckedItems();
				for(int i = 0 ; i < checkedItems .size(); i++)
					{System.out.println("valeur coucher ++++++++++"+checkedItems.get(i));
					CentreInteret ci = enseignantRepository.chercherCIparN(checkedItems.get(i));
					 lstCi = s.getLstCi();
					lstCi.add(ci);
					s.setLstCi(lstCi);
					}
				//ajouter l'eseignant au liste des  centre d'interet 
				for(int i = 0 ; i < lstCi.size(); i++)
				{
					List<Enseignant> ls=lstCi.get(i).getLstEnsignant();
					ls.add(s);
					
					
					lstCi.get(i).setLstEnsignant(ls);
					CIRepository.save(lstCi.get(i));
					
				}
					
				enseignantRepository.save(s);
				model.addAttribute("cin",Ncin);
				String ch=ens;
				ch = ch.replace(ch.substring(0, 9), "");
				model.addAttribute("info",ch);
				model.addAttribute("ci",checkedItems);
				
				/*
			
			System.out.println("ciin++++++++"+s.getNom());
				//CentreInteret ci = enseignantRepository.chercherCI(idCi);
				//System.out.println("ciin++++++++"+ci.getCi());
				//List<CentreInteret> lstCi = s.getLstCi();
			//	lstCi.add(ci);
			//	s.setLstCi(lstCi);
	
				enseignantRepository.save(s);
				model.addAttribute("cin",Ncin);
			//	model.addAttribute("ci",ci.getCi());
				String ch=ens;
				ch = ch.replace(ch.substring(0, 9), "");
				model.addAttribute("info",ch);
				return "Page1";*/
				return "EnseignantI/ConfirmationCI";
			}
			
			//affichage CI
			@RequestMapping(value="/ConsulterCI")
			public String ConsulterCi(Model model,
					@RequestParam(name="page",defaultValue="0") int p,
					@RequestParam(name="size",defaultValue="5")int s
					,@RequestParam(name="motCle",defaultValue="")String mc
					) {
				Page<Enseignant> pageEnseignant=		
						enseignantRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
				
				model.addAttribute("ListEnseignants",pageEnseignant.getContent());
				int[] pages= new int[pageEnseignant.getTotalPages()];
				
				
				
				model.addAttribute("pages", pages);
				model.addAttribute("size", s);
				model.addAttribute("pagecourante", p);
				model.addAttribute("motCle",mc);
				return "EnseignantI/ConslterCI";
			}	
			
			
			//delete
			@RequestMapping(value="/deleteCI",method=RequestMethod.GET)
			public String deleteCi(int idEns,int idCi,String ci,String motCle,int page,int size) {
				Enseignant ens=enseignantRepository.chercherEID(idEns);
				List<CentreInteret> lstci = ens.getLstCi();
				CentreInteret centri= new CentreInteret(idCi,ci);
				System.out.println("++++++++++++++++"+centri.getCi());
				System.out.println("++++++++++++++++"+centri.getIdCi());
				
			
					for(int i=0; i<lstci.size(); i++) 
				{
					if((lstci.get(i).getCi().equals(ci))&&(lstci.get(i).getIdCi()==idCi))
					{	System.out.println("icii!!!!!!!!!");
					System.out.println("!!!!!!!!"+lstci.get(i).getCi());
					CentreInteret c = new CentreInteret(idCi, ci);
						lstci.remove(i);
						lstci.remove(c);
							
					}
					
					
					
				}
					for(int i=0; i<lstci.size(); i++) 
					{ List<Enseignant> lsens=lstci.get(i).getLstEnsignant();
					lsens.remove(ens);
						lstci.get(i).setLstEnsignant(lsens);
						CIRepository.save(lstci.get(i));
					}
				List<CentreInteret> lc=new ArrayList<CentreInteret>();
				lc.add(centri);
				
				
				ens.setLstCi(lstci);
				enseignantRepository.save(ens);
				return "redirect:/ConsulterCI?page="+page+"&size="+size+"&motCle="+motCle;
			}
			
}
