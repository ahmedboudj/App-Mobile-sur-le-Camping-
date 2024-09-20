package com.example.campingirma;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<List<Object>> valuesSejour;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mettre le tableau sejour dans une liste pour l afficher dans notre menu
        valuesSejour = new ArrayList<>();
        valuesSejour.add(Arrays.asList("de--> 2023-05-31", "-a->   2023-06-31", 18.91 ,""));
        valuesSejour.add(Arrays.asList("de--> 2023-07-01", "-a->   2023-08-31", 23.25,""));
        valuesSejour.add(Arrays.asList("de--> 2023-09-01", "-a->   2023-10-31", 20.25,""));

        TextView textView = findViewById(R.id.textviewirma);
        animateText(textView);

        Button myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // intent pour Lancer la deuxième activité
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
               startActivity(intent);
            }
        });
    }

    // affichage et information des calendrier de sejour et les prix
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST, 0, "Info Sejour");

        return true;
    }

    private void animateText(TextView textView) {
        AnimationSet animationSet = new AnimationSet(true);

        // Animation de gauche à droite
        Animation slideRight = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        slideRight.setDuration(3000);
        animationSet.addAnimation(slideRight);

        // Animation de droite à gauche
        Animation slideLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        slideLeft.setDuration(3000);
        slideLeft.setStartOffset(3000); // Démarre après la première animation
        animationSet.addAnimation(slideLeft);

        animationSet.setRepeatCount(Animation.INFINITE); // Répétition infinie

        textView.startAnimation(animationSet);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST: // Option "sejour"
                StringBuilder messages = new StringBuilder();
                messages.append("\n\n\n Information Sejour\n\n");
                for (List<Object> sejour : valuesSejour) {
                    messages.append("\n\n");
                    for (Object sejourItem : sejour) {
                        messages.append(sejourItem.toString()).append("\n\n");
                    }
                }

                // Affichage des données dans une AlertDialog
                AlertDialog.Builder builderequi = new AlertDialog.Builder(MainActivity.this);
                builderequi.setTitle("Welcom to Camping ");
                builderequi.setMessage(messages.toString());
                builderequi.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialogequi = builderequi.create();
                alertDialogequi.show();
        }
        return true;
    }
}