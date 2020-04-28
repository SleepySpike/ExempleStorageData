package com.example.exemplestoragedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exemplestoragedata.entities.Adherent;
import com.example.exemplestoragedata.entities.Adherents;
import com.example.exemplestoragedata.utilities.Constants;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Context context;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        gson = new Gson();
        String nomFichier = Constants.NOM_FICHIER;

        Button btnSaveInternalStorage = findViewById(R.id.btnSaveInternalStorage);

        btnSaveInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Première Solution
                Adherent adherent = new Adherent();
                adherent.setNom("Toto");
                adherent.setNom("Titi");

                //2e solution
                Adherent adherent1 = new Adherent("Henry", "Claude");

                //Liste adherents
                Adherents adherents = new Adherents();
                adherents.add(adherent);
                adherents.add(adherent1);

                String data = gson.toJson(adherent);
                FileOutputStream fosData = null;

                try {
                    //On ouvre un fichier pour écrire à l'intérieur
                    fosData = openFileOutput(Constants.NOM_FICHIER,MODE_PRIVATE);
                    //On écrit nos données
                    fosData.write(data.getBytes());
                }
                catch (FileNotFoundException e) {
                   Log.e("erreur data",e.getMessage());
                }
                catch (IOException e) {
                    Log.e("erreur data",e.getMessage());
                }
                finally {

                    try {
                        //On ferme le fichier
                        fosData.close();
                    } catch (IOException e) {
                        Log.e("erreur data",e.getMessage());
                    }
                }
            }
        });

        Button btnOpenInternalStorage = findViewById(R.id.btnOpenInternalStorage);

        btnOpenInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                FileInputStream fisData = null;

                StringBuilder sb = new StringBuilder();
                String line;

                try {
                    //On ouvre notre fichier
                    FileInputStream fis = openFileInput(Constants.NOM_FICHIER);
                    //On utilise un streamReader (flux)
                    InputStreamReader inputStreamReader = new InputStreamReader(fis);
                    //Mémoire Tampon
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    //On lit le buffer (Mémoire Tampon)
                    while ((line = bufferedReader.readLine()) != null) {
                        //On récupère nos informations que l'on stock dans un stringBuilder (qui construit une chaine de caractères)
                        sb.append(line);
                    }
                    Log.e("data", sb.toString());
                    //On ferme notre fichier
                    inputStreamReader.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String adherentJson = sb.toString();
                try
                {
                    Adherent adherent = gson.fromJson(adherentJson, Adherent.class);

                    Toast.makeText(context, "Bonjour" + adherent.getNom(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
