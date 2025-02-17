package controleur;




public class User {
    
    private int id_user, id_ville, cp;
    private String nom, prenom, adresse, ville, email, telephone, mdp, role;
    
        public User(int id_user, int id_ville, String nom, String prenom, String adresse, int cp, String telephone, String email, String mdp, String role) {
            this.id_user = id_user;
            this.id_ville = id_ville;
            this.cp = cp;
            this.nom = nom;
            this.prenom = prenom;
            this.adresse = adresse;
            this.email = email;
            this.telephone = telephone;
            this.mdp = mdp;
            this.role = role;
        }

        public User (int id_ville, String nom, String prenom, String adresse, String ville, int cp, String telephone, String email, String mdp, String role) {
            this.id_user = 0;
            this.id_ville = id_ville;
            this.cp = cp;
            this.nom = nom;
            this.prenom = prenom;
            this.adresse = adresse;
            this.email = email;
            this.telephone = telephone;
            this.mdp = mdp;
            this.role = role;
            this.ville = ville;
        }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelphone(String telephone) {
        this.telephone = telephone;
    }

    public void setIdVille(int id_ville) {
        this.id_ville = id_ville;
    }

    public int getIdVille() {
        return id_ville;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
