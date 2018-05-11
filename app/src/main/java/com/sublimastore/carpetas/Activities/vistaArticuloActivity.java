package com.sublimastore.carpetas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anny.ejemploorm.R;
import com.sublimastore.carpetas.model.Articulos;

public class vistaArticuloActivity extends AppCompatActivity {
    EditText txtNombre, txtDesc, txtPrecio, txtCantidad;
    Spinner spTipo;
    Button btnModificar;
    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_articulo);
        txtNombre = findViewById(R.id.txtVerNombre);
        txtDesc = findViewById(R.id.txtVerDesc);
        txtPrecio = findViewById(R.id.txtVerPrecio);
        txtCantidad = findViewById(R.id.txtVerCantidad);
        spTipo = findViewById(R.id.spVerTipo);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            id = extras.getLong("id");
            Articulos articulo = Articulos.findById(Articulos.class,id);
            txtNombre.setText(articulo.getNombre());
            txtDesc.setText(articulo.getDescripcion());
            txtPrecio.setText(String.valueOf(articulo.getPrecio()));
            txtCantidad.setText(String.valueOf(articulo.getCantidad()));
            for (int i = 0; i<spTipo.getAdapter().getCount(); i++){
                if(spTipo.getAdapter().getItem(i).toString().equals(articulo.getTipo())){
                    spTipo.setSelection(i);
                }
            }
        }

        /*btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                modificar(id,nombre,tipo,precio,cant,desc);
            }
        });*/
    }

    public void borrar(View view){
        Articulos art = Articulos.findById(Articulos.class,id);
        art.delete();
        Toast.makeText(this,"Articulo eliminado", Toast.LENGTH_LONG).show();
        Intent i = new Intent(vistaArticuloActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void modificar(View view) {
        String nombre = txtNombre.getText().toString();
        String desc = txtDesc.getText().toString();
        double precio = Double.parseDouble(txtPrecio.getText().toString());
        int cant = Integer.parseInt(txtCantidad.getText().toString());
        String tipo = spTipo.getSelectedItem().toString();

        Articulos art = Articulos.findById(Articulos.class, id);
        art.setNombre(nombre);
        art.setDescripcion(desc);
        art.setCantidad(cant);
        art.setPrecio(precio);
        art.setTipo(tipo);
        art.save();
        Toast.makeText(this,"Articulo modificado", Toast.LENGTH_LONG).show();

        Intent i = new Intent(vistaArticuloActivity.this, MainActivity.class);
        startActivity(i);
    }
}
