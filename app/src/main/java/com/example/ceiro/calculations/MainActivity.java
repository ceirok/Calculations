package com.example.ceiro.calculations;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText unit_price;
    private EditText no_units;
    private RadioButton ex_vat;
    private RadioButton inc_vat;
    private TextView wo_vat;
    private TextView vat;
    private TextView w_vat;
    private Button clrBtn;
    private Button okBtn;

    private static final double km = 0.2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unit_price = findViewById(R.id.unit_price);
        no_units = findViewById(R.id.no_units);
        ex_vat = findViewById(R.id.ex_vat);
        inc_vat = findViewById(R.id.inc_vat);
        wo_vat = findViewById(R.id.wo_vat);
        vat = findViewById(R.id.vat);
        w_vat = findViewById(R.id.w_vat);
        clrBtn = findViewById(R.id.clrBtn);
        okBtn = findViewById(R.id.okBtn);

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit_price.setText("");
                no_units.setText("");

                wo_vat.setText("");
                vat.setText("");
                w_vat.setText("");
            }
        });
    }

    public String setVatAmount(){
        double dblPrice = Double.parseDouble(unit_price.getText().toString());
        double dblUnit = Double.parseDouble(no_units.getText().toString());
        if(ex_vat.isChecked()){
            return String.valueOf(dblPrice * dblUnit);
        } else {
            return String.valueOf((dblPrice - (dblPrice * km)) * dblUnit);
        }
    }
    public String setVat(){
        double dblPrice = Double.parseDouble(unit_price.getText().toString());
        double dblUnit = Double.parseDouble(no_units.getText().toString());
        if(ex_vat.isChecked()){
            return String.valueOf(dblPrice * dblUnit * km);
        } else {
            return String.valueOf((dblPrice * dblUnit) * km);
        }
    }
    public String setIncVatAmount(){
        double dblPrice = Double.parseDouble(unit_price.getText().toString());
        double dblUnit = Double.parseDouble(no_units.getText().toString());
        if(ex_vat.isChecked()){
            return String.valueOf((dblPrice * dblUnit) * km + (dblPrice * dblUnit));
        } else {
            return String.valueOf(dblPrice  * dblUnit);
        }
    }

    public void onClick(View view){
        if(unit_price.getText().length() > 0 && no_units.getText().length() > 0){
            if(view.getId() == R.id.okBtn){
                wo_vat.setText(setVatAmount());
                vat.setText(setVat());
                w_vat.setText(setIncVatAmount());
            }
        } else {
            Toast.makeText(this, "Please fill in both inputs", Toast.LENGTH_SHORT).show();
        }
    }
}
