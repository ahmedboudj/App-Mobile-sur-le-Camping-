package com.example.campingirma;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.view.MenuItem;
import android.widget.RadioButton;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import android.database.sqlite.SQLiteException;
import java.util.Date;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private TextView textdd;
    private int a =1;
    private TextView textdf;
    private DatabaseHelper dbHelper;
    public static final String TB_EQUITATION = "equitation";
    private TextView textcompteur;
    private int counter = 1;
    double prix_esc = 0;
    double counterb=0;
    double countera=0;
    double prix_esca = 0;
    private int counter2 = 1;
    private TextView textcompteur2;
    private ListView listView;
    private ArrayList<Sejour> reservationItemArrayList;

    private ReservationtemAdapter reservationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_deux);




        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.listViewReservations);
        reservationItemArrayList = new ArrayList<Sejour>();
        textdd = findViewById(R.id.textdebut);
        textdf = findViewById(R.id.textfin);
        textcompteur = findViewById(R.id.textcompteur);

        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonPlus);

        reservationAdapter = new ReservationtemAdapter(this, reservationItemArrayList);
        listView.setAdapter(reservationAdapter);

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter > 1) {
                    counter--;
                    textcompteur.setText(String.valueOf(counter));
                } else {
                    Toast.makeText(getApplicationContext(), "Invalide : minimum une personne !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textcompteur.setText(String.valueOf(counter));
            }
        });


        Button buttonNew = findViewById(R.id.buttonNew);
        TextView textViewNew = findViewById(R.id.textdebut);

        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la date actuelle
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Créer un DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity2.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
                                textViewNew.setText(formattedDate);
                            }
                        }, year, 4, 31);
                datePickerDialog.show();
            }
        });

        Button buttonNew2 = findViewById(R.id.buttonNew2);
        TextView textViewNew2 = findViewById(R.id.textfin);

        buttonNew2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la date actuelle
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Créer un DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity2.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
                                textViewNew2.setText(formattedDate);
                            }
                        }, year, 5, dayOfMonth); // Utiliser les valeurs actuelles pour initialiser le DatePickerDialog
                datePickerDialog.show();
            }
        });

        Button bottomButton = findViewById(R.id.btnReservation);
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nombrePersonnes = Integer.parseInt(textcompteur.getText().toString());


                EditText editTextNom = findViewById(R.id.editTextNom);
                EditText editTextPrenom = findViewById(R.id.editTextPrenom);
                TextView textcompteur = findViewById(R.id.textcompteur);
                TextView textViewNew = findViewById(R.id.textdebut);
                TextView textViewNew2 = findViewById(R.id.textfin);
                String dateDebut = textViewNew.getText().toString();
                String dateFin = textViewNew2.getText().toString();
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String compteur = textcompteur.getText().toString();






                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date startDate;
                Date endDate;
                long differenceDays = 0;
                // tester la connection base de donnee

                try {
                    startDate = format.parse(dateDebut);
                    endDate = format.parse(dateFin);
                    long difference = endDate.getTime() - startDate.getTime();
                    differenceDays = difference / (1000 * 60 * 60 * 24);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (differenceDays == 1) {
                    differenceDays++;

                }

                   StringBuilder message = new StringBuilder();
                    message.append("NOM: ").append(nom).append("\n");
                    message.append("Prenom: ").append(prenom).append("\n\n");
                    message.append("Nombre Total des personnes: ").append(compteur).append("\n\n");
                    message.append("Date de debut: ").append(dateDebut).append("\n");
                    message.append("Date de fin: ").append(dateFin).append("\n");
                    message.append("Nombre de jours de séjour: ").append(differenceDays).append("\n\n");



                    double prixsejour = 0.0;
                    int nombrePersonne = Integer.parseInt(textcompteur.getText().toString());


                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        Date startDated = dateFormat.parse(dateDebut);
                        Date endDated = dateFormat.parse(dateFin);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDated);

                        while (!calendar.getTime().after(endDated)) {
                            int month = calendar.get(Calendar.MONTH);

                            if ((month >= Calendar.MAY && month <= Calendar.JUNE) && (month < Calendar.JULY)) {
                                if (nombrePersonne == 1) {
                                    prixsejour = (18.9 * differenceDays);
                                } else if (nombrePersonne > 1) {
                                    prixsejour = (18.9 * differenceDays) + (18.9 * nombrePersonne);

                                }


                            } else if ((month >= Calendar.JUNE && month <= Calendar.AUGUST) && (month < Calendar.SEPTEMBER)) {

                                if (nombrePersonne == 1) {
                                    prixsejour = (23.25 * nombrePersonne);
                                } else if (nombrePersonne > 1) {
                                    prixsejour = (23.25 * nombrePersonne) + (23.25 * (differenceDays));

                                }

                            } else if (month >= Calendar.SEPTEMBER && month <= Calendar.OCTOBER) {
                                if (nombrePersonne == 1) {
                                    prixsejour = (20.25 * nombrePersonne);
                                } else if (nombrePersonne > 1) {
                                    prixsejour = (20.25 * nombrePersonne) + (20.25 * (differenceDays));

                                }
                            } else {
                                Toast.makeText(MainActivity2.this, "Not Found", Toast.LENGTH_SHORT).show();
                            }

                            calendar.add(Calendar.DAY_OF_MONTH, 1);

                        }


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    message.append("Prix Sejour: ").append(String.format("%.2f", prixsejour)).append(" $").append("\n");

                if (nom.isEmpty() || prenom.isEmpty() ) {
                    Toast.makeText(MainActivity2.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }
else {
                    Sejour sjr = new Sejour(nom, prenom, dateDebut, dateFin, counter, differenceDays, prixsejour, 0, 0, 0);
                    reservationAdapter.add(sjr);
                    reservationAdapter.notifyDataSetChanged();

                    listView.setAdapter(reservationAdapter);
                    listView.setAdapter(reservationAdapter);

                    editTextNom.getText().clear();
                    editTextPrenom.getText().clear();
                    textViewNew.setText("");
                    textViewNew2.setText("");

                }

                AlertDialog.Builder builderrr = new AlertDialog.Builder(MainActivity2.this);
                builderrr.setTitle("votre réservation");
                builderrr.setMessage(message.toString());
                builderrr.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder anotherBuilder = new AlertDialog.Builder(MainActivity2.this);

                        anotherBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String messagev = "Voir facture!" ;
                                Toast.makeText(MainActivity2.this, messagev, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        anotherBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog anotherDialog = anotherBuilder.create();
                        anotherDialog.show();
                        dialog.dismiss();
                    }
                });

                builderrr.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builderrr.create();
                alertDialog.show();
                try {
                    DatabaseHelper databaseGateway = new DatabaseHelper(MainActivity2.this);
                    SQLiteDatabase db = databaseGateway.getReadableDatabase();
                    Toast.makeText(MainActivity2.this, "Connexion a la base de donnees reussie", Toast.LENGTH_SHORT).show();
                    db.close();
                } catch (SQLiteException e) {
                    Toast.makeText(MainActivity2.this, "Probleme de connexion a la base de donnees", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnConfirmer = findViewById(R.id.buttonConfirmationFinal);
        btnConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Création d'une instance du DatabaseHelper
                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity2.this);

                // Récupération de la liste des réservations et insertion dans la table Facture_final
                List<List<Object>> dataToInsert = new ArrayList<>();
                for (Sejour reservation : reservationItemArrayList) {
                    List<Object> rowData = new ArrayList<>();

                    // Ajoutez les éléments de l'objet reservation à rowData dans l'ordre approprié

                    rowData.add(reservation.getNom());
                    rowData.add(reservation.getPrenom());
                    rowData.add(reservation.getDateDebut());
                    rowData.add(reservation.getleleCompteur());
                    rowData.add(reservation.getNombreJoursSejour());
                    rowData.add(reservation.getPrixSejour());
                    rowData.add(reservation.getprixEquitance());
                    rowData.add(reservation.getprixCanot());
                    rowData.add(reservation.getprixEscalade());

                    dataToInsert.add(rowData);
                }

                StringBuilder tableauContenu = new StringBuilder();
                for (List<Object> row : dataToInsert) {
                    for (Object item : row) {
                        tableauContenu.append(item.toString()).append(" ");
                    }
                    tableauContenu.append("\n");
                }

                // Affichage du contenu du tableau dans un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Contenu du tableau");
                builder.setMessage(tableauContenu.toString());

                builder.setPositiveButton("OK", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void addItemToList() {
        int nombrePersonnes = Integer.parseInt(textcompteur.getText().toString());

        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);
        TextView textcompteur = findViewById(R.id.textcompteur);
        TextView textViewNew = findViewById(R.id.textdebut);
        TextView textViewNew2 = findViewById(R.id.textfin);
        String dateDebut = textViewNew.getText().toString();
        String dateFin = textViewNew2.getText().toString();
        String nom = editTextNom.getText().toString();
        String prenom = editTextPrenom.getText().toString();
        String compteur = textcompteur.getText().toString();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST + 0, 0, "Equitation");
        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Canot");
        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "Escalade");
        menu.add(Menu.NONE, Menu.FIRST + 3, 3, "Facture");
        menu.add(Menu.NONE, Menu.FIRST + 4, 4, "Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
// "Equitation"
            case Menu.FIRST:
                LayoutInflater inflaterEqui = getLayoutInflater();
                View dialogViewEqui = inflaterEqui.inflate(R.layout.equitation_layout_menu, null);

                DatabaseHelper dbHelperEqui = new DatabaseHelper(MainActivity2.this);
                SQLiteDatabase dbEqui = dbHelperEqui.getReadableDatabase();
                Cursor cursorEqui = dbEqui.query(DatabaseHelper.TB_EQUITATION, null, null, null, null, null, null);

                StringBuilder messageEqui = new StringBuilder();
                messageEqui.append("\n\n  Équitation\n\n" + "\n\n   Par personne :\n" + " ");

                if (cursorEqui != null) {
                    if (cursorEqui.moveToFirst()) {
                        messageEqui.append("\n\nnparcour\t\t\t\tsemaine\t\t\t\tF_semaine\n");
                        do {
                            int nparcours = cursorEqui.getInt(cursorEqui.getColumnIndexOrThrow(DatabaseHelper.EQUITATION_NUM_PARCOURS));
                            double smn = cursorEqui.getDouble(cursorEqui.getColumnIndexOrThrow(DatabaseHelper.EQUITATION_PRIX_SEMAINE));
                            double fsmn = cursorEqui.getDouble(cursorEqui.getColumnIndexOrThrow(DatabaseHelper.EQUITATION_PRIX_FINSEMAINE));

                            messageEqui.append("\t\t\t\t\t").append(nparcours).append("\t\t\t\t\t\t\t")
                                    .append(smn).append("\t\t\t\t\t\t\t")
                                    .append(fsmn).append("\n");
                            a++;
                        } while (cursorEqui.moveToNext());
                        cursorEqui.close();
                    } else {
                        messageEqui.append("Aucune donnée d'équitation disponible.");
                    }
                }

                // Affichage des données dans une AlertDialog
                AlertDialog.Builder builderEqui = new AlertDialog.Builder(MainActivity2.this);
                builderEqui.setTitle("Table Equitation");
                builderEqui.setMessage(messageEqui.toString());
                builderEqui.setView(dialogViewEqui);

                builderEqui.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

// Trouver les boutons et le champ de texte dans le layout equitation_layout_menu
                Button buttonPlus2 = dialogViewEqui.findViewById(R.id.buttonPlus2);
                Button buttonMinus2 = dialogViewEqui.findViewById(R.id.buttonMinus2);
                TextView textViewCounter2 = dialogViewEqui.findViewById(R.id.textViewCounter2);
                RadioButton radioParcours1 = dialogViewEqui.findViewById(R.id.radioParcours1);
                RadioButton radioParcours2 = dialogViewEqui.findViewById(R.id.radioParcours2);
                RadioButton radioSemaine = dialogViewEqui.findViewById(R.id.radioSemaine);
                RadioButton radioFinDeSemaine = dialogViewEqui.findViewById(R.id.radioFinDeSemaine);

                buttonPlus2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter++;
                        textViewCounter2.setText(String.valueOf(counter));
                    }
                });

                buttonMinus2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter--;
                        textViewCounter2.setText(String.valueOf(counter));
                    }
                });
                Button buttonAjouter = dialogViewEqui.findViewById(R.id.buttonAjouter);
                buttonAjouter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int counter2 = Integer.parseInt(textViewCounter2.getText().toString());
                        double prix_equitation = 0.0;

                        if (radioParcours1.isChecked()) {
                            if (radioSemaine.isChecked()) {
                                prix_equitation = 15.25 * counter2;
                            } else if (radioFinDeSemaine.isChecked()) {
                                prix_equitation = 18.25 * counter2;
                            }
                        } else if (radioParcours2.isChecked()) {
                            if (radioSemaine.isChecked()) {
                                prix_equitation = 22.75 * counter2;
                            } else if (radioFinDeSemaine.isChecked()) {
                                prix_equitation = 25.5 * counter2;
                            }
                        }
                        Sejour premierSejour = reservationItemArrayList.get(0);
                        premierSejour.setprixEquitance(prix_equitation); // Mettre à jour le champ prixEquitance
                        reservationAdapter.notifyDataSetChanged();
                        reservationAdapter.add(premierSejour); // Utilisation de l'instance reservationAdapter pour ajouter le nouvel élément


                        listView.setAdapter(reservationAdapter);

                        // Afficher le prix dans un Toast ou un autre élément visuel
                        String message = "Ce montant : " + prix_equitation +" sera ajouter a votre facture final ";
                        Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();

                    }


                });

                AlertDialog alertDialogEqui = builderEqui.create();
                alertDialogEqui.show();

                return true;

// canot
            case Menu.FIRST + 1:
                LayoutInflater inflatercanot = getLayoutInflater();
                View dialogViewcanot = inflatercanot.inflate(R.layout.canot_layout_menu, null);
                DatabaseHelper dbHelpercanot = new DatabaseHelper(MainActivity2.this);
                SQLiteDatabase db1 = dbHelpercanot.getReadableDatabase();
                Cursor cursor1 = db1.query(DatabaseHelper.TB_CANOT, null, null, null, null, null, null);

                StringBuilder message1 = new StringBuilder();
                message1.append(
                        "Par embarcation et chaque deux heures\n" +
                        " ");

                if (cursor1 != null && cursor1.moveToFirst()) {
                    int semaine = cursor1.getInt(cursor1.getColumnIndexOrThrow(DatabaseHelper.CANOT_SEMAINE_PRIX));
                    double finsemaine = cursor1.getDouble(cursor1.getColumnIndexOrThrow(DatabaseHelper.CANOT_F_SEMAINE_PRIX));

                    cursor1.close();
                } else {
                    message1.append("Aucune donnée de canot disponible.");
                }

                // Affichage des données dans une AlertDialog
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity2.this);
                builder1.setTitle("Table Canot");
                builder1.setMessage(message1.toString());

                builder1.setView(dialogViewcanot);
                builder1.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String messagevv = "Un montant est ajouter a votre facture !!!" ;
                        Toast.makeText(MainActivity2.this, messagevv, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                RadioButton radioSemainecanot = dialogViewcanot.findViewById(R.id.radioSemainecanot);
                RadioButton radioFinDeSemainecanot = dialogViewcanot.findViewById(R.id.radioFinDeSemainecanotcanot);
                Button buttonAjoutercanot = dialogViewcanot.findViewById(R.id.buttonAjoutercanot);
                buttonAjoutercanot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double prix_canot = 0.0;

                        if (radioSemainecanot.isChecked()) {
                            prix_canot = 22 * counter2;
                        } else if (radioFinDeSemainecanot.isChecked()) {
                            prix_canot = 29.55 * counter2;
                        }

                        Sejour premierSejour2 = reservationItemArrayList.get(0);
                        // Mettre à jour le champ prixcanot
                        premierSejour2.setprixCanot(prix_canot);
                        reservationAdapter.notifyDataSetChanged();
                        reservationAdapter.add(premierSejour2);

                        // Afficher le prix dans un Toast ou un autre élément visuel
                        String message = "Ce montant : " + prix_canot + " sera ajouter a votre facture final";
                        Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog1 = builder1.create();

                alertDialog1.show();

                return true;

//  "Escalade"
            case Menu.FIRST + 2:

                LayoutInflater inflateresca = getLayoutInflater();
                View dialogViewesca = inflateresca.inflate(R.layout.escalade_layout_menu, null);
                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity2.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.query(DatabaseHelper.TB_ESCALADE, null, null, null, null, null, null);

                StringBuilder message2 = new StringBuilder();

                if (cursor != null && cursor.moveToFirst()) {
                    int nbreHeure = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.ESCALADE_NBRE_H));
                    double prix = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.ESCALADE_PRIX));

                    message2.append("\n\n\t\t\t\t\t\t\t\t\t\tHeures\t\t\t\t\t\t\t\t\tPrix\n");
                    message2.append("\t\t\t\t\t\t\t\t\t\t")
                            .append(nbreHeure).append("\t\t\t\t\t\t\t\t\t\t\t\t\t")
                            .append(prix).append("\n");

                    cursor.close();
                } else {
                    message2.append("Aucune donnée d'escalade disponible.");
                }

                // Affichage des données dans une AlertDialog
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity2.this);
                builder2.setTitle("Table Escalade");
                builder2.setMessage(message2.toString());

                builder2.setView(dialogViewesca);
                builder2.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                Button buttonPlusp = dialogViewesca.findViewById(R.id.buttonPlusp);
                Button buttonMinusp = dialogViewesca.findViewById(R.id.buttonMinusp);
                TextView textViewCounterp = dialogViewesca.findViewById(R.id.textViewCounterp);

                Button buttonPlush = dialogViewesca.findViewById(R.id.buttonPlush);
                Button buttonMinush = dialogViewesca.findViewById(R.id.buttonMinush);
                TextView textViewCounterh = dialogViewesca.findViewById(R.id.textViewCounterh);

                Button buttonAjouterces = dialogViewesca.findViewById(R.id.buttonAjouteresc);


                buttonPlusp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counterb++;
                        textViewCounterp.setText(String.valueOf(counterb));
                    }
                });

                buttonMinusp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(counterb>0) {
                            counterb--;
                            textViewCounterp.setText(String.valueOf(counterb));
                        }
                        else{
                            String messagg= "Invalid " ;
                            Toast.makeText(MainActivity2.this, messagg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                // bouton pour heure ajouter  +/-
                buttonPlush.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countera++;
                        textViewCounterh.setText(String.valueOf(countera));
                    }
                });

                buttonMinush.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(countera>0){
                        countera--;
                        textViewCounterh.setText(String.valueOf(countera));
                    }
                    else{
                            String messa= "Invalid " ;
                            Toast.makeText(MainActivity2.this, messa, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                buttonAjouterces.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            prix_esca = 10 * counterb * countera;

                        Sejour premierSejour3 = reservationItemArrayList.get(0);
                        premierSejour3.setprixEscalade(prix_esca);
                        reservationAdapter.notifyDataSetChanged();
                        reservationAdapter.add(premierSejour3);

                        // Afficher le prix dans un Toast ou un autre élément visuel
                        String message4 = "Le prix du l escalade est : " + prix_esca;
                        Toast.makeText(MainActivity2.this, message4, Toast.LENGTH_SHORT).show();
                        String messagev = "Verifier la liste en bas de page pour Confirmer ensuite !!!" ;
                        Toast.makeText(MainActivity2.this, messagev, Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog2 = builder2.create();
                alertDialog2.show();

                return true;

            case Menu.FIRST + 3:

                showExitConfirmationDialog();// methode pour sortir avec message de confirmation d exit
                return true;

            case Menu.FIRST + 4:

                showExitConfirmationDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // methode pour sortir avec message de confirmation
    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Voulez vous quitter ?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Oui",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finishAffinity();
                    }
                });

        builder.setNegativeButton(
                "Non",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().setBackgroundDrawableResource(android.R.drawable.alert_dark_frame);
    }
}