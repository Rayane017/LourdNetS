package vue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.Neige;

public class VueGenerale extends JFrame implements ActionListener 
{

    
    private JPanel panelMenu = new JPanel();

    //creation des boutons

    private JButton btUser = new JButton("Utilisateur");
    private JButton btProprietaire = new JButton("Proprietaires");
    private JButton btReservations = new JButton("Reservations");
    private JButton btContrats = new JButton("Contrats");
    private JButton btStats = new JButton("Statistiques");
    private JButton btQuitter = new JButton("Quitter");

    //instance des panels
    public static PanelUser unPanelUser = new PanelUser();
    public static PanelProprietaire unPanelProprietaire = new PanelProprietaire();
    public static PanelReservations unPanelReservations = new PanelReservations();
    public static PanelContrats unPanelContrats = new PanelContrats();
    public static PanelStats unPanelStats = new PanelStats();

    public VueGenerale() {
        this.setTitle("Gestion Neige et Soleil 2025");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.darkGray);
		this.setLayout(null);
		this.setBounds(50, 50, 1000, 600);



        this.panelMenu.setBackground(Color.white);
        this.panelMenu.setBounds(50, 10, 900, 40);
        this.panelMenu.setLayout(new GridLayout(1,6));
        this.panelMenu.add(this.btUser);
        this.panelMenu.add(this.btProprietaire);
        this.panelMenu.add(this.btReservations);
        this.panelMenu.add(this.btContrats);
        this.panelMenu.add(this.btStats);
        this.panelMenu.add(this.btQuitter);
        this.add(this.panelMenu);

        //rendre les boutons cliquables

        this.btUser.addActionListener(this);
        this.btProprietaire.addActionListener(this);
        this.btReservations.addActionListener(this);
        this.btContrats.addActionListener(this);
        this.btStats.addActionListener(this);
        this.btQuitter.addActionListener(this);

        //ajout des panels dans la vue générale 
		this.add(unPanelUser); 
		this.add(unPanelProprietaire); 
		//this.add(unPanelReservations); 
		//this.add(unPanelContrats);  
		//this.add(unPanelStats); 
		
		this.setVisible(true);
     
    }

    private void afficherPanel(int choix){
        unPanelUser.setVisible(false);
        unPanelProprietaire.setVisible(false);
        unPanelReservations.setVisible(false);
        unPanelContrats.setVisible(false);
        //unPanelStats.setVisible(false);
        switch(choix) {
            case 1: unPanelUser.setVisible(true); break; 
            case 2: unPanelProprietaire.setVisible(true); break; 
            case 3: unPanelReservations.setVisible(true); break; 
            case 4: unPanelContrats.setVisible(true); break; 
            case 5: unPanelStats.setVisible(true); break; 
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String choix = e.getActionCommand();
        switch(choix) {
            case "Utilisateur": this.afficherPanel(1); break; 
            case "Proprietaires": this.afficherPanel(2); break; 
            case "Reservations": this.afficherPanel(3); break; 
            case "Contrats": this.afficherPanel(4); break;
            case "Statistiques": this.afficherPanel(5); break;
            case "Quitter": Neige.creerVueGenerale(false); 
                            Neige.rendreVisibleVueConnexion(true);break;
        }
        

    }

    
}