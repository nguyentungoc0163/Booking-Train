package com.example.bookingtrain;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String name;
    int money;
    int result;
    String phone;
    String language, city;
    String moneyLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnCancel =  findViewById(R.id.btnCancel);
        Button btnInfo =  findViewById(R.id.btnInfo);
        final EditText editName =  findViewById(R.id.editName);
        final EditText editPhone =  findViewById(R.id.editPhone);
        final CheckBox cbLayout =  findViewById(R.id.cbLayout);
        final RadioButton rdSeat =   findViewById(R.id.rdSeat);
        final RadioButton rdBoth =  findViewById(R.id.rdBoth);



        final Spinner dropdown;
        dropdown = (Spinner) findViewById(R.id.spinLang);
        final ArrayList<String> country = new ArrayList<String>();
        country.add("USD");
        country.add("VND");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        dropdown.setAdapter(arrayAdapter);

        cbLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLayout.isChecked()) {
                    Toast.makeText(MainActivity.this, "Discount 20% for vip member", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnInfo.setOnClickListener ( new View.OnClickListener () {
            @Override


            public void onClick(View v) {

                name = "Name: " + editName.getText().toString();
                phone = "\nPhone: " + editPhone.getText().toString();
                if (rdSeat.isChecked()){
                    money = 600000;
                }else if (rdBoth.isChecked()){
                    money = 1200000;
                }

                if (cbLayout.isChecked()) {
                    result = money -(money/100*20);
                    if (dropdown.getSelectedItem().toString() == "USD") {
                        city = "US";
                        language = "en";
                        moneyLocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(result / 23300);
                    } else if (dropdown.getSelectedItem().toString() == "VND") {
                        city = "VN";
                        language = "vi";
                        moneyLocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(result);
                    }

                }else if (dropdown.getSelectedItem().toString() == "USD") {
                    city = "US";
                    language = "en";
                    moneyLocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(money/ 23300);

                } else if (dropdown.getSelectedItem().toString() == "VND") {
                    city = "VN";
                    language = "vi";
                    moneyLocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(money);
                }

                String mess = name + phone + "\nPrice: " + moneyLocal;
                display(mess);

            }
        } );


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void display(String mess){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder (MainActivity.this );
        dlgAlert.setMessage (mess);
        dlgAlert.setTitle("Booking Train Tickets Online");
        dlgAlert.setPositiveButton("OK",null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }
}