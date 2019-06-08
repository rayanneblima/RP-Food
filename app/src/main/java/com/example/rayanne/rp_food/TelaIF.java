package com.example.rayanne.rp_food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rayanne on 23/05/2017.
 */
public class TelaIF extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_if);

        //botão voltar
        Button VoltarButton = (Button) findViewById(R.id.voltarButton);

        VoltarButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(TelaIF.this, Tela1App.class);

                startActivity(intent);

                finish();
            }
        });




    }
}
