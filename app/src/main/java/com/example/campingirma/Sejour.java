package com.example.campingirma;

public class Sejour {
    private String nom;
    private String prenom;

    private String dateDebut;
    private String dateFin;
    private double lecompteur;
    private double nombreJoursSejour;

    private double prixSejour;
    private double prixEquitation;
    private double  prixCanot;
    private double prixEscalade;


    public Sejour(String nom, String prenom, String dateDebut, String dateFin, double lecompteur, double nombreJoursSejour, double prixSejour, double prixEquitation, double prixCanot, double prixEscalade) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDebut = this.dateDebut;
        this.dateFin = this.dateFin;
        this.lecompteur = lecompteur;
        this.nombreJoursSejour = nombreJoursSejour;
        this.prixSejour = prixSejour;
        this.prixEquitation = prixEquitation;
        this.prixCanot = prixCanot;
        this.prixEscalade = prixEscalade;

    }

    public double getLecompteur() {
        return lecompteur;
    }

    public void setLecompteur(double lecompteur) {
        this.lecompteur = lecompteur;
    }

    public double getprixEquitance() {
        return prixEquitation;
    }

    public void setprixEquitance(double prixequitance) {
        prixEquitation = prixequitance;
    }

    public double getprixCanot() {
        return prixCanot;
    }

    public void setprixCanot(double prixcanot) {
        prixCanot = prixcanot;
    }

    public double getprixEscalade() {
        return prixEscalade;
    }

    public void setprixEscalade(double prixescalade) {
        prixEscalade = prixescalade;
    }

    public String getNom() {
        return nom;
    }

    public double getleleCompteur() {
        return lecompteur;
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


    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public double getNombreJoursSejour() {
        return nombreJoursSejour;
    }

    public void setNombreJoursSejour(double nombreJoursSejour) {
        this.nombreJoursSejour = nombreJoursSejour;
    }

    public double getPrixSejour() {
        return prixSejour;
    }

    public void setPrixSejour(double prixSejour) {
        this.prixSejour = prixSejour;
    }


    public double calculerPrixTotal() {
        return prixSejour + prixEquitation + prixCanot + prixEscalade;
    }

}