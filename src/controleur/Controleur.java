package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {

    	/************************** Controle des données ************************/
	public static boolean verifDonnees (ArrayList<String> lesChamps) {
		boolean ok = true;
		for (String Champ : lesChamps) {
			if (Champ.isEmpty()) {
				ok = false;
				}
			
			}
		return ok;
		}

		public static int count (String table) {
		return Modele.count(table);
	}

	public static String getSaliere() {
		return Modele.getSaliere();
	}
	
	/************************** Gestion des utilisateurs ************************/
  
	public static User selectWhereUser(String email, String mdp) {
		return Modele.selectWhereUser(email, mdp);

    }

	public static void insertUser(User unUser) {
		Modele.insertUser(unUser);
	}

	public static ArrayList<User> selectAllUsers (){
		return Modele.selectAllUsers();
	}

	public static ArrayList<User> selectLikeUsers (String filtre){
		return Modele.selectLikeUsers(filtre);
	}

	public static void updateUser(User unUser) {
		Modele.updateUser(unUser);
	}

	public static void deleteUser (int idUser) {
		Modele.deleteUser(idUser);
	}
	

	/************************** Gestion des Villes ************************/

	public static void insertVille(Ville uneVille) {
		Modele.insertVille(uneVille);
	}

	public static ArrayList<Ville> selectAllVilles (){
		return Modele.selectAllVilles();
	}

	public static ArrayList<Ville> selectLikeVilles (String filtre){
		return Modele.selectLikeVilles(filtre);
	}

	public static void updateVille(Ville uneVille) {
		Modele.updateVille(uneVille);
	}

	public static void deleteVille (int idVille) {
		Modele.deleteVille(idVille);
	}

	/************************ GESTION DES PROPRIETAIRES **********************/
	
		public static void insertProprietaire(Proprietaire unProprietaire) {
			Modele.insertProprietaire(unProprietaire);
		}
	
		public static ArrayList<Proprietaire> selectAllProprietaires (){
			return Modele.selectAllProprietaires();
		}
	
		public static ArrayList<Proprietaire> selectLikeProprietaires (String filtre){
			return Modele.selectLikeProprietaires(filtre);
		}
	
		public static void updateProprietaire(Proprietaire unProprietaire) {
			Modele.updateProprietaire(unProprietaire);
		}
	
		public static void deleteProprietaire (int idProprietaire) {
			Modele.deleteProprietaire(idProprietaire);
		}
	
		/************************ GESTION DES CONTRATS ************************/

			public static void insertContrat(Contrat unContrat) {
			Modele.insertContrat(unContrat);
		}
	
		public static ArrayList<Contrat> selectAllContrats (){
			return Modele.selectAllContrats();
		}
	
		public static ArrayList<Contrat> selectLikeContrats (String filtre){
			return Modele.selectLikeContrats(filtre);
		}
	
		public static void updateContrat(Contrat unContrat) {
			Modele.updateContrat(unContrat);
		}
	
		public static void deleteContrat (int idContrat) {
			Modele.deleteContrat(idContrat);
		}

		public static ArrayList<Batiment> selectAllBatiments() {
			return Modele.selectAllBatiments();
		}

		public static ArrayList<Appartement> selectAllAppartements() {
			return Modele.selectAllAppartements();
		}
		
	/************************ GESTION DES RESAPARTYPE ************************/

	public static ArrayList<ResaParType> selectAllResaParType() {
		return Modele.selectAllResaParType();
	}

	/************************ GESTION DES CAPARDPT ************************/

	public static ArrayList<CaParDpt> selectAllCaParDpt() {
		return Modele.selectAllCaParDpt();
	}


	}
