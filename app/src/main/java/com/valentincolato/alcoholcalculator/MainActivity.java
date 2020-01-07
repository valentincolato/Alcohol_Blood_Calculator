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
    EditText time_input;
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
        time_input = (EditText) findViewById(R.id.input_time);
        alcohol_output = (TextView) findViewById(R.id.grado_alcohol);
        submitButton = (Button) findViewById(R.id.add_drink);
        borrar = (Button) findViewById(R.id.borrar);
        calcular = (Button) findViewById(R.id.button);

        listview = (ListView)findViewById(R.id.lv1);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bebidas);
        listview.setAdapter(adapter);

        Toast toast1 = Toast.makeText(getApplicationContext(), "The results of this application are approximate", Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER,0 ,3 );

        toast1.show();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = bebida_input.getText().toString();
                String graduacion = graduacion_input.getText().toString();
                String cant = cant_ing_input.getText().toString();
                if(nombre.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes de ingresar un nombre", Toast.LENGTH_LONG).show();
                }
                if(graduacion.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes de ingresar una graduacion", Toast.LENGTH_LONG).show();
                }
                if(cant.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes de ingresar una cantidad", Toast.LENGTH_LONG).show();
                }

                if(nombre.length() != 0 && graduacion.length() != 0 && cant.length() != 0){
                    Toast.makeText(getApplicationContext(), "drink added successfully...", Toast.LENGTH_LONG).show();
                    drink = new Drink(nombre,Double.valueOf(graduacion),Integer.valueOf(cant));
                    bebidas.add(drink.getNombre() +" "+drink.getCant_ing()+ "ml");
                    drinks.add(drink);
                    listview.setAdapter(adapter);
                }






            }
        });
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.radio_masculino);
                boolean esMasculino = radioButton.isChecked();
                String p =peso_input.getText().toString();
                String t = time_input.getText().toString();
                if(p.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes de ingresar un peso", Toast.LENGTH_LONG).show();
                }
                if(t.length() == 0){
                    Toast.makeText(getApplicationContext(), "Debes de ingresar un tiempo", Toast.LENGTH_LONG).show();
                }
                if(p.length() != 0 && t.length() != 0){
                    Toast.makeText(getApplicationContext(), "Calculo en proceso...", Toast.LENGTH_LONG).show();
                    Double peso = Double.valueOf(p);

                    Double alcohol_p = alcohol_puro(drinks);
                    Double alcohol;
                    if (esMasculino) {
                        alcohol = alcohol_p / (peso * 0.73);
                    } else {
                        alcohol = alcohol_p / (peso * 0.66);}

                    alcohol = alcohol - (0.15 * Double.valueOf(time_input.getText().toString()));
                    if(alcohol<0){
                        alcohol= Double.valueOf(0);
                    }


                    Toast toast1 = Toast.makeText(getApplicationContext(), "blood alcohol concentration: " + alcohol, Toast.LENGTH_LONG);
                    toast1.setGravity(Gravity.CENTER, 0, 3);

                    toast1.show();
                    alcohol_output.setText("blood alcohol concentration: " + alcohol);
                }


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
