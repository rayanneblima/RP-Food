package com.example.rayanne.rp_food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Rayanne on 22/05/2017.
 */


public class PagCadastro extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagcadastro);


        //botão cadastrar
        Button ButtonCadastro = (Button) findViewById(R.id.buttonCadastro);

        ButtonCadastro.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(PagCadastro.this, Tela1App.class);

                startActivity(intent);

                Toast.makeText(getApplicationContext(), R.string.suc_cadastro, Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), R.string.bem_vindo, Toast.LENGTH_SHORT).show();

                finish();
            }
        });


        //botão entrar
        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(PagCadastro.this, TelaLogin.class);

                startActivity(intent);

                finish();
            }
        });

    }

}

