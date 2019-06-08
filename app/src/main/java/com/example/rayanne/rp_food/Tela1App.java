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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.facebook.login.LoginManager;

import com.example.rayanne.rp_food.R;
import com.example.rayanne.rp_food.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.util.Log;

import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class Tela1App extends AppCompatActivity {

    // URL JSON ARRAY
    private String urlJsonArry = "https://riopombafood.000webhostapp.com/select.php";

    private static String TAG = Tela1App.class.getSimpleName();
    private Button btnMakeArrayRequest;

    // Dialog
    private ProgressDialog pDialog;
    private TextView txtResponse;

    // String temporária
    private String jsonResponse;


    RatingBar ratingBar;
    private Spinner sp;
    private String[] RestaurantesNome = new String[]{"Restaurante Universitário - IF RP"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //iniciar a tela
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela1);

        //JSON
        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);

        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //json array request
                makeJsonArrayRequest();
            }
        });

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


        //rating bar
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Intent intent = new Intent();
                intent.setClass(Tela1App.this, Opiniao.class);
                startActivity(intent);
                finish();
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
            startActivity(new Intent(Tela1App.this, Perfil.class));
            return true;
        }

        //pop up
        if (id == R.id.contato_menu){
            startActivity(new Intent(Tela1App.this,PopUpTela1.class));
            return true;
        }



        if (id == R.id.sair_menu){
            LoginManager.getInstance().logOut();
            goLoginScreen();
            Context contexto = getApplicationContext();
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


    /**
     * JSON ARRAY [
     * */
    private void makeJsonArrayRequest() {
        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String salada1 = person.getString("salada1");
                                String salada2 = person.getString("salada2");
                                String prato_principal = person.getString("prato_principal");
                                String guarnicao = person.getString("guarnicao");
                                String suco = person.getString("suco");
                                String sobremesa = person.getString("sobremesa");
                                String data = person.getString("data");

                                jsonResponse +=  data + "\n\n";
                                jsonResponse += "Salada 1: " + salada1 + "\n\n";
                                jsonResponse += "\t  2: " + salada2 + "\n\n";
                                jsonResponse += "Prato Principal: " + prato_principal + "\n\n";
                                jsonResponse += "Guarnição: " + guarnicao + "\n\n";
                                jsonResponse += "Suco: " + suco + "\n\n";
                                jsonResponse += "Sobremesa: " + sobremesa + "\n\n";

                            }

                            txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Erro: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Erro: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adiciona request ao request queue
        AppController.getInstance().addToRequestQueue(req);

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
