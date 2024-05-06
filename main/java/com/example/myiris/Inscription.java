package com.example.myiris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Inscription extends AppCompatActivity implements View.OnClickListener{

    private EditText txtNom, txtPrenom, txtEmail;
    private Spinner sPromotion;
    private RadioGroup rgEtude;
    private Button btParticiper;
    private String choix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inscription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.btParticiper = (Button) findViewById(R.id.idParticiper);
        this.txtNom = (EditText) findViewById(R.id.idNom);
        this.txtPrenom = (EditText) findViewById(R.id.idPrenom);
        this.txtEmail = (EditText) findViewById(R.id.idEmail);
        this.sPromotion = (Spinner) findViewById(R.id.idPromotion);
        this.rgEtude = (RadioGroup) findViewById(R.id.idEtude);

        this.btParticiper.setOnClickListener(this);

        //remplir le spinner Promotion : BTS, Bachelor, Master.
        ArrayList<String> lesDiplomes = new ArrayList<>();
        lesDiplomes.add("BTS");
        lesDiplomes.add("Bachelor");
        lesDiplomes.add("Master");
        ArrayAdapter unAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesDiplomes);
        this.sPromotion.setAdapter(unAdapter);

        //on recupere le choix de l'enquete
        this.choix = this.getIntent().getStringExtra("choix").toString();
    }

    @Override
    public void onClick(View v) {
        String nom, prenom, email = "", promotion, statut;
        if (v.getId() == R.id.idParticiper){

            nom = this.txtNom.getText().toString();
            prenom = this.txtPrenom.getText().toString();
            prenom = this.txtEmail.getText().toString();
            promotion = this.sPromotion.getSelectedItem().toString();
            if(this.rgEtude.getCheckedRadioButtonId() == R.id.idAlternant){
                statut = "Alternant";
            }else{
                statut = "Initial";
            }
            //Instanciation d'un étudiant
            Etudiant unEtudiant = new Etudiant(nom, prenom, email, promotion, statut);
            //Ajouter l'etudiant dans l'enquete choisie
            IRIS.getEnquete(this.choix).ajouterEtudiant(unEtudiant);

            Toast.makeText(this, "Bienvenue dans l'enquete : " +this.choix, Toast.LENGTH_LONG).show();

            Intent unIntent = new Intent (this, Page1.class);
            //passage du choix de l'enquete et de l'email de l'étudiant comme $_SESSION
            unIntent.putExtra("choix", this.choix);
            unIntent.putExtra("email", email);
            this.startActivity(unIntent);
        }
    }
}