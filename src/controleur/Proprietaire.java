package controleur;

public class Proprietaire {

    private int id_proprietaire, id_ville, code_postal;
    private String nom, prenom, adresse, ville, tel, email, mdp, role;
    

public Proprietaire() {

}


public int getId_proprietaire() {
    return id_proprietaire;
}


public void setId_proprietaire(int id_proprietaire) {
    this.id_proprietaire = id_proprietaire;
}


public int getId_ville() {
    return id_ville;
}


public void setId_ville(int id_ville) {
    this.id_ville = id_ville;
}


public int getCode_postal() {
    return code_postal;
}


public void setCode_postal(int code_postal) {
    this.code_postal = code_postal;
}


public String getNom() {
    return nom;
}


public void setNom(String nom) {
    this.nom = nom;
}


public String getPrenom() {
    return prenom;
}


public void setPrenom(String prenom) {
    this.prenom = prenom;
}


public String getAdresse() {
    return adresse;
}


public void setAdresse(String adresse) {
    this.adresse = adresse;
}


public String getVille() {
    return ville;
}


public void setVille(String ville) {
    this.ville = ville;
}


public String getTel() {
    return tel;
}


public void setTel(String tel) {
    this.tel = tel;
}


public String getEmail() {
    return email;
}


public void setEmail(String email) {
    this.email = email;
}


public String getMdp() {
    return mdp;
}


public void setMdp(String mdp) {
    this.mdp = mdp;
}


public String getRole() {
    return role;
}


public void setRole(String role) {
    this.role = role;
}


}