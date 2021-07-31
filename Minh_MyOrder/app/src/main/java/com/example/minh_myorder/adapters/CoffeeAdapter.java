package com.example.minh_myorder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minh_myorder.databinding.RvCoffeeOrderBinding;
import com.example.minh_myorder.models.Coffee;

import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private final Context _context;
    private final ArrayList<Coffee> _coffeeOrders;

    public CoffeeAdapter(Context context, ArrayList<Coffee> coffeeOrders){
        _context = context;
        _coffeeOrders = coffeeOrders;

    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoffeeViewHolder(RvCoffeeOrderBinding.inflate(LayoutInflater.from(_context)), _context);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeAdapter.CoffeeViewHolder holder, int position) {
        final Coffee currentCoffee = this._coffeeOrders.get(position);
        holder.bind(_context, currentCoffee);
    }

    @Override
    public int getItemCount() {
        return this._coffeeOrders.size();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder{
        RvCoffeeOrderBinding binding;

        public CoffeeViewHolder(RvCoffeeOrderBinding b, Context context){
            super(b.getRoot());
            this.binding = b;
        }

        public void bind(Context context, final Coffee coffee){
            //used to bind the actual data to the coffee view holder item
            binding.tvCoffeeType.setText(coffee.getType());
            if(!coffee.getStyle().isEmpty()){
                binding.tvCoffeeStyle.setText("Style: " + coffee.getStyle());
            }else{
                binding.tvCoffeeStyle.setVisibility(View.GONE);
            }
            binding.tvCoffeeQuantity.setText("Order Quantity: " + coffee.getQuantity());
            binding.tvCoffeeSize.setText("Coffee size: " + coffee.getSize());
        }
    }
}
