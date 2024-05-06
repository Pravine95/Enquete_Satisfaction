package com.example.myiris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Page1 extends AppCompatActivity implements View.OnClickListener{

    private Button btSuivant;
    private RadioGroup r01, r02;
    private String choix, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.btSuivant =(Button) findViewById(R.id.idSuivant);
        this.r01 =(RadioGroup) findViewById(R.id.idQ1);
        this.r02 =(RadioGroup) findViewById(R.id.idQ2);

        this.btSuivant.setOnClickListener(this);
        this.choix = this.getIntent().getStringExtra("choix").toString();
        this.email = this.getIntent().getStringExtra("email").toString();
    }

    @Override
    public void onClick(View v) {
        int score = 0;
        if (v.getId() == R.id.idSuivant){
            if(this.r01.getCheckedRadioButtonId()==R.id.idQ11){
                score = 16;
            }else if(this.r01.getCheckedRadioButtonId()==R.id.idQ12){
                score = 14;
            }else {
                score = 8;
            }
            //
            IRIS.getEnquete(this.choix).getEtudiant(this.email).ajouterReponse("avis", score);

            if(this.r02.getCheckedRadioButtonId()==R.id.idQ21){
                score = 16;
            }else if(this.r02.getCheckedRadioButtonId()==R.id.idQ22){
                score = 14;
            }else {
                score = 8;
            }

            IRIS.getEnquete(this.choix).getEtudiant(this.email).ajouterReponse("efforts", score);


            Intent unIntent = new Intent (this, Page2.class);
            unIntent.putExtra("choix", this.choix);
            unIntent.putExtra("email", this.choix);
            this.startActivity(unIntent);
        }
    }
}