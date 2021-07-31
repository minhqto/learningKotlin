package com.example.minh_myorder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minh_myorder.databinding.RvCoffeeOrderBinding;
import com.example.minh_myorder.models.CoffeeItem;

import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private final Context _context;
    private final ArrayList<CoffeeItem> _coffeeItemOrders;
    private final OnCoffeeItemClickListener _itemClickListener;

    public CoffeeAdapter(Context context, ArrayList<CoffeeItem> coffeeItemOrders, OnCoffeeItemClickListener itemClickListener){
        _context = context;
        _coffeeItemOrders = coffeeItemOrders;
        _itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoffeeViewHolder(RvCoffeeOrderBinding.inflate(LayoutInflater.from(_context)), _context);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeAdapter.CoffeeViewHolder holder, int position) {
        final CoffeeItem currentCoffeeItem = this._coffeeItemOrders.get(position);
        holder.bind(_context, currentCoffeeItem, _itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this._coffeeItemOrders.size();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder{
        RvCoffeeOrderBinding binding;

        public CoffeeViewHolder(RvCoffeeOrderBinding b, Context context){
            super(b.getRoot());
            this.binding = b;
        }

        public void bind(Context context, final CoffeeItem coffeeItem, final OnCoffeeItemClickListener itemClickListener){
            //used to bind the actual data to the coffee view holder item
            binding.tvCoffeeType.setText(coffeeItem.getType());
            if(!coffeeItem.getStyle().isEmpty()){
                binding.tvCoffeeStyle.setText("Style: " + coffeeItem.getStyle());
            }else{
                binding.tvCoffeeStyle.setVisibility(View.GONE);
            }
            binding.tvCoffeeQuantity.setText("Order Quantity: " + coffeeItem.getQuantity());
            binding.tvCoffeeSize.setText("Coffee size: " + coffeeItem.getSize());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onCoffeeItemClicked(coffeeItem);
                    binding.tvCoffeeQuantity.setText("Order Quantity: " + coffeeItem.getQuantity());
                }
            });
        }
    }
}
