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

import controleur.Ville;
import controleur.Controleur;
import controleur.Tableau;


public class PanelVille extends PanelPrincipal implements ActionListener, KeyListener
{
    private JPanel panelForm = new JPanel(); 
    
    private JTextField txtNom = new JTextField(); 

    private JButton btAnnuler = new JButton("Annuler"); 
    private JButton btValider = new JButton("Valider"); 
    
    private JTable tableVilles ; 
    private Tableau tableauVilles ; 
    
    private JPanel panelFiltre = new JPanel (); 
    private JTextField txtFiltre = new JTextField(); 
    private JButton btFiltrer= new JButton("Filtrer");
    
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JLabel lbNBVilles = new JLabel();
 
    
    public PanelVille() {
        super ("Gestion des Villes");
        
        //Placement du panel formulaire 
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 100, 300, 200);
        this.panelForm.setLayout(new GridLayout(8,2));
        
        this.panelForm.add(new JLabel("Nom Ville :")); 
        this.panelForm.add(this.txtNom);
    	
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider); 
		
		this.add(this.panelForm);

		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//rendre les champs ecoutables 
		this.txtNom.addKeyListener(this);


		
		//installation de la JTable 
		String entetes[] = {"Id Ville", "Nom"};
		this.tableauVilles = new Tableau (this.obtenirDonnees(""), entetes); 
		this.tableVilles = new JTable(this.tableauVilles);
		JScrollPane uneScroll = new JScrollPane(this.tableVilles);
		uneScroll.setBounds(360, 100, 480, 250);
		this.add(uneScroll); 
		
		//installation du panel filtre 
		this.panelFiltre.setBackground(Color.cyan);
		this.panelFiltre.setBounds(370, 60, 450, 30);
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les villes par : ")); 
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
		this.lbNBVilles.setBounds(450, 380, 400, 20);
		this.add(this.lbNBVilles);
		this.lbNBVilles.setText("Nombre de villes : " + this.tableauVilles.getRowCount());
		
		//rendre la Jtable ecoutable sur le click de la souris 
		this.tableVilles.addMouseListener(new MouseListener() {
			
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
					numLigne = tableVilles.getSelectedRow(); 
                    
					txtNom.setText(tableauVilles.getValueAt(numLigne, 1).toString());
					
					
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
			}
		});
		
	}

	public Object [][] obtenirDonnees(String filtre)
	{
		//convertir une ArrayList d'objets de villes en matrice d'elements 
		ArrayList <Ville>  lesVilles ; 
		if (filtre.equals("")) {
			lesVilles = Controleur.selectAllVilles();
		}else {
			lesVilles = Controleur.selectLikeVilles(filtre);
		}
		Object matrice[][] = new Object[lesVilles.size()][2];
		int i = 0; 
		for (Ville unVille : lesVilles) {
			matrice[i][0] = unVille.getId_ville(); 
			matrice[i][1] = unVille.getNom();  
			i++;
		}
		return matrice ; 
	}

	public void viderChamps () {
		this.txtNom.setText("");
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
			this.tableauVilles.setDonnees(this.obtenirDonnees(filtre));
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			this.traitement ();
			this.lbNBVilles.setText("Nombre de villes : " + this.tableauVilles.getRowCount());
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			// recupérer l'ID ville 
			int numLigne, idville ; 
			numLigne = tableVilles.getSelectedRow(); 
			idville = Integer.parseInt(tableauVilles.getValueAt(numLigne, 0).toString());
			//récupérer les champs de données 
			String nom = this.txtNom.getText(); 
			ArrayList<String> lesChamps = new ArrayList<String>(); 
			lesChamps.add(nom); 

			if (Controleur.verifDonnees(lesChamps)) {
				//instancier un nouveau ville 
				Ville unVille = new Ville();
				unVille.setNom(nom);
                unVille.setId_ville(idville);
				//réaliser la modification dans la BDD 
				Controleur.updateVille(unVille);
				//actualiser l'afffichage 
				this.tableauVilles.setDonnees(this.obtenirDonnees(""));
				PanelProprietaire.remplirIdVille();
				PanelUser.remplirIdVille();
				//lorsque le panelcontrat sera fait il faudra recharger les id villes apres chaque insert ou update 
				
				// message de confirmation 
				JOptionPane.showMessageDialog(this, "Modification réussie du ville.", 
						"Modification Ville", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", 
						"Insertion Ville", JOptionPane.ERROR_MESSAGE);
			}
			// vider les champs 
			this.viderChamps();
		}
		else if (e.getSource() == this.btSupprimer) {
			//on récupère l'id ville 
			int numLigne, idville ; 
			numLigne = tableVilles.getSelectedRow(); 
			idville = Integer.parseInt(tableauVilles.getValueAt(numLigne, 0).toString());
			
			//on supprime dans la BDD 
			Controleur.deleteVille(idville);
			//on actualise l'affichage 
			this.tableauVilles.setDonnees(this.obtenirDonnees(""));
			this.lbNBVilles.setText("Nombre de villes : " + this.tableauVilles.getRowCount());
			PanelProprietaire.remplirIdVille();
			PanelUser.remplirIdVille();
			
			// confirmation de la suppression réussie 
			JOptionPane.showMessageDialog(this, "Suppression réussie du ville.", 
					"Suppression Ville", JOptionPane.INFORMATION_MESSAGE);
			
			//vider les champs 
			this.viderChamps();
			//PanelStats.actualiser();
			
		}
	}
	private void traitement () {
		//récupérer les données 
		String nom = this.txtNom.getText(); 
		
		ArrayList<String> lesChamps = new ArrayList<String>(); 
		lesChamps.add(nom); 
		if (Controleur.verifDonnees(lesChamps)) {
			//créer une instance de la classe Ville 
			Ville unVille  = new Ville();
			unVille.setNom(nom);
			
			//Insérer dans la base de données 
			Controleur.insertVille(unVille);
			
			//Afficher message de confirmation 
			JOptionPane.showMessageDialog(this, "Insertion réussie du ville.", 
					"Insertion Ville", JOptionPane.INFORMATION_MESSAGE);
			
			//Actualiser l'affichage du tableau villes 
			this.tableauVilles.setDonnees(this.obtenirDonnees(""));
			PanelProprietaire.remplirIdVille();
			PanelUser.remplirIdVille();
			
		}else {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs.", 
					"Insertion Ville", JOptionPane.ERROR_MESSAGE);
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


















 