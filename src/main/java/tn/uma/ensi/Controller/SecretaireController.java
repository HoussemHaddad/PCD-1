package tn.uma.ensi.Controller;




import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.ensi.dao.SecretaireRepository;
import tn.uma.ensi.entities.Departement;
import tn.uma.ensi.entities.Role;
import tn.uma.ensi.entities.Secretaire;




@Controller
public class SecretaireController {
	@Autowired
	private SecretaireRepository secretaireRepository;
	
	//affichage
	@RequestMapping(value="/ListeSecretaire")
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="size",defaultValue="5")int s
			,@RequestParam(name="motCle",defaultValue="")String mc
			) {
		Page<Secretaire> pageSecretaire=		
				secretaireRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
		
		model.addAttribute("ListSecretaires",pageSecretaire.getContent());
		int[] pages= new int[pageSecretaire.getTotalPages()];
		
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pagecourante", p);
		model.addAttribute("motCle",mc);
		return "ListeSecretaire";
	}
	
	//delete
		@RequestMapping(value="/delete",method=RequestMethod.GET)
		public String delete(String login,String motCle,int page,int size) {
			secretaireRepository.delete(login);
			
			return "redirect:/ListeSecretaire?page="+page+"&size="+size+"&motCle="+motCle;
		}
		
		//ajout
		@RequestMapping(value="/FormSecretaire",method=RequestMethod.GET)
		public String formSecretaire(Model model) {
			model.addAttribute("secretaire",new Secretaire());
			//List<String> listeRole= secretaireRepository.chercherRole();
			//model.addAttribute("rol", listeRole);
			List<Departement> listedept = secretaireRepository.chercherdept();
			model.addAttribute("dept", listedept);
			
			List<String> rol = secretaireRepository.chercherRole();
			model.addAttribute("rol", rol);
			return "FormSecretaire";
		}
		
		

		//save pour ajout et msg
			@RequestMapping(value="/save",method=RequestMethod.POST)
			public String save(Model model,  int id,  String rolee, @Valid  Secretaire secretaire,
					BindingResult bindingResult) {
				if(bindingResult.hasErrors())
				{
					List<Departement> listedept = secretaireRepository.chercherdept();
					model.addAttribute("dept", listedept);
					
					List<String> rol = secretaireRepository.chercherRole();
					model.addAttribute("rol", rol);
			        System.out.println( "There are errors! {}"+ bindingResult );
			      //  return "redirect:/FormSecretaire";
					return "FormSecretaire";
			        }
				System.out.println("++++++++++++"+secretaire.getEmail());
				System.out.println("++++++++++++"+id);
				
			Departement d= secretaireRepository.chercher1dept(id);
				model.addAttribute("nomdept", d.getNom());
				System.out.println("++++++++++++"+d.getNom());
		secretaire.setDept(d);
		
	Role r= new Role();
		r.setRole(rolee);
		List<Role> lr= new ArrayList<Role>();
		lr.add(r);
		secretaire.setRole(lr);
		model.addAttribute("r", rolee);
		
				secretaireRepository.save(secretaire);
			//secretaireRepository.InsertR(secretaire.getLogin(), rolee);
				
				
				
				
				return "ConfirmationSecretaire";
			}

			//edit
			@RequestMapping(value="/EditSecretaire",method=RequestMethod.GET)
			public String edit(Model model,String login) {
				
				Secretaire s1=secretaireRepository.chercherS(login);
				model.addAttribute("secretaire",s1);
				List<Departement> listedept = secretaireRepository.chercherdept();
				model.addAttribute("dept", listedept);
				
				List<String> rol = secretaireRepository.chercherRole();
				model.addAttribute("rol", rol);
				
				return "EditSecretaire";
			}
			
			//action par defaut
			@RequestMapping(value="/Page1")
			public String home1() {
				return "Page1";
			}
			
			
			//action par defaut
			@RequestMapping(value="/")
			public String home() {
				return "redirect:/Page1";
			}
			@RequestMapping(value="/403")
			public String accessDneied() {
				return "403";
			}
			@RequestMapping(value="/login")
			public String login() {
				return "login";
			}
			
			
		
			
			//delete cookies logout

		    
		    
@RequestMapping(value = "/login?logout", method = RequestMethod.POST)

public void logout(HttpServletRequest request,
HttpServletResponse response) {
/* Getting session and then invalidating it */
/*HttpSession session = request.getSession(false);
if (request.isRequestedSessionIdValid() && session != null) {
session.invalidate();
}
Cookie[] cookies = request.getCookies();
for (Cookie cookie : cookies) {
cookie.setMaxAge(0);
cookie.setValue(null);
cookie.setPath("/");
response.addCookie(cookie);
}
*/
	
	SecurityContextHolder.clearContext();
Cookie cookie = new Cookie("JSESSIONID", null);
cookie.setPath(request.getContextPath());
cookie.setMaxAge(0);
response.addCookie(cookie);




}
}
