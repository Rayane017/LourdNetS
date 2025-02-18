package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.User;
import controleur.Controleur;
import controleur.Tableau;
import controleur.Ville;


public class PanelUser extends PanelPrincipal implements ActionListener, KeyListener
{
    private JPanel panelForm = new JPanel(); 
    
    private JTextField txtNom = new JTextField(); 
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTel = new JTextField();
    private JTextField txtCodePostal = new JTextField();
	private static JComboBox<String> txtIdVille = new JComboBox<String>();
    
    private JButton btAnnuler = new JButton("Annuler"); 
    private JButton btValider = new JButton("Valider"); 
    
    private JTable tableUsers ; 
    private Tableau tableauUsers ; 
    
    private JPanel panelFiltre = new JPanel (); 
    private JTextField txtFiltre = new JTextField(); 
    private JButton btFiltrer= new JButton("Filtrer");
    
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JLabel lbNBUsers = new JLabel();
 
    
    public PanelUser() {
        super ("Gestion des Users");
        
        //Placement du panel formulaire 
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 100, 300, 200);
        this.panelForm.setLayout(new GridLayout(8,2));
        this.panelForm.add(new JLabel("Nom User :")); 
        this.panelForm.add(this.txtNom); 
        
        this.panelForm.add(new JLabel("Prénom User :")); 
        this.panelForm.add(this.txtPrenom);
        
        this.panelForm.add(new JLabel("Adresse postale :")); 
        this.panelForm.add(this.txtAdresse);

		this.panelForm.add(new JLabel("Ville :"));
		this.panelForm.add(txtIdVille);

        this.panelForm.add(new JLabel("Code postale :")); 
        this.panelForm.add(this.txtCodePostal);
		
		this.panelForm.add(new JLabel("Email User :")); 
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel("Téléphone User :")); 
		this.panelForm.add(this.txtTel);

		
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider); 
		
		this.add(this.panelForm);

		//remplir la combobox des villes
		remplirIdVille();

		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//rendre les champs ecoutables 
		this.txtNom.addKeyListener(this);
		this.txtPrenom.addKeyListener(this);
		this.txtAdresse.addKeyListener(this);
		txtIdVille.addKeyListener(this);
		this.txtCodePostal.addKeyListener(this);
		this.txtEmail.addKeyListener(this);
		this.txtTel.addKeyListener(this);

		
		//installation de la JTable 
		String entetes[] = {"IdUser", "Nom", "Prénom", "Adresse", "Ville", "Code Postale", "Email", "Téléphone", "Role"};
		this.tableauUsers = new Tableau (this.obtenirDonnees(""), entetes); 
		this.tableUsers = new JTable(this.tableauUsers);
		JScrollPane uneScroll = new JScrollPane(this.tableUsers);
		uneScroll.setBounds(360, 100, 480, 250);
		this.add(uneScroll); 
		
		//installation du panel filtre 
		this.panelFiltre.setBackground(Color.cyan);
		this.panelFiltre.setBounds(370, 60, 450, 30);
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les users par : ")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer); 
		this.add(this.panelFiltre);
		this.btFiltrer.addActionListener(this);
		
		//installation du bouton supprimer 
		this.btSupprimer.setBounds(80, 340, 140, 30);
		this.add(this.btSupprimer); 
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setVisible(false);
		this.btSupprimer.setBackground(Color.red);
		
		//installation du compteur 
		this.lbNBUsers.setBounds(450, 380, 400, 20);
		this.add(this.lbNBUsers);
		this.lbNBUsers.setText("Nombre de users : " + this.tableauUsers.getRowCount());
		
		//rendre la Jtable ecoutable sur le click de la souris 
		this.tableUsers.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {		
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0;
				if (e.getClickCount() >= 1) {
					numLigne = tableUsers.getSelectedRow(); 
					txtNom.setText(tableauUsers.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(tableauUsers.getValueAt(numLigne, 2).toString());
					txtAdresse.setText(tableauUsers.getValueAt(numLigne, 3).toString());
					txtIdVille.setSelectedItem(tableauUsers.getValueAt(numLigne, 4).toString());
                    txtCodePostal.setText(tableauUsers.getValueAt(numLigne, 5).toString());
					txtEmail.setText(tableauUsers.getValueAt(numLigne, 6).toString());
					txtTel.setText(tableauUsers.getValueAt(numLigne, 7).toString());
					
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
			}
		});
		
	}

	public static void remplirIdVille() {
		txtIdVille.removeAllItems();
		ArrayList<Ville> lesVilles = Controleur.selectAllVilles();
		for (Ville uneVille : lesVilles) {
			txtIdVille.addItem(uneVille.getId_ville() + " - " + uneVille.getNom());
		}
	}



	public Object [][] obtenirDonnees(String filtre)
	{
		//convertir une ArrayList d'objets de users en matrice d'elements 
		ArrayList <User>  lesUsers ; 
		if (filtre.equals("")) {
			lesUsers = Controleur.selectAllUsers();
		}else {
			lesUsers = Controleur.selectLikeUsers(filtre);
		}
		Object matrice[][] = new Object[lesUsers.size()][9];
		int i = 0; 
		for (User unUser : lesUsers) {
			matrice[i][0] = unUser.getId_user(); 
			matrice[i][1] = unUser.getNom(); 
			matrice[i][2] = unUser.getPrenom(); 
			matrice[i][3] = unUser.getAdresse(); 
			matrice[i][4] = unUser.getVille();
            matrice[i][5] = unUser.getCp();
			matrice[i][6] = unUser.getEmail(); 
			matrice[i][7] = unUser.getTelephone(); 
            matrice[i][8] = unUser.getRole();
			i++;
		}
		return matrice ; 
	}

	public void viderChamps () {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtAdresse.setText("");
		txtIdVille.setSelectedIndex(-1);
        this.txtCodePostal.setText("");
		this.txtEmail.setText("");
		this.txtTel.setText("");
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		}
		else if (e.getSource() == this.btFiltrer) {
			//on récupère le filtre
			String filtre = this.txtFiltre.getText(); 
			
			//on actualise les données 
			this.tableauUsers.setDonnees(this.obtenirDonnees(filtre));
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			this.traitement ();
			this.lbNBUsers.setText("Nombre de users : " + this.tableauUsers.getRowCount());
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			// recupérer l'ID user 
			int numLigne, iduser ; 
			numLigne = tableUsers.getSelectedRow(); 
			iduser = Integer.parseInt(tableauUsers.getValueAt(numLigne, 0).toString());
			//récupérer les champs de données 
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String tab [] = txtIdVille.getSelectedItem().toString().split(" - ");
			int idVille = Integer.parseInt(tab[0]);
			int codePostal = Integer.parseInt(this.txtCodePostal.getText());
			String email = this.txtEmail.getText();
			String tel = this.txtTel.getText();
			
			ArrayList<String> lesChamps = new ArrayList<String>(); 
			lesChamps.add(nom); 
			lesChamps.add(prenom);
			lesChamps.add(adresse);
			lesChamps.add(idVille+"");
            lesChamps.add(codePostal+"");
			lesChamps.add(email);
			lesChamps.add(tel); 
			if (Controleur.verifDonnees(lesChamps)) {
				//instancier un nouveau user 
				User unUser = new User();
				unUser.setId_user(iduser);
				unUser.setNom(nom);
				unUser.setPrenom(prenom);
				unUser.setAdresse(adresse);
				unUser.setId_ville(idVille);
				unUser.setCp(codePostal);
				unUser.setEmail(email);
				unUser.setTelephone(tel);
				unUser.setVille(txtIdVille.getSelectedItem().toString());
				//réaliser la modification dans la BDD 
				Controleur.updateUser(unUser);
				//actualiser l'afffichage 
				this.tableauUsers.setDonnees(this.obtenirDonnees(""));
				//PanelContrats.remplirIDUser();
				//lorsque le panelcontrat sera fait il faudra recharger les id users apres chaque insert ou update 
				
				// message de confirmation 
				JOptionPane.showMessageDialog(this, "Modification réussie du user.", 
						"Modification User", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", 
						"Insertion User", JOptionPane.ERROR_MESSAGE);
			}
			// vider les champs 
			this.viderChamps();
		}
		else if (e.getSource() == this.btSupprimer) {
			//on récupère l'id user 
			int numLigne, iduser ; 
			numLigne = tableUsers.getSelectedRow(); 
			iduser = Integer.parseInt(tableauUsers.getValueAt(numLigne, 0).toString());
			
			//on supprime dans la BDD 
			Controleur.deleteUser(iduser);
			//on actualise l'affichage 
			this.tableauUsers.setDonnees(this.obtenirDonnees(""));
			this.lbNBUsers.setText("Nombre de users : " + this.tableauUsers.getRowCount());
			//PanelContrats.remplirIDUser();
			
			// confirmation de la suppression réussie 
			JOptionPane.showMessageDialog(this, "Suppression réussie du user.", 
					"Suppression User", JOptionPane.INFORMATION_MESSAGE);
			
			//vider les champs 
			this.viderChamps();
			//PanelStats.actualiser();
			
		}
	}
	private void traitement () {
		//récupérer les données 
		String nom = this.txtNom.getText(); 
		String prenom = this.txtPrenom.getText();
		String adresse = this.txtAdresse.getText();
		String tab [] = txtIdVille.getSelectedItem().toString().split(" - ");
		int idVille = Integer.parseInt(tab[0]);
        int codePostal = Integer.parseInt(this.txtCodePostal.getText());
		String email = this.txtEmail.getText();
		String tel = this.txtTel.getText();
		
		ArrayList<String> lesChamps = new ArrayList<String>(); 
		lesChamps.add(nom); 
		lesChamps.add(prenom);
		lesChamps.add(adresse);
		lesChamps.add(idVille+"");
        lesChamps.add(codePostal+"");
		lesChamps.add(email);
		lesChamps.add(tel); 
		if (Controleur.verifDonnees(lesChamps)) {
			//créer une instance de la classe User 
			User unUser  = new User();
			unUser.setNom(nom);
			unUser.setPrenom(prenom);
			unUser.setAdresse(adresse);
			unUser.setId_ville(idVille);
			unUser.setCp(codePostal);
			unUser.setEmail(email);
			unUser.setTelephone(tel);
			
			//Insérer dans la base de données 
			Controleur.insertUser(unUser);
			
			//Afficher message de confirmation 
			JOptionPane.showMessageDialog(this, "Insertion réussie du user.", 
					"Insertion User", JOptionPane.INFORMATION_MESSAGE);
			
			//Actualiser l'affichage du tableau users 
			this.tableauUsers.setDonnees(this.obtenirDonnees(""));
			//PanelContrats.remplirIDUser();
			
		}else {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", 
					"Insertion User", JOptionPane.ERROR_MESSAGE);
		}
		//Vider les champs texte 
		this.viderChamps();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				this.traitement(); 
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void actualiser() {
		
	}
}


















 