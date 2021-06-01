package com.example.minh_hydrocalc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private final double hst = 0.13;
    private final double provincial_rebate = 0.08;
    private final ArrayList<Double> usages = new ArrayList<>(Arrays.asList(0.132, 0.065, 0.094));
    ArrayList<TextView> allCharges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calcButton = findViewById(R.id.calc_hydro_button);
        calcButton.setOnClickListener(btnClick);
    }

    private void getChargeAmountViews(){
        this.allCharges.add(findViewById(R.id.on_peak_charges_amount));
        this.allCharges.add(findViewById(R.id.off_peak_charges_amount));
        this.allCharges.add(findViewById(R.id.mid_peak_charges_amount));
    }

    private View.OnClickListener btnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            ArrayList<String> allPeaksAsStrings;
            ArrayList<Double> allPeaksAsDouble;
            Double hydroConsumpCharges = 0.0;

            allPeaksAsStrings = getPeaksAsStrings();
            allPeaksAsDouble = convertStringsToDouble(allPeaksAsStrings);
            getChargeAmountViews();
            for(int i = 0; i < allPeaksAsDouble.size(); i++){
                Double amount = allPeaksAsDouble.get(i) * usages.get(i);
                hydroConsumpCharges += amount;
                allCharges.get(i).setText(String.format("$%.2f", amount));
            }

            TextView hydroConsumpChargesView = findViewById(R.id.hydro_consumption_charges);
            hydroConsumpChargesView.setText(String.format("$%.2f", hydroConsumpCharges));
            hideKeyboard();
        }
    };

    private ArrayList<String> getPeaksAsStrings(){
        ArrayList<String> peaks = new ArrayList<>();
        EditText onPeak = findViewById(R.id.on_peak);
        EditText offPeak = findViewById(R.id.off_peak);
        EditText midPeak = findViewById(R.id.mid_peak);

        String onPeakString = onPeak.getText().toString();
        String offPeakString = offPeak.getText().toString();
        String midPeakString = midPeak.getText().toString();

        peaks.add(onPeakString);
        peaks.add(offPeakString);
        peaks.add(midPeakString);

        return peaks;
    }

    private ArrayList<Double>convertStringsToDouble(ArrayList<String> strings){
        ArrayList<Double> peaksAsDoubles = new ArrayList<Double>();
        for(int i = 0; i < strings.size(); i++){
            try{
                peaksAsDoubles.add(Double.parseDouble(strings.get(i)));
            }catch(Exception e){
                Log.d("String conversion error", "convertStringsToDouble: " + e.toString());
            }
        }

        return peaksAsDoubles;
    }
    private double calculateCharges(double amountUsed, double usageType){
        return amountUsed * usageType;
    }

    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}