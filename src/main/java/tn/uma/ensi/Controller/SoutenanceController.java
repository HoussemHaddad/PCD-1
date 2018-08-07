package tn.uma.ensi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.ensi.Service.NotificationService;
import tn.uma.ensi.dao.CentreInteretRepository;
import tn.uma.ensi.dao.EnseignantRepository;
import tn.uma.ensi.dao.GroupeRepository;
import tn.uma.ensi.dao.SoutenanceRepository;
import tn.uma.ensi.dao.SujetRepository;
import tn.uma.ensi.entities.CentreInteret;
import tn.uma.ensi.entities.Enseignant;
import tn.uma.ensi.entities.Etudiant;
import tn.uma.ensi.entities.Groupe;

import tn.uma.ensi.entities.Soutenance;
import tn.uma.ensi.entities.Sujet;
//import required classes
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.Classifier;





import java.io.File;

import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;




import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;


@Controller
public class SoutenanceController {
	private static String Modele="C:\\Users\\Houssem\\Desktop\\PCD\\new\\PCD3.model";
	private static Instances test;
	private static Classifier cls;
	@Autowired
	private SujetRepository sujetRepository;
	@Autowired
	private EnseignantRepository enseignantRepository;
	@Autowired
	private CentreInteretRepository CIRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private SoutenanceRepository soutenanceRepository;
	@Autowired
	private NotificationService notificationService;
	// page tourner l'algo
	@RequestMapping(value="/classer")
	public String home1() {
		return "Soutenance/DemarerAlgo";
	}
	//--------------------------------------------------Classer les sujet ------------------------------------------------
	
	//fonctionemment d'algo
	@RequestMapping(value="/affecter")
	public String algo(Model model)
	{
		List<Sujet> lstsjt = sujetRepository.chercherParCluster();
		 final String chemin = "C:\\\\Users\\\\Houssem\\\\Desktop\\\\PCD\\\\new\\\\tmp.arff";
	        final File fichier =new File(chemin); 
	        try {
	            // Creation du fichier
	            fichier .createNewFile();
	            // creation d'un writer (un écrivain)
	            final FileWriter writer = new FileWriter(fichier);
	            try {
	            	writer.write("@relation Training \r\n");
	            	
	            	 writer.write("@attribute nom string\r\n");
	            	 writer.write("@attribute mot_cle string \r\n");
	            	 writer.write("@attribute classes {Smart-ENSI,E-Govenment,Machine-Learning,Finance,Data-Mining,Developpement,Blockchain-Technology,Cloud-Computing}\r\n" + 
	            	 		"	            	 \r\n");
	            	
	            	 writer.write("@data \r\n");
	            	 
	            	 for(int i = 0 ; i < lstsjt.size(); i++)
	         		{
	            	System.out.println("+++++++++++++++++++++++"+lstsjt.get(i).getTitreS());;
	            		 writer.write('"'+lstsjt.get(i).getTitreS()+'"'+','+'"'+lstsjt.get(i).getMotCle()+'"'+','+lstsjt.get(i).getCluster()+"\r\n");
	               
	         		}
	            } finally {
	                // quoiqu'il arrive, on ferme le fichier
	                writer.close();
	            }
	        } catch (Exception e) {
	            System.out.println("Impossible de creer le fichier");
	        }
		
		
		
		
		
		
		
	
		try {
			if(1==1)
			{
				 cls = (Classifier) weka.core.SerializationHelper.read("C:\\Users\\Houssem\\Desktop\\PCD\\new\\PCD3.model");
					
				 DataSource source = new DataSource("C:\\\\Users\\\\Houssem\\\\Desktop\\\\PCD\\\\new\\\\tmp.arff");
				    Instances test = source.getDataSet();
				    test.setClassIndex(test.numAttributes()-1);
				 
					//test = new Instances(new BufferedReader(new FileReader("C:\\Users\\Houssem\\Desktop\\PCD\\new\\test.arff")));
					//int lastIndex = test.numAttributes() - 1;
			      //  test.setClassIndex(lastIndex);
				    
				    List<Sujet> listeSujet = new ArrayList<Sujet>();
				    List<String> lstclass = new ArrayList<String>();
				    CentreInteret centreI = new CentreInteret();
			            for(int i=0; i<test.numInstances(); i++)
			            {
			            
			                    double index = cls.classifyInstance(test.instance(i));
			                    String className = test.classAttribute().value((int)index);
			                    lstclass.add(className);
			                    model.addAttribute("lstclass", lstclass);
			                    
			                    		
			               
			            }
			            
			            //ajout la classe au sujet 
			            for(int i = 0 ; i < lstsjt.size(); i++)
		         		{
			            	lstsjt.get(i).setCluster(lstclass.get(i));
			            	lstsjt.get(i).setCISujet(CIRepository.chercherCI(lstclass.get(i)));
			            	sujetRepository.save(lstsjt.get(i));
			            	
		         		}
			            
			            
			            for(int i = 0 ; i < lstclass.size(); i++)
		         		{System.out.println(lstclass.get(i));
	                    listeSujet = sujetRepository.chercherParNomCluster(lstclass.get(i))  ;
	                    List<Sujet> lss= centreI.getLstSujet();
	                    for(int j = 0 ; j < listeSujet.size(); j++)
		         		{
	                    	lss.add(listeSujet.get(j));
	                    	//System.out.println(listeSujet.get(j).getTitreS());
		         		}
	                    centreI = CIRepository.chercherCI("Developpement");
	                   // List<Sujet> lss= centreI.getLstSujet();
	                
	                    centreI.setLstSujet(lss);
	                    CIRepository.save(centreI);
	                    
			            	
			            	
		         		}
			  
			}
			
			
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/affectation";
	}
	
	
	
	//affectation
	@RequestMapping(value="/affectation")
	public String affectation(Model model)
	{
		List<CentreInteret> lstCi=  CIRepository.chercherCI();
		model.addAttribute("lstci", lstCi);
		for(int j = 0 ; j < lstCi.size(); j++)
 		{for(int i = 0 ; i < lstCi.get(j).getLstSujet().size(); i++)
 		{
			System.out.println(lstCi.get(j).getLstSujet().get(i).getTitreS());
 		}}
		
		return "Soutenance/ResultatAlgo";
	}
	
	//---------------------------------------Base d'apprenttisage-------------------------------------------------
	//selection fichier de base 
		@RequestMapping(value="/apprentissage")
		public String apprentissage(Model model)
		{return "Soutenance/apprentissage";
		}
	
	//mettre le fichier et faire l'apprentissage
		
		
		@RequestMapping(value="/apprentissagesave",method=RequestMethod.POST)
		public String Apprentissageeffectue(Model model,String chemin) {
			System.out.println(chemin);
			
			try {
				/*Instances train = new Instances(new BufferedReader(new FileReader("C:\\Users\\Houssem\\Desktop\\PCD\\new\\Train.arff")));
				int lastIndex = train.numAttributes() - 1;
		        train.setClassIndex(lastIndex);
				*/
				
				 DataSource source = new DataSource(chemin);
				    Instances train = source.getDataSet();
				    //set class index to the last attribute
				    train.setClassIndex(train.numAttributes()-1);
				
				
				Classifier j48tree = new J48();
				
				StringToWordVector filter = new StringToWordVector();
			    filter.setInputFormat(train);
			    FilteredClassifier fc = new FilteredClassifier();
			    fc.setFilter(filter);
			    fc.setClassifier(j48tree);
				fc.buildClassifier(train);
				weka.core.SerializationHelper.write(Modele, fc);
				
				 
			} catch ( Exception e) {
				e.printStackTrace();
			}
			
			
			
			return "Soutenance/finApprentissage";
		}
	
		//--------------------------------------------------affectation sujet enseignant------------------------------------------------
		
		// faire l'affectation Page 1 selection du CI
		@RequestMapping(value="/choisirCI")
		public String ChoisirCI(Model model)
		{
			List<CentreInteret> lstCi=  CIRepository.chercherCI();
			model.addAttribute("lstci", lstCi);
			
			return "Soutenance/SelectionCI";
		}
		
		// affecter un sujet a un eseingnant
		
		@RequestMapping(value="/SujetEnseignant")
		public String SujetEns(Model model,String nomci)
		{
			
			
			CentreInteret Enseignant=  CIRepository.chercherCI(nomci);
			List<Enseignant> lsten= Enseignant.getLstEnsignant();
			model.addAttribute("lstEnseignant", lsten);
			List<Sujet> sujet = sujetRepository.chercherParNomClusteretNull(nomci);
			model.addAttribute("lstsujet", sujet);
			  for(int i = 0 ; i < sujet.size(); i++)
       		System.out.println(sujet.get(i).getIdS()+"+++++"+sujet.get(i).getTitreS());
			
			return "Soutenance/SujetEnseignant";
		}
		
		
		
		
		// Traitement d'affectation un ens a un sujet
		@RequestMapping(value="/AffecterEnsprSujet")
		public String AffecterEnsprSujet(Model model,String nomens,String nomsjt)
		{
		String Cin =(nomens.substring(0,8));
		String[] decoupe = nomsjt.split(" ");
		
		Enseignant ens = enseignantRepository.chercherE(Cin);
		Sujet sjt=sujetRepository.chercherID(Integer.parseInt(decoupe[0]));

sjt.setSujetens(ens);
sujetRepository.save(sjt);
model.addAttribute("sujet", sjt);
model.addAttribute("ens", ens);
		
			return "Soutenance/ConfirmAffectationjury";
		}
		//--------------------------------------Lister les sujet affecter avec jury-----------
		
		@RequestMapping(value="/ConsulterAffectation")
		public String ConsulterAffectation(Model model,@RequestParam(name="page",defaultValue="0") int p,
				@RequestParam(name="size",defaultValue="5")int s
				,@RequestParam(name="motCle",defaultValue="")String mc
				) {
		
		Page<Sujet> lstsujet = sujetRepository.chercherListe("%"+mc+"%",new  PageRequest(p,s));
		
		
		int[] pages= new int[lstsujet.getTotalPages()];
		
		
		
		List<Sujet> lsts = sujetRepository.chercherL("%"+mc+"%");/*
		List<String> lstetd = new ArrayList<String>();
		String g1;
		String g2 = "(";
		for(int i = 0 ; i < lsts.get(i).getSujetGroupe().size(); i++)
		{
			 g1 = lsts.get(i).getSujetGroupe().get(i).getIdG()+":"+lsts.get(i).getSujetGroupe().get(i).getNomG()+" ";
			 for(int j = 0 ; j < lsts.get(i).getSujetGroupe().get(i).getEtudGP().size(); j++)
			 
			 g2=g2+lsts.get(i).getSujetGroupe().get(i).getEtudGP().get(j).getCin()+" "
			 +lsts.get(i).getSujetGroupe().get(i).getEtudGP().get(j).getNom()+" "
			 +lsts.get(i).getSujetGroupe().get(i).getEtudGP().get(j).getPrenom()+",";
			 
			 
			 g1=g1+g2+")";
			   lstetd.add(g1);
		
	}
	model.addAttribute("listegp", lstetd);
		*/
		
		model.addAttribute("lstsujet", lstsujet.getContent());
		
		
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pagecourante", p);
		model.addAttribute("motCle",mc);
		
		
			return "Soutenance/ConsulterAffectation";
		}
		
		
		//Ajouter la date du soutenance
		
		@RequestMapping(value="/AjouterDate")
		public String ConsulterAffectation(Model model,String idS,String idG
				) {
		
			Sujet sujet = sujetRepository.chercherSujet(Integer.parseInt(idS));
			Enseignant ens = enseignantRepository.chercherEID(sujet.getSujetens().getIdEns());
			Groupe grp = groupeRepository.chercherG(Integer.parseInt(idG));
			String S="(";
			for (int i=0 ; i<grp.getEtudGP().size();i++)
			{
				S= S+' '+grp.getEtudGP().get(i).getCin()+' '+grp.getEtudGP().get(i).getNom()+' '+grp.getEtudGP().get(i).getPrenom()+",";
			}
			S=S+")";
			model.addAttribute("S", S);
			model.addAttribute("sujet",sujet);
			model.addAttribute("ens",ens);
			model.addAttribute("grp",grp);
			
			List<String> jr = new ArrayList<String>();
			for(int i=1;i<=31;i++)
			jr.add(""+i);
			
			
			model.addAttribute("jr", jr);
			
			
			List<String> mois = new ArrayList<String>();
			for(int i=1;i<=12;i++)
			mois.add(""+i);
			
			
			model.addAttribute("mois", mois);
			
		System.out.println(idS+"+++++++++++"+idG);
			return "Soutenance/PreparerPlanning";
		}
		
		//----enregistrer le planing en table ---
		
		@RequestMapping(value="/savePlaning")
		public String savePlaning(Model model, String nomS,String jury, String nomG,String membre,String jr,String mois,String annee1
				) {	
			//System.out.println(nomS+"++++"+jury+"+++"+nomG+"+++"+membre+"+++"+jr+"++"+mois+"+++"+annee1);
			String date = jr+"/"+mois+"/"+annee1;
			Soutenance sout = new Soutenance(nomS,jury,nomG,membre,date);
			soutenanceRepository.save(sout);
			String mod =(nomS+"++++"+jury+"+++"+nomG+"+++"+membre+"+++"+jr+"/"+mois+"/"+annee1);
			
			
			model.addAttribute("nomS", nomS);
			model.addAttribute("nomG", nomG);
			model.addAttribute("jury", jury);
			model.addAttribute("membre", membre);
			model.addAttribute("date", date);
			return "Soutenance/ConfirmSoutenance";
		}
		
		//-----------------------------Consulter Planing des Soutnance final ------
		
		
		//affichage
		@RequestMapping(value="/ListeSoutenance")
		public String index(Model model,
				@RequestParam(name="page",defaultValue="0") int p,
				@RequestParam(name="size",defaultValue="5")int s
				,@RequestParam(name="motCle",defaultValue="")String mc
				) {
			Page<Soutenance> pageSoutenance=		
					soutenanceRepository.chercher("%"+mc+"%",new  PageRequest(p,s));
			
			model.addAttribute("ListSoutenance",pageSoutenance.getContent());
			int[] pages= new int[pageSoutenance.getTotalPages()];
			
			model.addAttribute("pages", pages);
			model.addAttribute("size", s);
			model.addAttribute("pagecourante", p);
			model.addAttribute("motCle",mc);
			return "Soutenance/ListeSoutenance";
		}
		
		// notification par mail
		
		@RequestMapping(value="/Notifier")
		public String notifier(Model model, String idS) {	
			
		Soutenance sout = soutenanceRepository.chercherSout(Integer.parseInt(idS));
		String Infoens = sout.getInfoJury();
		String cin = Infoens.substring(0, 8);
		Enseignant ens = enseignantRepository.chercherE(cin);
		String msg = "Bonjour M. "+ens.getNom()+" "+ens.getPrenom()+",   nous voulon vous informer que vous avez une soutannce du groupe "
		+sout.getNomGroupe()+" sur le sujet "+sout.getNomSujet()+" le "+sout.getDateSoutenance();
		try {
			notificationService.sendNotification(ens.getEmail(),msg);
			}
			catch(MailException e)
			{}
		
		String nomGroup = sout.getNomGroupe();
		Groupe grp = groupeRepository.chercherparNomG(nomGroup);
			List<Etudiant> listetud = grp.getEtudGP();
			String msgetud="";
			for (int i=0 ; i<listetud.size();i++)
			{
				
				
				
				
				
				
				msgetud= "Bonjour M."+listetud.get(i).getNom()+" "+listetud.get(i).getPrenom()+",   nous voulon vous informer que vous avez une soutannce le "
						+sout.getDateSoutenance()+"  et votre presedent jury M."+ens.getNom()+" "+ens.getPrenom();
			System.out.println(msgetud);
				
				
				try {
					notificationService.sendNotification(listetud.get(i).getEmail(),msgetud);
					}
					catch(MailException e)
					{}
			}
			return "Soutenance/ListeSoutenance";
		}
		
}

