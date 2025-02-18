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

import controleur.Appartement;
import controleur.Batiment;
import controleur.Contrat;
import controleur.Controleur;
import controleur.Proprietaire;
import controleur.Tableau;
import controleur.Ville;


public class PanelContrat extends PanelPrincipal implements ActionListener, KeyListener
{
    private JPanel panelForm = new JPanel(); 
    
	private static JComboBox<String> txtIdProprietaire = new JComboBox<String>();
	private static JComboBox<String> txtIdBatiment = new JComboBox<String>();
	private static JComboBox<Integer> txtNumeroAppartement = new JComboBox<Integer>();
    private JTextField txtRIB = new JTextField(); 
    private JTextField txtTarifHaute = new JTextField();
    private JTextField txtTarifMoyen = new JTextField();
    private JTextField txtTarifBasse = new JTextField();
	private JTextField txtAnneeLocation = new JTextField();
    private JComboBox<String> txtEtat  = new JComboBox<String>();

    
    private JButton btAnnuler = new JButton("Annuler"); 
    private JButton btValider = new JButton("Valider"); 
    
    private JTable tableContrats ; 
    private Tableau tableauContrats ; 
    
    private JPanel panelFiltre = new JPanel (); 
    private JTextField txtFiltre = new JTextField(); 
    private JButton btFiltrer= new JButton("Filtrer");
    
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JLabel lbNBContrats = new JLabel();
 
    
    public PanelContrat() {
        super ("Gestion des Contrats");
        
        //Placement du panel formulaire 
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 100, 300, 200);
        this.panelForm.setLayout(new GridLayout(10,2));
        this.panelForm.add(new JLabel("ID Proprietaire:")); 
        this.panelForm.add(txtIdProprietaire); 
        
        this.panelForm.add(new JLabel("Batiment :")); 
        this.panelForm.add(txtIdBatiment);
        
        this.panelForm.add(new JLabel("Appartement:")); 
        this.panelForm.add(txtNumeroAppartement);

		this.panelForm.add(new JLabel("RIB :"));
		this.panelForm.add(txtRIB);

        this.panelForm.add(new JLabel("Haute Saison :")); 
        this.panelForm.add(this.txtTarifHaute);
		
		this.panelForm.add(new JLabel("Moyenne Saison:")); 
		this.panelForm.add(this.txtTarifMoyen);
		
		this.panelForm.add(new JLabel("Basse Saison :")); 
		this.panelForm.add(this.txtTarifBasse);

		this.panelForm.add(new JLabel("Annee Location :"));
		this.panelForm.add(this.txtAnneeLocation);

		this.panelForm.add(new JLabel("Etat :"));
		this.panelForm.add(this.txtEtat);

		
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider); 
		
		this.add(this.panelForm);

		//remplir la combobox des villes
		remplirIdProprietaire();
		remplirIdBatiment();
		remplirIdAppartement(); 
		this.txtEtat.addItem("Actif");
		this.txtEtat.addItem("Inactif");


		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//rendre les champs ecoutables 
		txtIdProprietaire.addKeyListener(this);
		txtIdBatiment.addKeyListener(this);
		txtNumeroAppartement.addKeyListener(this);
		this.txtRIB.addKeyListener(this);
		this.txtTarifHaute.addKeyListener(this);
		this.txtTarifMoyen.addKeyListener(this);
		this.txtTarifBasse.addKeyListener(this);
		this.txtEtat.addKeyListener(this);

	
		//installation de la JTable 
		String entetes[] = {"Contrat", "Proprietaire", "Batiment", "Appartement", "RIB", "Haute Saison", "Moyenne Saison", "Basse Saison", "Annee", "Etat"};
		this.tableauContrats = new Tableau (this.obtenirDonnees(""), entetes); 
		this.tableContrats = new JTable(this.tableauContrats);
		JScrollPane uneScroll = new JScrollPane(this.tableContrats);
		uneScroll.setBounds(360, 100, 500, 250);
		this.add(uneScroll); 
		
		//installation du panel filtre 
		this.panelFiltre.setBackground(Color.cyan);
		this.panelFiltre.setBounds(370, 60, 450, 30);
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les contrats par : ")); 
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
		this.lbNBContrats.setBounds(450, 380, 400, 20);
		this.add(this.lbNBContrats);
		this.lbNBContrats.setText("Nombre de contrats : " + this.tableauContrats.getRowCount());
		
		//rendre la Jtable ecoutable sur le click de la souris 
		this.tableContrats.addMouseListener(new MouseListener() {
			
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
					numLigne = tableContrats.getSelectedRow(); 
					
					
					txtIdProprietaire.setVisible(false);
					txtIdBatiment.setVisible(false);
					txtNumeroAppartement.setVisible(false);
					txtRIB.setVisible(false);
					txtTarifHaute.setVisible(false);
					txtTarifMoyen.setVisible(false);
					txtTarifBasse.setVisible(false);
					txtAnneeLocation.setVisible(false);
					
					txtEtat.setSelectedItem(tableauContrats.getValueAt(numLigne, 9).toString());
					
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
			}
		});
		
	}

	public static void remplirIdProprietaire() {
		txtIdProprietaire.removeAllItems();
		ArrayList<Proprietaire> lesProprietaires = Controleur.selectAllProprietaires();
		for (Proprietaire unProprietaire : lesProprietaires) {
			txtIdProprietaire.addItem(unProprietaire.getId_proprietaire() + " - " + unProprietaire.getNom() + " - " + unProprietaire.getPrenom());
		}
	}

	public static void remplirIdBatiment() {
		txtIdBatiment.removeAllItems();
		ArrayList<Batiment> lesBatiments = Controleur.selectAllBatiments();
		for (Batiment unBatiment : lesBatiments) {
			txtIdBatiment.addItem(unBatiment.getIdBatiment());
		}
	}

	public static void remplirIdAppartement() {
		txtNumeroAppartement.removeAllItems();
		ArrayList<Appartement> lesAppartements = Controleur.selectAllAppartements();
		for (Appartement unAppartement : lesAppartements) {
			txtNumeroAppartement.addItem(unAppartement.getNumeroAppartement());
		}
	}



	public Object [][] obtenirDonnees(String filtre)
	{
		//convertir une ArrayList d'objets de contrats en matrice d'elements 
		ArrayList <Contrat>  lesContrats ; 
		if (filtre.equals("")) {
			lesContrats = Controleur.selectAllContrats();
		}else {
			lesContrats = Controleur.selectLikeContrats(filtre);
		}
		Object matrice[][] = new Object[lesContrats.size()][10];
		int i = 0; 
		for (Contrat unContrat : lesContrats) {
			matrice[i][0] = unContrat.getIdContrat();
			matrice[i][1] = unContrat.getIdProprietaire(); 
			matrice[i][2] = unContrat.getIdBatiment(); 
			matrice[i][3] = unContrat.getNumeroAppartement(); 
			matrice[i][4] = unContrat.getRIB(); 
			matrice[i][5] = unContrat.getTarifHaute();
			matrice[i][6] = unContrat.getTarifMoyen();
			matrice[i][7] = unContrat.getTarifBasse(); 
			matrice[i][8] = unContrat.getAnneeLocation();
			matrice[i][9] = unContrat.getEtat();
			i++;


		}
		return matrice ; 
	}

	public void viderChamps () {
		txtIdProprietaire.setSelectedIndex(-1);
		txtIdBatiment.setSelectedIndex(-1);
		txtNumeroAppartement.setSelectedIndex(-1);
		this.txtRIB.setText("");
        this.txtTarifHaute.setText("");
		this.txtTarifMoyen.setText("");
		this.txtTarifBasse.setText("");
		this.txtAnneeLocation.setText("");
		this.txtEtat.setSelectedIndex(-1);
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
			this.tableauContrats.setDonnees(this.obtenirDonnees(filtre));
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			this.traitement ();
			this.lbNBContrats.setText("Nombre de contrats : " + this.tableauContrats.getRowCount());
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			// recupérer l'ID contrat 
			int numLigne, idcontrat ; 
			numLigne = tableContrats.getSelectedRow(); 
			idcontrat = Integer.parseInt(tableauContrats.getValueAt(numLigne, 0).toString());
			//récupérer les champs de données 
			String etat = this.txtEtat.getSelectedItem().toString(); 
			
			ArrayList<String> lesChamps = new ArrayList<String>(); 
			lesChamps.add(etat); 
			if (Controleur.verifDonnees(lesChamps)) {
				//instancier un nouveau contrat 
				Contrat unContrat = new Contrat();
				unContrat.setIdContrat(idcontrat);
				unContrat.setEtat(etat);
				
				//réaliser la modification dans la BDD 
				Controleur.updateContrat(unContrat);
				//actualiser l'afffichage 
				this.tableauContrats.setDonnees(this.obtenirDonnees(""));
				//PanelContrats.remplirIDContrat();
				//lorsque le panelcontrat sera fait il faudra recharger les id contrats apres chaque insert ou update 
				
				// message de confirmation 
				JOptionPane.showMessageDialog(this, "Modification réussie du contrat.", 
						"Modification Contrat", JOptionPane.INFORMATION_MESSAGE);
				
					txtIdProprietaire.setVisible(true);
					txtIdBatiment.setVisible(true);
					txtNumeroAppartement.setVisible(true);
					txtRIB.setVisible(true);
					txtTarifHaute.setVisible(true);
					txtTarifMoyen.setVisible(true);
					txtTarifBasse.setVisible(true);
					txtAnneeLocation.setVisible(true);
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", 
						"Insertion Contrat", JOptionPane.ERROR_MESSAGE);
			}
			// vider les champs 
			this.viderChamps();
		}
		else if (e.getSource() == this.btSupprimer) {
			//on récupère l'id contrat 
			int numLigne, idcontrat ; 
			numLigne = tableContrats.getSelectedRow(); 
			idcontrat = Integer.parseInt(tableauContrats.getValueAt(numLigne, 0).toString());
			
			//on supprime dans la BDD 
			Controleur.deleteContrat(idcontrat);
			//on actualise l'affichage 
			this.tableauContrats.setDonnees(this.obtenirDonnees(""));
			this.lbNBContrats.setText("Nombre de contrats : " + this.tableauContrats.getRowCount());
			//PanelContrats.remplirIDContrat();
			
			// confirmation de la suppression réussie 
			JOptionPane.showMessageDialog(this, "Suppression réussie du contrat.", 
					"Suppression Contrat", JOptionPane.INFORMATION_MESSAGE);
			
			//vider les champs 
			this.viderChamps();
			//PanelStats.actualiser();
			
		}
	}
	private void traitement () {
		//récupérer les données 
		String tab[]  = txtIdProprietaire.getSelectedItem().toString().split(" - "); 
		int idProprietaire = Integer.parseInt(tab[0]);
		String idBatiment = txtIdBatiment.getSelectedItem().toString(); 
		tab = txtNumeroAppartement.getSelectedItem().toString().split(" - "); 
		int numeroAppartement = Integer.parseInt(tab[0]);
		String RIB = this.txtRIB.getText();
		Float tarifHaute = Float.parseFloat(this.txtTarifHaute.getText());
		Float tarifMoyen = Float.parseFloat(this.txtTarifMoyen.getText());
		Float tarifBasse = Float.parseFloat(this.txtTarifBasse.getText());
		int Annee = Integer.parseInt(this.txtAnneeLocation.getText());
		String etat = this.txtEtat.getSelectedItem().toString();
		
		ArrayList<String> lesChamps = new ArrayList<String>(); 
		lesChamps.add(idProprietaire+""); 
		lesChamps.add(idBatiment);
		lesChamps.add(numeroAppartement + "");
		lesChamps.add(RIB);
        lesChamps.add(tarifHaute + "");
		lesChamps.add(tarifMoyen+ "");
		lesChamps.add(tarifBasse+ ""); 
		lesChamps.add(Annee+ "");
		lesChamps.add(etat);
		if (Controleur.verifDonnees(lesChamps)) {
			//créer une instance de la classe Contrat 
			Contrat unContrat  = new Contrat();
			unContrat.setIdProprietaire(idProprietaire);
			unContrat.setIdBatiment(idBatiment);
			unContrat.setNumeroAppartement(numeroAppartement);
			unContrat.setRIB(RIB);
			unContrat.setTarifHaute(tarifHaute);
			unContrat.setTarifMoyen(tarifMoyen);
			unContrat.setTarifBasse(tarifBasse);
			unContrat.setAnneeLocation(Annee);
			unContrat.setEtat(etat);
			
			//Insérer dans la base de données 
			Controleur.insertContrat(unContrat);
			
			//Afficher message de confirmation 
			JOptionPane.showMessageDialog(this, "Insertion réussie du contrat.", 
					"Insertion Contrat", JOptionPane.INFORMATION_MESSAGE);
			
			//Actualiser l'affichage du tableau contrats 
			this.tableauContrats.setDonnees(this.obtenirDonnees(""));
			//PanelContrats.remplirIDContrat();
			
		}else {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", 
					"Insertion Contrat", JOptionPane.ERROR_MESSAGE);
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


















 