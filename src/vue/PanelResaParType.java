package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.ResaParType;
import controleur.Tableau;

public class PanelResaParType extends PanelPrincipal implements ActionListener
{
    private JTable tableResaParType; 
    private Tableau tableauResaParType; 
    
    private JPanel panelFiltre = new JPanel(); 
    private JTextField txtFiltre = new JTextField(); 
    private JButton btFiltrer = new JButton("Filtrer");
    private JButton btActualiser = new JButton("Actualiser");
    
    private JLabel lbNBResaParType = new JLabel();
 
    
    public PanelResaParType() {
        super("Consultation des Réservations par Type");
        
        //installation de la JTable 
        String entetes[] = {"Rang", "Type", "Nombre de Réservations"};
        this.tableauResaParType = new Tableau(this.obtenirDonnees(""), entetes); 
        this.tableResaParType = new JTable(this.tableauResaParType);
        
        // Rendre la table non éditable
        this.tableResaParType.setEnabled(false);
        
        JScrollPane uneScroll = new JScrollPane(this.tableResaParType);
        uneScroll.setBounds(50, 100, 600, 300);
        this.add(uneScroll); 
        
        //installation du panel filtre 
        this.panelFiltre.setBackground(Color.cyan);
        this.panelFiltre.setBounds(50, 60, 600, 30);
        this.panelFiltre.setLayout(new GridLayout(1, 4));
        this.panelFiltre.add(new JLabel("Filtrer par type : ")); 
        this.panelFiltre.add(this.txtFiltre); 
        this.panelFiltre.add(this.btFiltrer); 
        this.panelFiltre.add(this.btActualiser);
        this.add(this.panelFiltre);
        
        // Rendre les boutons écoutables
        this.btFiltrer.addActionListener(this);
        this.btActualiser.addActionListener(this);
        
        //installation du compteur 
        this.lbNBResaParType.setBounds(50, 420, 400, 20);
        this.add(this.lbNBResaParType);
        this.lbNBResaParType.setText("Nombre d'entrées : " + this.tableauResaParType.getRowCount());
    }

    public Object[][] obtenirDonnees(String filtre) {

        ArrayList<ResaParType> lesResaParType; 
        
            lesResaParType = Controleur.selectAllResaParType();
       
        
        Object matrice[][] = new Object[lesResaParType.size()][3];
        int i = 0; 
        for (ResaParType uneResaParType : lesResaParType) {
            matrice[i][0] = i + 1;
            matrice[i][1] = uneResaParType.getType(); 
            matrice[i][2] = uneResaParType.getNbResa(); 
            i++;
        }
        return matrice; 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btFiltrer) {
            //on récupère le filtre
            String filtre = this.txtFiltre.getText(); 
            
            //on actualise les données 
            this.tableauResaParType.setDonnees(this.obtenirDonnees(filtre));
            this.lbNBResaParType.setText("Nombre d'entrées : " + this.tableauResaParType.getRowCount());
        }
        else if (e.getSource() == this.btActualiser) {
            // Actualiser toutes les données (sans filtre)
            this.txtFiltre.setText("");
            this.tableauResaParType.setDonnees(this.obtenirDonnees(""));
            this.lbNBResaParType.setText("Nombre d'entrées : " + this.tableauResaParType.getRowCount());
        }
    }
    
    @Override
    public void actualiser() {
        this.tableauResaParType.setDonnees(this.obtenirDonnees(""));
        this.lbNBResaParType.setText("Nombre d'entrées : " + this.tableauResaParType.getRowCount());
    }
}


















 