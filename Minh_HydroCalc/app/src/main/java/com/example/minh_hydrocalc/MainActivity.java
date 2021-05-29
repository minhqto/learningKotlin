package com.example.minh_hydrocalc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private double hst = 0.13;
    private double provincial_rebate = 0.08;
    private ArrayList<Double> usages = new ArrayList<>(Arrays.asList(0.132, 0.065, 0.094));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calcButton = findViewById(R.id.calc_hydro_button);
        calcButton.setOnClickListener(btnClick);
    }

        private View.OnClickListener btnClick = new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ArrayList<String> allPeaksAsStrings = new ArrayList<>();
                ArrayList<Double> allPeaksAsDouble = new ArrayList<>();

                allPeaksAsStrings = getPeaksAsStrings();
                allPeaksAsDouble = convertStringsToDouble(allPeaksAsStrings);


                String TAG  = "TAG";
                for(int i = 0; i < allPeaksAsDouble.size(); i++){
                    Log.d(TAG, "onClick: " + String.format("%.2f", calculateCharges(allPeaksAsDouble.get(i), usages.get(i))));
                }

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
}