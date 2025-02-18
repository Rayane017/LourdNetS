package controleur;

public class Appartement {
    
    private String idBatiment; 
    private int numeroAppartement;


    public Appartement(){
    }

    public String getIdBatiment() {
        return idBatiment;
    }

    public int getNumeroAppartement() {
        return numeroAppartement;
    }

    public void setIdBatiment(String idBatiment) {
        this.idBatiment = idBatiment;
    }

    public void setNumeroAppartement(int numeroAppartement) {
        this.numeroAppartement = numeroAppartement;
    }

}
