package com.example.minh_myorder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minh_myorder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onRadioButtonClicked(View v){
        switch(v.getId()){
            case R.id.rb_espresso:
                this.binding.tvCoffeeStylePrompt.setVisibility(View.INVISIBLE);
                this.binding.rgCoffeeStyle.setVisibility(View.INVISIBLE);
                break;
            case R.id.rb_coffee:
                Log.d("Coffee App", "onRadioButtonClicked: clicked!");
                this.binding.tvCoffeeStylePrompt.setVisibility(View.VISIBLE);
                this.binding.rgCoffeeStyle.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_cappuccino:
                this.binding.tvCoffeeStylePrompt.setVisibility(View.INVISIBLE);
                this.binding.rgCoffeeStyle.setVisibility(View.INVISIBLE);
                break;
        }
    }
}