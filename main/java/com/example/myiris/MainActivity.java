package com.example.myiris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgProfs, imgProg, imgServ ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.imgProfs= (ImageButton) findViewById(R.id.idProfesseur);
        this.imgProg= (ImageButton) findViewById(R.id.idPedagogie);
        this.imgServ= (ImageButton) findViewById(R.id.idService);

        this.imgServ.setOnClickListener(this);
        this.imgProg.setOnClickListener(this);
        this.imgProfs.setOnClickListener(this);

        //initialisation des enquetes de IRIS
        IRIS.initialiser();
    }

    @Override
    public void onClick(View v) {
        String choix = "";
        if (v.getId() == R.id.idPedagogie){
            choix = "Pedagogie";
        } else if (v.getId() == R.id.idService){
            choix = "Services";
        } else if (v.getId() == R.id.idProfesseur){
            choix = "Professeurs";
        }

        Intent unIntent = new Intent(this, Inscription.class);
        unIntent.putExtra("choix", choix);
        this.startActivity(unIntent);
    }
}