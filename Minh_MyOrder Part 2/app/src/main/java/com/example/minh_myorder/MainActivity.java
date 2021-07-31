package com.example.minh_myorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.minh_myorder.databinding.ActivityMainBinding;
import com.example.minh_myorder.entities.Coffee;
import com.example.minh_myorder.viewmodels.CoffeeViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private String coffeeType;
    private String coffeeStyle = "";
    private String coffeeSize;
    private String quantity;
    private CoffeeViewModel coffeeVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.coffeeVM = new ViewModelProvider(this).get(CoffeeViewModel.class);
        this.binding.rgCoffeeStyle.setVisibility(View.GONE);
        this.binding.tvCoffeeStylePrompt.setVisibility(View.GONE);
        this.binding.btnSubmit.setOnClickListener(this);
    }

    public void onRadioButtonClicked(View v){
        switch(v.getId()){
            case R.id.rb_espresso:
                this.binding.tvCoffeeStylePrompt.setVisibility(View.INVISIBLE);
                this.binding.rgCoffeeStyle.setVisibility(View.GONE);
                this.coffeeType = this.binding.rbEspresso.getText().toString();
                break;
            case R.id.rb_coffee:
                this.binding.tvCoffeeStylePrompt.setVisibility(View.VISIBLE);
                this.binding.rgCoffeeStyle.setVisibility(View.VISIBLE);
                this.coffeeType = this.binding.rbCoffee.getText().toString();
                break;
            case R.id.rb_cappuccino:
                this.binding.tvCoffeeStylePrompt.setVisibility(View.INVISIBLE);
                this.binding.rgCoffeeStyle.setVisibility(View.GONE);
                this.coffeeType = this.binding.rbCappuccino.getText().toString();
                break;
            case R.id.rb_black:
                this.coffeeStyle = this.binding.rbBlack.getText().toString();
                break;
            case R.id.rb_regular:
                this.coffeeStyle = this.binding.rbRegular.getText().toString();
                break;
            case R.id.rb_double:
                this.coffeeStyle = this.binding.rbDouble.getText().toString();
                break;
            case R.id.rb_small:
                this.coffeeSize = this.binding.rbSmall.getText().toString();
                break;
            case R.id.rb_medium:
                this.coffeeSize = this.binding.rbMedium.getText().toString();
                break;
            case R.id.rb_large:
                this.coffeeSize = this.binding.rbLarge.getText().toString();
                break;
            case R.id.one:
                this.quantity = this.binding.one.getText().toString();
                break;
            case R.id.two:
                this.quantity = this.binding.two.getText().toString();
                break;
            case R.id.three:
                this.quantity = this.binding.three.getText().toString();
                break;
            case R.id.four:
                this.quantity = this.binding.four.getText().toString();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v != null){
            switch(v.getId()){
                case(R.id.btn_submit): {
//                    this.coffeeOrders.add(new Coffee(this.coffeeType, this.coffeeStyle, this.coffeeSize, this.quantity));
                    Coffee newCoffee = new Coffee(this.coffeeType, this.coffeeStyle, this.coffeeSize, Integer.parseInt(this.quantity));
                    this.coffeeVM.addCoffee(newCoffee);
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setTitle("Coffee added to order! Would you like to order more?");
                    alertBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    });
                    alertBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showOrders();
                        }
                    });
                    alertBuilder.show();
                    break;
                }
            }

        }
    }

    private void showOrders(){
        Intent coffeeOrdersIntent = new Intent(this, CoffeeOrdersActivity.class);
//        coffeeOrdersIntent.putParcelableArrayListExtra("coffees", coffeeOrders);
        startActivity(coffeeOrdersIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //this function creates the options menu (a type of android menu)
        //you have to inflate your menu resource (xml) not the menu object provided in this callback
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_orders:{
                this.showOrders();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}