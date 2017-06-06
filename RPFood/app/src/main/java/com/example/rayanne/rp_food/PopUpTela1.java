package com.example.rayanne.rp_food;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rayanne on 04/06/2017.
 */
public class PopUpTela1 extends Activity {

    private TextView txtCel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sobre_app);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        //Facebook
        Button btnFb = (Button) findViewById(R.id.btnFb);
        btnFb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent facebookIntent = getOpenFacebookIntent(PopUpTela1.this);
                startActivity(facebookIntent);
            }
        });

        //Gmail
        Button btnGmail = (Button) findViewById(R.id.btnGmail);
        btnGmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gmailIntent = getOpenGmailIntent(PopUpTela1.this);
                startActivity(gmailIntent);
            }
        });


        //Pegar o telefone e abrir tela preenchida
        Button btnWpp = (Button) findViewById(R.id.btnCel);
        txtCel = (TextView) findViewById(R.id.txtCel);
        btnWpp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String telefone = txtCel.getText().toString();
                Uri uri = Uri.parse("tel:"+telefone);

                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

    }




    //Abrir Facebook
    public static Intent getOpenFacebookIntent(PopUpTela1 context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checa se o facebook está instalado.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/rayanne.b.lima22")); //Interação com a uri do fb.
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/rayanne.b.lima22")); //Página desejada.
        }
    }

    //Abrir Gmail
    public static Intent getOpenGmailIntent(PopUpTela1 context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.apps.plus", 0); //Checa se o facebook está instalado.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://plus.google.com/u/0/+RayanneLima2208")); //Interação com a uri do fb.
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://plus.google.com/u/0/+RayanneLima2208")); //Página desejada.
        }
    }




}
