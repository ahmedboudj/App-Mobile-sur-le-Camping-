package com.example.campingirma;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservationtemAdapter extends ArrayAdapter<Sejour> {

    private Context context;
    private ArrayList<Sejour> sejourItemList;

    public ReservationtemAdapter(Context context, ArrayList<Sejour> sejourItemList) {
        super(context, 0, sejourItemList);
        this.context = context;
        this.sejourItemList = sejourItemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sejour sjr = sejourItemList.get(position);

        LayoutInflater layoutInflater=LayoutInflater.from(this.context);



        convertView = LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false);


        TextView nomTextView = convertView.findViewById(R.id.textnom);
        TextView prenomTextView = convertView.findViewById(R.id.textprenom);
        TextView ntelTextView = convertView.findViewById(R.id.textntel);

        TextView datedebutTextView = convertView.findViewById(R.id.textdatedebut);
        TextView datefinTextView = convertView.findViewById(R.id.textdatefin);
        TextView textlecompteur = convertView.findViewById(R.id.textlecompteur);
        TextView textnbrejour = convertView.findViewById(R.id.textnbrejour);
        TextView textprixjour = convertView.findViewById(R.id.textprixjour);
        TextView textequitation = convertView.findViewById(R.id.textequitation);
        TextView textcanot = convertView.findViewById(R.id.textcanot);
        TextView textescalade = convertView.findViewById(R.id.textescalade);

        nomTextView.setText("Nom : " + sjr.getNom());
        prenomTextView.setText("Prenom : " + sjr.getPrenom());
        datedebutTextView.setText("Date_debut : " + sjr.getDateDebut());
        datefinTextView.setText("Date_fin : " + sjr.getDateFin());
        textlecompteur.setText("Nmbre_personne : " + sjr.getleleCompteur());
        textnbrejour.setText("Nbre_jour : " + sjr.getNombreJoursSejour());
        textprixjour.setText("Prix_sejour : " + sjr.getPrixSejour());
        textequitation.setText("Equitation : " + sjr.getprixEquitance());
        textcanot.setText("Canot : " + sjr.getprixCanot());
        textescalade.setText("Escalade : " + sjr.getprixEscalade());

        return convertView;
    }
}
