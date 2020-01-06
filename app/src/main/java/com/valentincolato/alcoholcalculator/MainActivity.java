package com.valentincolato.alcoholcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Drink drink;
    private RadioButton masculino;
    EditText bebida_input;
    EditText cant_ing_input;
    EditText graduacion_input;
    EditText peso_input;
    TextView alcohol_output;
    Button submitButton,calcular,borrar;
    /////////////////////
    private ListView listview;

    private ArrayList<String> bebidas;
    private ArrayList<Drink> drinks;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bebidas = new ArrayList<String>();
        drinks = new ArrayList<Drink>();


        bebida_input = (EditText) findViewById(R.id.input_nombre);
        cant_ing_input = (EditText) findViewById(R.id.input_cant);
        graduacion_input = (EditText) findViewById(R.id.input_g);
        peso_input = (EditText) findViewById(R.id.input_peso);
        alcohol_output = (TextView) findViewById(R.id.grado_alcohol);
        submitButton = (Button) findViewById(R.id.add_drink);
        borrar = (Button) findViewById(R.id.borrar);
        calcular = (Button) findViewById(R.id.button);

        listview = (ListView)findViewById(R.id.lv1);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bebidas);
        listview.setAdapter(adapter);

        Toast toast1 = Toast.makeText(getApplicationContext(), "Los resultados de esta aplicacion son aproximados", Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER,0 ,3 );

        toast1.show();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drink = new Drink(bebida_input.getText().toString(),Double.valueOf(graduacion_input.getText().toString()),Integer.valueOf(cant_ing_input.getText().toString()));
                bebidas.add(drink.getNombre() +" "+drink.getCant_ing()+ "ml");
                drinks.add(drink);
                listview.setAdapter(adapter);





            }
        });
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.radio_masculino);
                boolean esMasculino = radioButton.isChecked();
                String p =peso_input.getText().toString();
                    Double peso = Double.valueOf(p);

                Double alcohol_p = alcohol_puro(drinks);
                Double alcohol;
                if (esMasculino) {
                    alcohol = alcohol_p / (peso * 0.7);
                } else {
                    alcohol = alcohol_p / (peso * 0.5);}




                    Toast toast1 = Toast.makeText(getApplicationContext(), "GRADO DE ALCOHOLEMIA EN " + alcohol, Toast.LENGTH_LONG);
                    toast1.setGravity(Gravity.CENTER, 0, 3);

                    toast1.show();
                    alcohol_output.setText("GRADO DE ALCOHOLEMIA EN " + alcohol);
                }



        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              drinks.clear();
              bebidas.clear();
              adapter.clear();
              listview.setAdapter(adapter);



            }
        });




    }
    private Double alcohol_puro(ArrayList<Drink> drinks) {
        Double alcohol_p = Double.valueOf(0);

        for (int i = 0; i <drinks.size(); ++i) {
            alcohol_p= ((drinks.get(i).getGraduacion()*drinks.get(i).getCant_ing()*(0.80))/100) + alcohol_p;
        }
        return alcohol_p;



    }

}
