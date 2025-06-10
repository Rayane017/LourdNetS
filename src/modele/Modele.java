package modele;



import java.sql.*;
import java.util.ArrayList;

import controleur.Appartement;
import controleur.Batiment;
import controleur.CaParDpt;
import controleur.Contrat;
import controleur.Proprietaire;
import controleur.ResaParType;
import controleur.User;
import controleur.Ville;

public class Modele {
    /************************* Attributs ****************************/
    private static Connexion uneConnexion = new Connexion ("localhost", "NeigeSoleil", "root", "");

    /********************* Gestion des utilisateurs **************************/


    public static User selectWhereUser (String email, String mdp){
        String requete = "SELECT * FROM USER WHERE EMAIL ='"+email+"' AND MDP ='"+mdp+"';";
        User unUser = null;
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if (unResultat.next()){
                unUser = new User();
				unUser.setId_user(unResultat.getInt("ID_USER"));
				unUser.setNom(unResultat.getString("NOM"));
				unUser.setPrenom(unResultat.getString("PRENOM"));
				unUser.setAdresse(unResultat.getString("ADRESSE"));
				unUser.setCp(unResultat.getInt("CODE_POSTAL"));
				unUser.setEmail(unResultat.getString("EMAIL"));
				unUser.setTelephone(unResultat.getString("TELEPHONE"));
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : "+ requete);
        }
        return unUser;
    }

	public static void insertUser(User unUser) {
		String requete = "INSERT INTO USER VALUES (NULL, "+unUser.getId_ville()+",'"+unUser.getNom()
		+ "','" + unUser.getPrenom() + "','" + unUser.getAdresse() + "','" + unUser.getCp() + "','" + unUser.getTelephone()
		+ "','" + unUser.getEmail()+"','1234','Admin');";
		
		executerRequete (requete); 
	}

	public static ArrayList<User> selectAllUsers (){
		ArrayList<User> lesUsers = new ArrayList<User>(); 
		String requete ="SELECT U.*, V.NOM AS VILLE FROM USER U JOIN VILLE V ON U.ID_VILLE=V.ID_VILLE;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un user 
				User unUser = new User(); 
						unUser.setId_user( lesResultats.getInt("ID_USER")); unUser.setNom(lesResultats.getString("NOM"));
						unUser.setPrenom(lesResultats.getString("PRENOM"));unUser.setAdresse(lesResultats.getString("ADRESSE"));unUser.setVille(lesResultats.getString("VILLE"));unUser.setCp(lesResultats.getInt("CODE_POSTAL"));
						unUser.setEmail(lesResultats.getString("EMAIL"));unUser.setTelephone(lesResultats.getString("TELEPHONE"));
						unUser.setRole(lesResultats.getString("ROLE"));
				//on ajoute le user dans l'ArrayList
				lesUsers.add(unUser);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesUsers; 
	}

	public static ArrayList<User> selectLikeUsers (String filtre){
		ArrayList<User> lesUsers = new ArrayList<User>(); 
		String requete ="SELECT U.*, V.NOM AS VILLE FROM USER U JOIN VILLE V ON U.ID_VILLE=V.ID_VILLE WHERE U.NOM LIKE '%"+filtre
				+"%' OR U.PRENOM LIKE '%" + filtre + "%' OR U.ADRESSE LIKE '%"
				+ filtre + "%' OR V.NOM LIKE '%" + filtre + "%' OR U.EMAIL LIKE '%" + filtre + "%' OR U.TELEPHONE LIKE '%"
				+ filtre + "%' OR U.ROLE LIKE '%"+filtre+"%'; ";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un user 
				User unUser = new User(); 
				unUser.setId_user( lesResultats.getInt("ID_USER")); unUser.setNom(lesResultats.getString("NOM"));
				unUser.setPrenom(lesResultats.getString("PRENOM"));unUser.setAdresse(lesResultats.getString("ADRESSE"));unUser.setVille(lesResultats.getString("VILLE"));unUser.setCp(lesResultats.getInt("CODE_POSTAL"));
				unUser.setEmail(lesResultats.getString("EMAIL"));unUser.setTelephone(lesResultats.getString("TELEPHONE"));
				unUser.setRole(lesResultats.getString("ROLE"));
				//on ajoute le user dans l'ArrayList
				lesUsers.add(unUser);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			exp.printStackTrace();
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesUsers; 
	}

	public static void updateUser(User unUser) {
		String requete ="UPDATE USER SET ID_VILLE ="+unUser.getId_ville() +", NOM = '" + unUser.getNom() 
		+ "', PRENOM ='"+unUser.getPrenom() + "', ADRESSE='" + unUser.getAdresse()
		+ "', CODE_POSTAL='" + unUser.getCp()
		+ "', EMAIL ='"+unUser.getEmail() + "', TELEPHONE='" + unUser.getTelephone()
		+ "'  WHERE  ID_USER = "+unUser.getId_user()+";";
		
		executerRequete(requete);
	}

	public static void deleteUser (int idUser) {
		String requete = "DELETE FROM USER WHERE ID_USER = "+idUser+";";
		executerRequete(requete);
	}

    /********************* Gestion des villes **************************/

	public static void insertVille(Ville uneVille) {
		String requete = "INSERT INTO VILLE VALUES (NULL, '"+uneVille.getNom()+"');";
		
		executerRequete (requete); 
	}


    public static ArrayList<Ville> selectAllVilles (){
        ArrayList<Ville> lesVilles = new ArrayList<Ville>();
        String requete = "SELECT * FROM VILLE;";
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                Ville uneVille = new Ville();
				uneVille.setId_ville(lesResultats.getInt("ID_VILLE")); 
				uneVille.setNom(lesResultats.getString("NOM"));
                lesVilles.add(uneVille);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }
        catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesVilles;
    }

	public static ArrayList<Ville> selectLikeVilles (String filtre){
		ArrayList<Ville> lesVilles = new ArrayList<Ville>(); 
		String requete ="SELECT * FROM VILLE WHERE NOM LIKE '%"+filtre
				+"%' OR ID_VILLE LIKE '%" + filtre + "%' ;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un ville 
				Ville uneVille = new Ville(); 
				uneVille.setId_ville( lesResultats.getInt("ID_VILLE")); uneVille.setNom(lesResultats.getString("NOM"));
				//on ajoute le ville dans l'ArrayList
				lesVilles.add(uneVille);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesVilles; 
	}

	public static void updateVille(Ville uneVille) {
		String requete ="UPDATE VILLE SET NOM = '" + uneVille.getNom()+"' WHERE  ID_VILLE = "+uneVille.getId_ville()+";";
		
		executerRequete(requete);
	}

	public static void deleteVille (int idVille) {
		String requete = "DELETE FROM VILLE WHERE ID_VILLE = "+idVille+";";
		executerRequete(requete);
	}

    /************************ GESTION DES PROPRIETAIRES **********************/
	
	//Faut creer une procédure d'insertion dans laquelle on appelle un procédure qui genere un mot de passe random pour l'insérer en tant que mdp puis retourner ce mdp pour l'utiliser dans un api de mail pour envoyer le mdp au propriétaire
	public static void insertProprietaire(Proprietaire unProprietaire) {
		String requete = "INSERT INTO PROPRIETAIRE VALUES (NULL, "+unProprietaire.getId_ville()+",'"+unProprietaire.getNom()
		+ "','" + unProprietaire.getPrenom() + "','" + unProprietaire.getAdresse() + "','" + unProprietaire.getCode_postal() + "','" + unProprietaire.getTel()
		+ "','" + unProprietaire.getEmail()+"','1234','Proprietaire');";
		
		executerRequete (requete); 
	}

	public static ArrayList<Proprietaire> selectAllProprietaires (){
		ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>(); 
		String requete ="SELECT P.*, V.NOM AS VILLE FROM PROPRIETAIRE P JOIN VILLE V ON P.ID_VILLE=V.ID_VILLE;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un proprietaire 
				Proprietaire unProprietaire = new Proprietaire(); 
						unProprietaire.setId_proprietaire( lesResultats.getInt("ID_PROPRIETAIRE")); unProprietaire.setNom(lesResultats.getString("NOM"));
						unProprietaire.setPrenom(lesResultats.getString("PRENOM"));unProprietaire.setAdresse(lesResultats.getString("ADRESSE"));unProprietaire.setVille(lesResultats.getString("VILLE"));unProprietaire.setCode_postal(lesResultats.getInt("CODE_POSTAL"));
						unProprietaire.setEmail(lesResultats.getString("EMAIL"));unProprietaire.setTel(lesResultats.getString("TELEPHONE"));
				//on ajoute le proprietaire dans l'ArrayList
				lesProprietaires.add(unProprietaire);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesProprietaires; 
	}

	public static ArrayList<Proprietaire> selectLikeProprietaires (String filtre){
		ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>(); 
		String requete ="SELECT P.*, V.NOM AS VILLE FROM PROPRIETAIRE P JOIN VILLE V ON P.ID_VILLE=V.ID_VILLE WHERE P.NOM LIKE '%"+filtre
				+"%' OR P.PRENOM LIKE '%" + filtre + "%' OR P.ADRESSE LIKE '%"
				+ filtre + "%' OR V.NOM LIKE '%" + filtre + "%' OR P.EMAIL LIKE '%" + filtre + "%' OR P.TELEPHONE LIKE '%"
				+ filtre + "%' ; ";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un proprietaire 
				Proprietaire unProprietaire = new Proprietaire(); 
				unProprietaire.setId_proprietaire( lesResultats.getInt("ID_PROPRIETAIRE")); unProprietaire.setNom(lesResultats.getString("NOM"));
				unProprietaire.setPrenom(lesResultats.getString("PRENOM"));unProprietaire.setAdresse(lesResultats.getString("ADRESSE"));unProprietaire.setVille(lesResultats.getString("VILLE"));unProprietaire.setCode_postal(lesResultats.getInt("CODE_POSTAL"));
				unProprietaire.setEmail(lesResultats.getString("EMAIL"));unProprietaire.setTel(lesResultats.getString("TELEPHONE"));
				//on ajoute le proprietaire dans l'ArrayList
				lesProprietaires.add(unProprietaire);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesProprietaires; 
	}

	public static void updateProprietaire(Proprietaire unProprietaire) {
		String requete ="UPDATE PROPRIETAIRE SET ID_VILLE ="+unProprietaire.getId_ville() +", NOM = '" + unProprietaire.getNom() 
		+ "', PRENOM ='"+unProprietaire.getPrenom() + "', ADRESSE='" + unProprietaire.getAdresse()
		+ "', CODE_POSTAL='" + unProprietaire.getCode_postal()
		+ "', EMAIL ='"+unProprietaire.getEmail() + "', TELEPHONE='" + unProprietaire.getTel()
		+ "'  WHERE  ID_PROPRIETAIRE = "+unProprietaire.getId_proprietaire()+";";
		
		executerRequete(requete);
	}

	public static void deleteProprietaire (int idProprietaire) {
		String requete = "DELETE FROM PROPRIETAIRE WHERE ID_PROPRIETAIRE = "+idProprietaire+";";
		executerRequete(requete);
	}


	 /************************ GESTION DES CONTRATS **********************/
	
	public static void insertContrat(Contrat unContrat) {
		String requete = "INSERT INTO CONTRAT_DE_MANDAT_LOCATIF VALUES (NULL, "+unContrat.getIdProprietaire()+",'"+unContrat.getIdBatiment()
		+ "'," + unContrat.getNumeroAppartement() + ",'" + unContrat.getRIB() + "'," + unContrat.getTarifHaute() + "," + unContrat.getTarifMoyen()
		+ "," + unContrat.getTarifBasse()+",NULL,"+unContrat.getAnneeLocation()+",'"+unContrat.getEtat()+"');";
		
		executerRequete (requete); 
	}

	public static ArrayList<Contrat> selectAllContrats (){
		ArrayList<Contrat> lesContrats = new ArrayList<Contrat>(); 
		String requete ="SELECT * FROM CONTRAT_DE_MANDAT_LOCATIF;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un contrat 
				Contrat unContrat = new Contrat(); 
						unContrat.setIdContrat( lesResultats.getInt("ID_CONTRAT")); 
						unContrat.setIdProprietaire(lesResultats.getInt("ID_PROPRIETAIRE"));
						unContrat.setIdBatiment(lesResultats.getString("ID_BATIMENT"));
						unContrat.setNumeroAppartement(lesResultats.getInt("NUMERO_APPARTEMENT"));
						unContrat.setRIB(lesResultats.getString("RIB"));
						unContrat.setTarifHaute(lesResultats.getFloat("TARIF_HAUTE"));
						unContrat.setTarifMoyen(lesResultats.getFloat("TARIF_MOYEN"));
						unContrat.setTarifBasse(lesResultats.getFloat("TARIF_BASSE"));
						unContrat.setAnneeLocation(lesResultats.getInt("ANNEE_DE_LOCATION"));
						unContrat.setEtat(lesResultats.getString("ETAT"));
				//on ajoute le contrat dans l'ArrayList
				lesContrats.add(unContrat);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesContrats; 
	}

	public static ArrayList<Contrat> selectLikeContrats (String filtre){
		ArrayList<Contrat> lesContrats = new ArrayList<Contrat>(); 
		String requete ="SELECT * FROM CONTRAT_DE_MANDAT_LOCATIF WHERE ID_CONTRAT LIKE '%"+filtre
				+"%' OR ID_PROPRIETAIRE LIKE '%" + filtre + "%' OR ID_BATIMENT LIKE '%"
				+ filtre + "%' OR NUMERO_APPARTEMENT LIKE '%" + filtre + "%' OR RIB LIKE '%" + filtre + "%' OR ETAT LIKE '%"
				+ filtre + "%' OR ANNEE_DE_LOCATION LIKE '%"+filtre+"%'; ";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un contrat 
				Contrat unContrat = new Contrat(); 
				unContrat.setIdContrat( lesResultats.getInt("ID_CONTRAT")); 
						unContrat.setIdProprietaire(lesResultats.getInt("ID_PROPRIETAIRE"));
						unContrat.setIdBatiment(lesResultats.getString("ID_BATIMENT"));
						unContrat.setNumeroAppartement(lesResultats.getInt("NUMERO_APPARTEMENT"));
						unContrat.setRIB(lesResultats.getString("RIB"));
						unContrat.setTarifHaute(lesResultats.getFloat("TARIF_HAUTE"));
						unContrat.setTarifMoyen(lesResultats.getFloat("TARIF_MOYEN"));
						unContrat.setTarifBasse(lesResultats.getFloat("TARIF_BASSE"));
						unContrat.setAnneeLocation(lesResultats.getInt("ANNEE_DE_LOCATION"));
						unContrat.setEtat(lesResultats.getString("ETAT"));
				//on ajoute le contrat dans l'ArrayList
				lesContrats.add(unContrat);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesContrats; 
	}

	public static void updateContrat(Contrat unContrat) {
		String requete ="UPDATE CONTRAT_DE_MANDAT_LOCATIF SET ETAT ='"+unContrat.getEtat() +"' WHERE ID_CONTRAT = "+unContrat.getIdContrat()+";";
		
		executerRequete(requete);
	}

	public static void deleteContrat (int idContrat) {
		String requete = "DELETE FROM CONTRAT_DE_MANDAT_LOCATIF WHERE ID_CONTRAT = "+idContrat+";";
		executerRequete(requete);
	}

	// remplir le combo box
	public static ArrayList<Batiment> selectAllBatiments(){
        ArrayList<Batiment> lesBatiments = new ArrayList<Batiment>();
        String requete = "SELECT * FROM BATIMENT;";
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                Batiment unBatiment = new Batiment();
				unBatiment.setIdBatiment(lesResultats.getString("ID_BATIMENT")); 
                lesBatiments.add(unBatiment);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }
        catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesBatiments;
    }

	// remplir le combo box
	public static ArrayList<Appartement> selectAllAppartements(){
        ArrayList<Appartement> lesAppartements = new ArrayList<Appartement>();
        String requete = "SELECT * FROM APPARTEMENT;";
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                Appartement unAppartement = new Appartement();
				unAppartement.setIdBatiment(lesResultats.getString("ID_BATIMENT")); 
				unAppartement.setNumeroAppartement(lesResultats.getInt("NUMERO_APPARTEMENT"));
                lesAppartements.add(unAppartement);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }
        catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesAppartements;
    }

	public static ArrayList<ResaParType> selectAllResaParType(){
        ArrayList<ResaParType> lesStats = new ArrayList<ResaParType>();
        String requete = "SELECT * FROM ResaParType;";
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                ResaParType uneStat = new ResaParType();
				uneStat.setType(lesResultats.getString("TYPE")); 
				uneStat.setNbResa(lesResultats.getInt("nbResa"));
                lesStats.add(uneStat);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }
        catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesStats;
    }

	public static ArrayList<CaParDpt> selectAllCaParDpt(){
        ArrayList<CaParDpt> lesStats = new ArrayList<CaParDpt>();
        String requete = "SELECT * FROM CaParDpt;";
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                CaParDpt uneStat = new CaParDpt(); 
				uneStat.setDpt(lesResultats.getString("DPT"));
				uneStat.setCa(lesResultats.getBigDecimal("CA"));
                lesStats.add(uneStat);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }
        catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesStats;
    }
	

    /********************* Autres méthodes **************************/

    public static void executerRequete (String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			exp.printStackTrace();
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
	}

    public static int count (String table) {
		int nb = 0;
		String requete = "SELECT COUNT(*) AS NB FROM "+table+";";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next()) {
				nb = unResultat.getInt("NB");
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}

		return nb;
	}

	public static String getSaliere() {
		String grainSel = null;
		String requete = "SELECT GRAIN_DE_SEL FROM SALIERE;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next()) {
				grainSel = unResultat.getString("GRAIN_DE_SEL");
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		
		}
		return grainSel;

	}


    
}
