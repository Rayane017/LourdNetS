package controleur;

import java.math.BigDecimal;

public class CaParDpt {
    
    private int rang; 
    private String dpt;
    private BigDecimal ca;


    public CaParDpt(){
    }

    public int getRang() {
        return rang;
    }

    public String getDpt() {
        return dpt;
    }

    public BigDecimal getCa() {
        return ca;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public void setCa(BigDecimal ca) {
        this.ca = ca;
    }

    }
