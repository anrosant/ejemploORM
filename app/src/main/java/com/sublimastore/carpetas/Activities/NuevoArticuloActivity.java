package com.sublimastore.carpetas.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anny.ejemploorm.R;
import com.sublimastore.carpetas.model.Articulos;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.BitSet;

public class NuevoArticuloActivity extends AppCompatActivity {
    EditText txtNombre, txtDesc, txtPrecio, txtCant;
    ImageView ivImagen;
    Spinner sp_tipo;
    String tipo;
    final int REQUEST_CODE_GALLERY=999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_articulo);
        txtNombre = findViewById(R.id.txtNombre);
        txtDesc = findViewById(R.id.txtDesc);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCant = findViewById(R.id.txtCantidad);
        ivImagen = findViewById(R.id.ivImagen);
        sp_tipo = findViewById(R.id.spTipo);
        sp_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void crear(View view){
        String nombre= txtNombre.getText().toString();
        String descripcion = txtDesc.getText().toString();
        double precio = Double.parseDouble(txtPrecio.getText().toString());
        int cantidad = Integer.parseInt(txtCant.getText().toString());
        String dirImg = "cambiar.jpg";
        try{
            Articulos nuevoArticulo = new Articulos(nombre,tipo,precio,cantidad,descripcion, dirImg);
            nuevoArticulo.save();
            Toast.makeText(this, "Artículo creado",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("nombre",nombre);
        startActivity(intent);
    }

    public void cargarImagen(View view){
        ActivityCompat.requestPermissions(NuevoArticuloActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }

            else{
                Toast.makeText(getApplicationContext(),"No tiene permisos",Toast.LENGTH_LONG).show();
            }
            return;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data!=null){
            Uri uri = data.getData();

            try {
                assert uri != null;
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivImagen.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
