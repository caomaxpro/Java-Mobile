package com.example.pizzaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ToppingMenu extends AppCompatActivity {

    private Button move_to_act_1;

    public void moveToActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topping_menu);

        move_to_act_1 = findViewById(R.id.move_to_act_1);
        move_to_act_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivity1();
            }
        });

//        Button topping_btn = (Button) findViewById(R.id.topping_btn);
//        final TextView topping_selection = (TextView) findViewById(R.id.topping_selection);
//
//        final ArrayList<String> picked_toppings = new ArrayList<String>();
//        final String[] list_toppings;
//        final boolean[] check_toppings;
//        final ArrayList<Integer> selected_topping = new ArrayList<>();
//
//        list_toppings = getResources().getStringArray(R.array.topping_types);
//        check_toppings = new boolean[list_toppings.length];
//
//        topping_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertBoxShow();
//            }
//
//            public void AlertBoxShow(){
//                AlertDialog.Builder topping_builder = new AlertDialog.Builder(ToppingMenu.this);
//                topping_builder.setTitle("Toppings");
//                topping_builder.setMultiChoiceItems(list_toppings, check_toppings, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
//                        if(isChecked){
//                            selected_topping.add(which);
//                        }
//                        else if(selected_topping.contains(which)){
//                            selected_topping.remove(selected_topping.indexOf(which));
//                        }
//                    }
//                });
//
//                topping_builder.setCancelable(false);
//                topping_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String number_of_toppings = String.valueOf(selected_topping.size());
//                        topping_selection.setText(number_of_toppings);
//                    }
//                });
//
//                topping_builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                topping_builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        for(int i = 0; i < check_toppings.length; i++){
//                            check_toppings[i] = false;
//                            selected_topping.clear();
//                            picked_toppings.clear();
//                            topping_selection.setText("");
//                        }
//                    }
//                });
//
//                topping_builder.create();
//                topping_builder.show();
//            }
//        });
    }
}