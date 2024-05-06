package com.example.myiris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Fin extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgSmiley;
    private ListView lvResultats;
    private Button btRetour;
    private String choix, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.btRetour = (Button) findViewById(R.id.idRetour);
        this.lvResultats = (ListView) findViewById(R.id.idListe);
        this.imgSmiley = (ImageView) findViewById(R.id.idSmiley);

        this.btRetour.setOnClickListener(this);
        this.choix = this.getIntent().getStringExtra("choix").toString();
        this.email = this.getIntent().getStringExtra("email").toString();
        float moy = IRIS.getEnquete(this.choix).getEtudiant(this.email).moyenne();
        if(moy < 10){
            this.imgSmiley.setImageResource(R.drawable.smiley_r);
        }else if (moy <14){
            this.imgSmiley.setImageResource(R.drawable.smiley_n);
        }else{
            this.imgSmiley.setImageResource(R.drawable.smiley_1);
        }
        ArrayList<String> lesEtudiants = new ArrayList<>();
        for(Etudiant unEtudiant : IRIS.getEnquete(this.choix).getLesEtudiants().values()){
            lesEtudiants.add(unEtudiant.getNom() + "-" +unEtudiant.getPrenom()+"-"
                    + unEtudiant.moyenne()+"-" +unEtudiant.getStatut());
        }
        ArrayAdapter unAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lesEtudiants);
        this.lvResultats.setAdapter(unAdapter);
        Toast.makeText(this, "Merci d'avoir répondu à notre enquete", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.idRetour){
            Intent unIntent = new Intent(this, MainActivity.class);

            this.startActivity(unIntent);
        }

    }
}