package com.example.rayanne.rp_food;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Rayanne on 05/06/2017.
 */

public class Perfil extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    private ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_perfil);
        img = (ImageButton) findViewById(R.id.img);



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


        //botão salvar
        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Perfil.this, Tela1App.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), R.string.salvo_suc, Toast.LENGTH_SHORT).show();

                finish();
            }
        });


    }

    public void onImageGalleryClicked(View v){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*");

        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                Uri imageUri = data.getData();
                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image = BitmapFactory.decodeStream(inputStream);


                    img.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(this, "Erro ao abrir a imagem.", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}

