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
    public static void insertUser (User unUser) {

		Controleur.insertUser(unUser);
	}

	public static ArrayList<User> selectAllUser() {
		return Modele.selectAllUser();
	
	}

    public static ArrayList<User> selectLikeUser(String filtre) {
		return Modele.selectLikeUser(filtre);
       
    }

	

    public static User selectWhereUser(String email, String mdp) {
		return Modele.selectWhereUser(email, mdp);

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
	
		
}