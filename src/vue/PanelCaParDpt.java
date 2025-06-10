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
import controleur.CaParDpt;
import controleur.Tableau;

public class PanelCaParDpt extends PanelPrincipal implements ActionListener
{
    private JTable tableCaParDpt; 
    private Tableau tableauCaParDpt; 
    
    private JPanel panelFiltre = new JPanel(); 
    private JTextField txtFiltre = new JTextField(); 
    private JButton btFiltrer = new JButton("Filtrer");
    private JButton btActualiser = new JButton("Actualiser");
    
    private JLabel lbNBCaParDpt = new JLabel();
 
    
    public PanelCaParDpt() {
        super("Consultation du Chiffre d'Affaires par Département");
        
        //installation de la JTable 
        String entetes[] = {"Rang", "Département", "Chiffre d'Affaires (€)"};
        this.tableauCaParDpt = new Tableau(this.obtenirDonnees(""), entetes); 
        this.tableCaParDpt = new JTable(this.tableauCaParDpt);
        
        // Rendre la table non éditable
        this.tableCaParDpt.setEnabled(false);
        
        JScrollPane uneScroll = new JScrollPane(this.tableCaParDpt);
        uneScroll.setBounds(50, 100, 600, 300);
        this.add(uneScroll); 
        
        //installation du panel filtre 
        this.panelFiltre.setBackground(Color.cyan);
        this.panelFiltre.setBounds(50, 60, 600, 30);
        this.panelFiltre.setLayout(new GridLayout(1, 4));
        this.panelFiltre.add(new JLabel("Filtrer par département : ")); 
        this.panelFiltre.add(this.txtFiltre); 
        this.panelFiltre.add(this.btFiltrer); 
        this.panelFiltre.add(this.btActualiser);
        this.add(this.panelFiltre);
        
        // Rendre les boutons écoutables
        this.btFiltrer.addActionListener(this);
        this.btActualiser.addActionListener(this);
        
        //installation du compteur 
        this.lbNBCaParDpt.setBounds(50, 420, 400, 20);
        this.add(this.lbNBCaParDpt);
        this.lbNBCaParDpt.setText("Nombre d'entrées : " + this.tableauCaParDpt.getRowCount());
    }

    public Object[][] obtenirDonnees(String filtre) {

        ArrayList<CaParDpt> lesCaParDpt; 
        
        lesCaParDpt = Controleur.selectAllCaParDpt();
       
        
        Object matrice[][] = new Object[lesCaParDpt.size()][3];
        int i = 0; 
        for (CaParDpt unCaParDpt : lesCaParDpt) {
            matrice[i][0] = i + 1;
            matrice[i][1] = unCaParDpt.getDpt(); 
            matrice[i][2] = unCaParDpt.getCa(); 
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
            this.tableauCaParDpt.setDonnees(this.obtenirDonnees(filtre));
            this.lbNBCaParDpt.setText("Nombre d'entrées : " + this.tableauCaParDpt.getRowCount());
        }
        else if (e.getSource() == this.btActualiser) {
            // Actualiser toutes les données (sans filtre)
            this.txtFiltre.setText("");
            this.tableauCaParDpt.setDonnees(this.obtenirDonnees(""));
            this.lbNBCaParDpt.setText("Nombre d'entrées : " + this.tableauCaParDpt.getRowCount());
        }
    }
    
    @Override
    public void actualiser() {
        this.tableauCaParDpt.setDonnees(this.obtenirDonnees(""));
        this.lbNBCaParDpt.setText("Nombre d'entrées : " + this.tableauCaParDpt.getRowCount());
    }
}


















 