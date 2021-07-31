package com.example.minh_myorder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.minh_myorder.adapters.CoffeeAdapter;
import com.example.minh_myorder.databinding.ActivityCoffeeOrdersBinding;
import com.example.minh_myorder.models.Coffee;

import java.util.ArrayList;

public class CoffeeOrdersActivity extends AppCompatActivity {
    //this activity receives the orders from MainActivity
    private ArrayList<Coffee> coffeeOrders = new ArrayList<>();
    private ActivityCoffeeOrdersBinding binding;
    private CoffeeAdapter coffeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCoffeeOrdersBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        getCoffeeFromParcel();
    }

    private void getCoffeeFromParcel(){
        ArrayList<Coffee> tempCoffees = this.getIntent().getParcelableArrayListExtra("coffees");
        if(tempCoffees != null){
            this.coffeeOrders = tempCoffees;
        }
        this.coffeeAdapter = new CoffeeAdapter(this, this.coffeeOrders);
        this.binding.rvCoffeeOrders.setLayoutManager(new LinearLayoutManager(this));
        this.binding.rvCoffeeOrders.setAdapter(this.coffeeAdapter);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(this.binding.rvCoffeeOrders.getContext(),
                DividerItemDecoration.VERTICAL);
        this.binding.rvCoffeeOrders.addItemDecoration(mDividerItemDecoration);
    }
}