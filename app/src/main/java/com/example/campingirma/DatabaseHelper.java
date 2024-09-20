package com.example.campingirma;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;

    public static final String DATABASE_NAME = "DataBaseIRMA.db";
    public static final int DATABASE_VERSION = 1;

    // Table Sejour
    public static final String TB_SEJOUR = "sejour";
    public static final String SEJOUR_ID = "id";
    public static final String SEJOUR_DATE_DEBUT = "date_debut";
    public static final String SEJOUR_DATE_FIN = "date_fin";
    public static final String SEJOUR_PRIX = "prix";

    //Equitation
    public static final String TB_EQUITATION = "TB_EQUITATION";
    public static final String EQUITATION_ID = "id";
    public static final String EQUITATION_NUM_PARCOURS = "num_parcours";
    public static final String EQUITATION_PRIX_SEMAINE = "prix_semaine";
    public static final String EQUITATION_PRIX_FINSEMAINE = "prix_finsemaine";


    // Table Canot
    public static final String TB_CANOT = "canot";
    public static final String CANOT_ID = "id";
    public static final String CANOT_SEMAINE_PRIX = "semaine_prix";
    public static final String CANOT_F_SEMAINE_PRIX = "f_semaine_prix";
    public static final String CANOT_NBRE_HEURES = "nbre_heures";

    // Table Escalade
    public static final String TB_ESCALADE = "escalade";
    public static final String ESCALADE_ID = "id";
    public static final String ESCALADE_NBRE_H = "nbre_heure";
    public static final String ESCALADE_PRIX = "prix";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSejour = "CREATE TABLE " + TB_SEJOUR + " (" + SEJOUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SEJOUR_DATE_DEBUT + " TEXT, " + SEJOUR_DATE_FIN + " TEXT, " + SEJOUR_PRIX + " REAL" + ")";
        db.execSQL(createTableSejour);


        String createTableEquitation = "CREATE TABLE " + TB_EQUITATION + " (" + EQUITATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EQUITATION_NUM_PARCOURS + " INTEGER, " + EQUITATION_PRIX_SEMAINE + " REAL, " + EQUITATION_PRIX_FINSEMAINE + " REAL" + ")";
        db.execSQL(createTableEquitation);

        String createTableCanot = "CREATE TABLE " + TB_CANOT + " (" + CANOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CANOT_SEMAINE_PRIX + " REAL, " + CANOT_F_SEMAINE_PRIX + " REAL, " + CANOT_NBRE_HEURES + " INTEGER" + ")";
        db.execSQL(createTableCanot);

        String createTableEscalade = "CREATE TABLE " + TB_ESCALADE + " (" + ESCALADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ESCALADE_NBRE_H + " INTEGER, " + ESCALADE_PRIX + " REAL" + ")";
        db.execSQL(createTableEscalade);

        String createTableFactureFinal = "CREATE TABLE IF NOT EXISTS Facture_final" +
                " (column_0 TEXT, column_1 TEXT, column_2 TEXT,column3 TEXT,column_4 TEXT,column_5 TEXT,column_6 TEXT,column_7 TEXT,column_8 TEXT,column_9 TEXT,column_10 TEXT,column_11 TEXT,column_12 TEXT )";
        db.execSQL(createTableFactureFinal);

        // Insertion de données dans la table 'sejour'
        ContentValues valuesSejour1 = new ContentValues();
        valuesSejour1.put(SEJOUR_DATE_DEBUT, "2023-04-01");
        valuesSejour1.put(SEJOUR_DATE_FIN, "2023-05-31");
        valuesSejour1.put(SEJOUR_PRIX, 18.90);
        db.insert(TB_SEJOUR, null, valuesSejour1);
        ContentValues valuesSejour2 = new ContentValues();
        valuesSejour2.put(SEJOUR_DATE_DEBUT, "2023-06-01");
        valuesSejour2.put(SEJOUR_DATE_FIN, "2023-08-31");
        valuesSejour2.put(SEJOUR_PRIX, 23.25);
        db.insert(TB_SEJOUR, null, valuesSejour2);
        ContentValues valuesSejour3 = new ContentValues();
        valuesSejour3.put(SEJOUR_DATE_DEBUT, "2023-09-01");
        valuesSejour3.put(SEJOUR_DATE_FIN, "2023-10-31");
        valuesSejour3.put(SEJOUR_PRIX, 20.25);
        db.insert(TB_SEJOUR, null, valuesSejour3);




        // Insertion de données dans la table 'equitance'
        ContentValues valuesEquitation1 = new ContentValues();
        valuesEquitation1.put(EQUITATION_NUM_PARCOURS, 1);
        valuesEquitation1.put(EQUITATION_PRIX_SEMAINE, 15.25);
        valuesEquitation1.put(EQUITATION_PRIX_FINSEMAINE, 18.25);
        db.insert(TB_EQUITATION, null, valuesEquitation1);
        ContentValues valuesEquitation2 = new ContentValues();
        valuesEquitation2.put(EQUITATION_NUM_PARCOURS, 2);
        valuesEquitation2.put(EQUITATION_PRIX_SEMAINE, 22.75);
        valuesEquitation2.put(EQUITATION_PRIX_FINSEMAINE, 25.50);
        db.insert(TB_EQUITATION, null, valuesEquitation2);




        // Insertion de données dans la table 'canot'
        ContentValues valuesCanot = new ContentValues();
        valuesCanot.put(CANOT_SEMAINE_PRIX, 22.35);
        valuesCanot.put(CANOT_F_SEMAINE_PRIX, 29.55);
        valuesCanot.put(CANOT_NBRE_HEURES, 2);
        db.insert(TB_CANOT, null, valuesCanot);



        // Insertion de données dans la table 'escalade'
        ContentValues valuesEscalade = new ContentValues();
        valuesEscalade.put(ESCALADE_NBRE_H, 1);
        valuesEscalade.put(ESCALADE_PRIX, 10);
        db.insert(TB_ESCALADE, null, valuesEscalade);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_SEJOUR);
        db.execSQL("DROP TABLE IF EXISTS " + TB_EQUITATION);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CANOT);
        db.execSQL("DROP TABLE IF EXISTS " + TB_ESCALADE);
        onCreate(db);
    }

}