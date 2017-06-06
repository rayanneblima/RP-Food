package com.example.rayanne.rp_food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.lang.reflect.Array;


public class Tela1App extends AppCompatActivity {

    private Spinner sp;
    private String[] RestaurantesNome = new String[]{"Restaurante Universitário - IF RP", "Restaurante João e Maria",
            "Restaurante Riopombense"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //iniciar a tela
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela1);

        //criação da toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //criação de Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, RestaurantesNome);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp = (Spinner) findViewById(R.id.spinner);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //botão Cardápio
        Button Cardapiobtn = (Button) findViewById(R.id.cardapiobtn);

        Cardapiobtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Tela1App.this, TelaIF.class);

                startActivity(intent);

                finish();
            }
        });

    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //criação de menu e add itens
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tela1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //criar tela perfil
        if (id == R.id.perfil_menu){
            return true;
        }


        //pop up
        if (id == R.id.contato_menu){
            startActivity(new Intent(Tela1App.this,PopUpTela1.class));
            return true;
        }



        if (id == R.id.sair_menu){
            Context contexto = getApplicationContext();
            LoginManager.getInstance().logOut();
            goLoginScreen();
            String texto = "Deslogado com sucesso.";
            int duracao = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(contexto, texto,duracao);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







    private void goLoginScreen() {
        Intent intent = new Intent(this, TelaLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(Tela1App view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

}
