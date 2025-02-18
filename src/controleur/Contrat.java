package controleur;

import java.sql.Date;
import java.time.Year;

public class Contrat {

    private String idBatiment, RIB, etat; 
    private int idContrat, idProprietaire, numeroAppartement, anneeLocation; 
    private float tarifHaute, tarifMoyen, tarifBasse; 
    private Date dateInactif; 
    
    
    public Contrat() {}
    
    
    
    
    public String getIdBatiment() {
        return idBatiment;
    }
    public void setIdBatiment(String idBatiment) {
        this.idBatiment = idBatiment;
    }
    public String getRIB() {
        return RIB;
    }
    public void setRIB(String rIB) {
        RIB = rIB;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public int getIdContrat() {
        return idContrat;
    }
    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }
    public int getIdProprietaire() {
        return idProprietaire;
    }
    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
    }
    public int getNumeroAppartement() {
        return numeroAppartement;
    }
    public void setNumeroAppartement(int numeroAppartement) {
        this.numeroAppartement = numeroAppartement;
    }
    public float getTarifHaute() {
        return tarifHaute;
    }
    public void setTarifHaute(float tarifHaute) {
        this.tarifHaute = tarifHaute;
    }
    public float getTarifMoyen() {
        return tarifMoyen;
    }
    public void setTarifMoyen(float tarifMoyen) {
        this.tarifMoyen = tarifMoyen;
    }
    public float getTarifBasse() {
        return tarifBasse;
    }
    public void setTarifBasse(float tarifBasse) {
        this.tarifBasse = tarifBasse;
    }
    public Date getDateInactif() {
        return dateInactif;
    }
    public void setDateInactif(Date dateInactif) {
        this.dateInactif = dateInactif;
    }
    public int getAnneeLocation() {
        return anneeLocation;
    }
    public void setAnneeLocation(int anneeLocation) {
        this.anneeLocation = anneeLocation;
    } 

    
    
    
}
