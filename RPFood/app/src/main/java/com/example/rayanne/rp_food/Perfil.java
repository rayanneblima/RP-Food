package com.example.rayanne.rp_food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Rayanne on 05/06/2017.
 */

public class Perfil extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_perfil);

        //botão voltar
        Button BtnVoltar = (Button) findViewById(R.id.btnVoltar);

        BtnVoltar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Perfil.this, Tela1App.class);
                startActivity(intent);
                finish();
            }
        });


        //botão entrar
        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Perfil.this, Tela1App.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), R.string.salvo_suc, Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }

}

