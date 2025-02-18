package modele;



import java.sql.*;
import java.util.ArrayList;

import controleur.Proprietaire;
import controleur.User;
import controleur.Ville;

public class Modele {
    /************************* Attributs ****************************/
    private static Connexion uneConnexion = new Connexion ("localhost", "NeigeSoleil", "root", "");

    /********************* Gestion des utilisateurs **************************/


    public static User selectWhereUser (String email, String mdp){
        String requete = "select * from User where email ='"+email+"' and mdp ='"+mdp+"';";
        User unUser = null;
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if (unResultat.next()){
                unUser = new User();
				unUser.setId_user(unResultat.getInt("id_user"));
				unUser.setNom(unResultat.getString("nom"));
				unUser.setPrenom(unResultat.getString("prenom"));
				unUser.setAdresse(unResultat.getString("adresse"));
				unUser.setCp(unResultat.getInt("code_postal"));
				unUser.setEmail(unResultat.getString("email"));
				unUser.setTelephone(unResultat.getString("telephone"));
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : "+ requete);
        }
        return unUser;
    }

	public static void insertUser(User unUser) {
		String requete = "insert into User values (null, "+unUser.getId_ville()+",'"+unUser.getNom()
		+ "','" + unUser.getPrenom() + "','" + unUser.getAdresse() + "','" + unUser.getCp() + "','" + unUser.getTelephone()
		+ "','" + unUser.getEmail()+"','1234',current_timestamp(),'Admin');";
		
		executerRequete (requete); 
	}

	public static ArrayList<User> selectAllUsers (){
		ArrayList<User> lesUsers = new ArrayList<User>(); 
		String requete ="select u.*, v.nom ville from User u join ville v on u.id_ville=v.id_ville;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un user 
				User unUser = new User(); 
						unUser.setId_user( lesResultats.getInt("ID_USER")); unUser.setNom(lesResultats.getString("NOM"));
						unUser.setPrenom(lesResultats.getString("PRENOM"));unUser.setAdresse(lesResultats.getString("ADRESSE"));unUser.setVille(lesResultats.getString("ville"));unUser.setCp(lesResultats.getInt("CODE_POSTAL"));
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
		String requete ="select u.*, v.nom as ville from `User` u join ville v on u.id_ville=v.id_ville where u.nom like '%"+filtre
				+"%' or u.prenom like '%" + filtre + "%' or u.adresse like '%"
				+ filtre + "%' or v.nom like '%" + filtre + "%' or u.email like '%" + filtre + "%' or u.telephone like '%"
				+ filtre + "%' or u.role like '%"+filtre+"%'; ";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un user 
				User unUser = new User(); 
				unUser.setId_user( lesResultats.getInt("ID_USER")); unUser.setNom(lesResultats.getString("NOM"));
				unUser.setPrenom(lesResultats.getString("PRENOM"));unUser.setAdresse(lesResultats.getString("ADRESSE"));unUser.setVille(lesResultats.getString("ville"));unUser.setCp(lesResultats.getInt("CODE_POSTAL"));
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
		String requete ="update User set id_ville ="+unUser.getId_ville() +", nom = '" + unUser.getNom() 
		+ "', prenom ='"+unUser.getPrenom() + "', adresse='" + unUser.getAdresse()
		+ "', code_postal='" + unUser.getCp()
		+ "', email ='"+unUser.getEmail() + "', telephone='" + unUser.getTelephone()
		+ "'  where  id_user = "+unUser.getId_user()+";";
		
		executerRequete(requete);
	}

	public static void deleteUser (int idUser) {
		String requete = "delete from User where id_user = "+idUser+";";
		executerRequete(requete);
	}

    /********************* Gestion des villes **************************/

	public static void insertVille(Ville uneVille) {
		String requete = "insert into ville values (null, "+uneVille.getId_ville()+",'"+uneVille.getNom()+";";
		
		executerRequete (requete); 
	}


    public static ArrayList<Ville> selectAllVilles (){
        ArrayList<Ville> lesVilles = new ArrayList<Ville>();
        String requete = "select * from VILLE;";
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
		String requete ="select * from ville where nom like '%"+filtre
				+"%' or id_ville like '%" + filtre + "%' ;";
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
		String requete ="update ville set nom = '" + uneVille.getNom()+"' where  id_ville = "+uneVille.getId_ville()+";";
		
		executerRequete(requete);
	}

	public static void deleteVille (int idVille) {
		String requete = "delete from ville where id_ville = "+idVille+";";
		executerRequete(requete);
	}

    /************************ GESTION DES PROPRIETAIRES **********************/
	
	//Faut creer une procédure d'insertion dans laquelle on appelle un procédure qui genere un mot de passe random pour l'insérer en tant que mdp puis retourner ce mdp pour l'utiliser dans un api de mail pour envoyer le mdp au propriétaire
	public static void insertProprietaire(Proprietaire unProprietaire) {
		String requete = "insert into proprietaire values (null, "+unProprietaire.getId_ville()+",'"+unProprietaire.getNom()
		+ "','" + unProprietaire.getPrenom() + "','" + unProprietaire.getAdresse() + "','" + unProprietaire.getCode_postal() + "','" + unProprietaire.getTel()
		+ "','" + unProprietaire.getEmail()+"','1234','Proprietaire');";
		
		executerRequete (requete); 
	}

	public static ArrayList<Proprietaire> selectAllProprietaires (){
		ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>(); 
		String requete ="select p.*, v.nom ville from proprietaire p join ville v on p.id_ville=v.id_ville;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un proprietaire 
				Proprietaire unProprietaire = new Proprietaire(); 
						unProprietaire.setId_proprietaire( lesResultats.getInt("ID_PROPRIETAIRE")); unProprietaire.setNom(lesResultats.getString("NOM"));
						unProprietaire.setPrenom(lesResultats.getString("PRENOM"));unProprietaire.setAdresse(lesResultats.getString("ADRESSE"));unProprietaire.setVille(lesResultats.getString("ville"));unProprietaire.setCode_postal(lesResultats.getInt("CODE_POSTAL"));
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
		String requete ="select p.*, v.nom ville from proprietaire p join ville v on p.id_ville=v.id_ville where nom like '%"+filtre
				+"%' or prenom like '%" + filtre + "%' or adresse like '%"
				+ filtre + "%' or ville like %'" + filtre + "%' or email like '%" + filtre + "%' or tel like '%"
				+ filtre + "%' ; ";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un proprietaire 
				Proprietaire unProprietaire = new Proprietaire(); 
				unProprietaire.setId_proprietaire( lesResultats.getInt("ID_PROPRIETAIRE")); unProprietaire.setNom(lesResultats.getString("NOM"));
				unProprietaire.setPrenom(lesResultats.getString("PRENOM"));unProprietaire.setAdresse(lesResultats.getString("ADRESSE"));unProprietaire.setVille(lesResultats.getString("ville"));unProprietaire.setCode_postal(lesResultats.getInt("CODE_POSTAL"));
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
		String requete ="update proprietaire set id_ville ="+unProprietaire.getId_ville() +", nom = '" + unProprietaire.getNom() 
		+ "', prenom ='"+unProprietaire.getPrenom() + "', adresse='" + unProprietaire.getAdresse()
		+ "', code_postal='" + unProprietaire.getCode_postal()
		+ "', email ='"+unProprietaire.getEmail() + "', telephone='" + unProprietaire.getTel()
		+ "'  where  id_proprietaire = "+unProprietaire.getId_proprietaire()+";";
		
		executerRequete(requete);
	}

	public static void deleteProprietaire (int idProprietaire) {
		String requete = "delete from proprietaire where id_proprietaire = "+idProprietaire+";";
		executerRequete(requete);
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
		String requete = "select count(*) as nb from "+table+";";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next()) {
				nb = unResultat.getInt("nb");
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
		String requete = "select Grain_de_sel from saliere;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next()) {
				grainSel = unResultat.getString("Grain_de_sel");
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
