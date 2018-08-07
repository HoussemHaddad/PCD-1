package tn.uma.ensi.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.ensi.dao.CentreInteretRepository;
import tn.uma.ensi.entities.CentreInteret;


@Controller
public class CentreInteretController {
	@Autowired
	private CentreInteretRepository CIRepository;
	
	//ajout
	@RequestMapping(value="/FormCi",method=RequestMethod.GET)
	public String formSecretaire(Model model) {
		model.addAttribute("ci",new CentreInteret());
		
		return "CentreInteretI/FormCI";
	}
	
	
	//save pour ajout 
	@RequestMapping(value="/saveCentreI",method=RequestMethod.POST)
	public String save(Model model,  String ci) {
		
		
	CentreInteret c= new CentreInteret(ci);
	CIRepository.save(c);

	model.addAttribute("ci",ci);

		return "CentreInteretI/ConfirmajoutCI";
	}
	
	
	//affichage
	@RequestMapping(value="/ConsulterLesCI")
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="size",defaultValue="5")int s
			,@RequestParam(name="motCle",defaultValue="")String mc
			) {
		Page<CentreInteret> pageEnseignant=		
				CIRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
		
		model.addAttribute("ListCI",pageEnseignant.getContent());
		int[] pages= new int[pageEnseignant.getTotalPages()];
		
		
		
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pagecourante", p);
		model.addAttribute("motCle",mc);
		return "CentreInteretI/ListeCI";
	}
	
	
	//delete
	@RequestMapping(value="/deleteCIduliste",method=RequestMethod.GET)
	public String delete(int idCi,String motCle,int page,int size) {
		CIRepository.delete(idCi);
		
		return "redirect:/ConsulterLesCI?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
}
