package com.sublimastore.carpetas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.anny.ejemploorm.R;
import com.sublimastore.carpetas.model.Articulos;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    Button btn_prueba;
    ListView lista_articulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_prueba = findViewById(R.id.btnCrear);
        lista_articulos = findViewById(R.id.listaArticulos);
        final ArrayList<String> articulos = new ArrayList<>();
        Iterator<Articulos> articulosIt = Articulos.findAll(Articulos.class);

        while (articulosIt.hasNext()){
            Articulos articulo = articulosIt.next();
            String nombre = String.valueOf(articulo.getId())+" "+articulo.getNombre();
            articulos.add(nombre);
        }
        ArrayAdapter<String> articulos_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, articulos);
        lista_articulos.setAdapter(articulos_adapter);

        lista_articulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long idArt = Long.parseLong(articulos.get(position).split(" ")[0]);
                verArticulo(idArt);
            }
        });
    }
    public void verArticulo(Long id){
        Intent intent = new Intent(this, vistaArticuloActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void llamar(View view){
        Intent i = new Intent(this, NuevoArticuloActivity.class);
        startActivity(i);
    }


}
