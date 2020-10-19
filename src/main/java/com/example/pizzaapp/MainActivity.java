package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button move_to_act_2;



    public void moveToActivity2(){
        Intent intent = new Intent(this, ToppingMenu.class);
        startActivity(intent);
    };

    public static double price_calculation(int quantity, String size, int toppings){
        int number_of_toppings;
        double price_of_size = 0;
        String get_size = size;

        if(toppings > 2){
            number_of_toppings = (toppings - 2) * 2;
        }else{
            number_of_toppings = 0;
        }

        switch (get_size.toLowerCase()){
            case "small":
                price_of_size = 12.99;
                break;
            case "medium":
                price_of_size = 15.99;
                break;
            case "large":
                price_of_size = 17.99;
                break;
            case "extra large":
                price_of_size = 23.99;
                break;
        };

        double subtotal = quantity * price_of_size + number_of_toppings;
        double total = subtotal * 0.13 + subtotal;
        return total;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        move_to_act_2 = findViewById(R.id.moving_btn);
        move_to_act_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivity2();
            }
        });


        final Spinner pizza_choice = (Spinner) findViewById(R.id.pizza_spinner);

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pizza_types)
        );

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizza_choice.setAdapter(myAdapter);

        pizza_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImageView pizza_img = (ImageView) findViewById(R.id.pizza_img);
                int duration = Toast.LENGTH_LONG;
                String alert_message = String.format(myAdapter.getItem(position));

                String[] img_name = alert_message.toLowerCase().split(" ");
                String get_img_name = TextUtils.join("_", img_name);
                String pizza_name = String.format(get_img_name);
                // This points to activity.
                Toast toast = Toast.makeText(MainActivity.this, pizza_name, duration);
                toast.show();
                int img_id = getResources().getIdentifier(pizza_name, "drawable", getPackageName());
                pizza_img.setImageResource(img_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final int[] number_toppings = {0};
        Button topping_btn = (Button) findViewById(R.id.topping_btn);
        final TextView pizza_price = (TextView) findViewById(R.id.get_price);

        final ArrayList<String> picked_toppings = new ArrayList<String>();
        final String[] list_toppings;
        final boolean[] check_toppings;
        final ArrayList<Integer> selected_topping = new ArrayList<>();

        list_toppings = getResources().getStringArray(R.array.topping_types);
        check_toppings = new boolean[list_toppings.length];

        topping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertBoxShow();
            }

            public void AlertBoxShow(){
                AlertDialog.Builder topping_builder = new AlertDialog.Builder(MainActivity.this);
                topping_builder.setTitle("Toppings");
                topping_builder.setMultiChoiceItems(list_toppings, check_toppings, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        if(isChecked){
                            selected_topping.add(which);
                        }
                        else if(selected_topping.contains(which)){
                            selected_topping.remove(selected_topping.indexOf(which));
                        }
                    }
                });

                topping_builder.setCancelable(false);
                topping_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int topping_quantity = Integer.parseInt(String.valueOf(selected_topping.size()));
                        number_toppings[0] = topping_quantity;
                    }
                });

                topping_builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                topping_builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i < check_toppings.length; i++){
                            check_toppings[i] = false;
                            selected_topping.clear();
                            picked_toppings.clear();
                        }
                    }
                });

                topping_builder.create();
                topping_builder.show();
            }
        });

        final EditText pizza_quantity = (EditText) findViewById(R.id.pizza_quantity);
        Button order_btn = (Button) findViewById(R.id.order_btn);
        final RadioButton rb1 = (RadioButton) findViewById(R.id.r1);
        final RadioButton rb2 = (RadioButton) findViewById(R.id.r2);
        final RadioButton rb3 = (RadioButton) findViewById(R.id.r3);
        final RadioButton rb4 = (RadioButton) findViewById(R.id.r4);

        final String[] get_size = {""};
        final int[] get_pizza = new int[1];

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb1.isChecked()){
                    get_size[0] = rb1.getText().toString();
                }
                if(rb2.isChecked()){
                    get_size[0] = rb2.getText().toString();
                }
                if(rb3.isChecked()){
                    get_size[0] = rb3.getText().toString();
                }
                if(rb4.isChecked()){
                    get_size[0] = rb4.getText().toString();
                }

                final String finalGet_size = get_size[0];
                get_pizza[0] = Integer.parseInt(pizza_quantity.getText().toString());

                double total = price_calculation(get_pizza[0], finalGet_size, number_toppings[0]);
                pizza_price.setText(String.format("%.2f", total));
            }
        });

    }
}