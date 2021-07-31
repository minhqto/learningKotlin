package com.example.minh_myorder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minh_myorder.adapters.CoffeeAdapter;
import com.example.minh_myorder.adapters.OnCoffeeItemClickListener;
import com.example.minh_myorder.databinding.ActivityCoffeeOrdersBinding;
import com.example.minh_myorder.entities.Coffee;
import com.example.minh_myorder.models.CoffeeItem;
import com.example.minh_myorder.viewmodels.CoffeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrdersActivity extends AppCompatActivity implements OnCoffeeItemClickListener {
    //this activity receives the orders from MainActivity
    private ArrayList<CoffeeItem> coffeeOrders = new ArrayList<>();
    private ActivityCoffeeOrdersBinding binding;
    private CoffeeAdapter coffeeAdapter;
    private CoffeeViewModel coffeeVM;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCoffeeOrdersBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.helper = new ItemTouchHelper(itemTouchCallback);
        this.helper.attachToRecyclerView(this.binding.rvCoffeeOrders);
        this.coffeeVM = new ViewModelProvider(this).get(CoffeeViewModel.class);
        this.coffeeAdapter = new CoffeeAdapter(this, coffeeOrders, this);
        getCoffee();
    }

    private void getCoffee(){
        LiveData<List<Coffee>> allCoffees = this.coffeeVM.getAllCoffees();
        allCoffees.observe(this, new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                coffeeOrders.clear();
                for(Coffee coffee : coffees){
                    CoffeeItem tempCoffeeItem = new CoffeeItem(coffee.getCoffeeId(), coffee.getType(), coffee.getStyle(), coffee.getSize(), String.valueOf(coffee.getQuantity()));
                    coffeeOrders.add(tempCoffeeItem);
;                }
                coffeeAdapter.notifyDataSetChanged();
            }
        });

        this.binding.rvCoffeeOrders.setLayoutManager(new LinearLayoutManager(this));
        this.binding.rvCoffeeOrders.setAdapter(coffeeAdapter);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(this.binding.rvCoffeeOrders.getContext(),
                DividerItemDecoration.VERTICAL);
        this.binding.rvCoffeeOrders.addItemDecoration(mDividerItemDecoration);
    }


    @Override
    public void onCoffeeItemClicked(CoffeeItem coffeeItem) {
        Log.d("Minh", "onCoffeeItemClicked: hello");
        //convert to coffee first
        Coffee selectedCoffee = new Coffee(coffeeItem.getCoffeeId(), coffeeItem.getType(), coffeeItem.getStyle(), coffeeItem.getSize(), Integer.parseInt(coffeeItem.getQuantity()));
        AlertDialog.Builder update = new AlertDialog.Builder(this);
        update.setTitle("Update Coffee Order");
        update.setMessage("Enter in new quantity");

        final EditText inputQuantity = new EditText(this);
        update.setView(inputQuantity);

        update.setPositiveButton("Done", new DialogInterface.OnClickListener(){
            @Override
             public void onClick(DialogInterface dialog, int which){
                selectedCoffee.setQuantity(Integer.parseInt(inputQuantity.getText().toString()));
                coffeeVM.updateCoffee(selectedCoffee);
                coffeeAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Coffee order has been updated", Toast.LENGTH_LONG).show();
            }
        });

        update.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        update.show();
    }

    private void deleteCoffee(int position){
        CoffeeItem coffeeItem = coffeeOrders.get(position);
        Coffee  coffeeToBeDeleted= new Coffee(coffeeItem.getCoffeeId(), coffeeItem.getType(), coffeeItem.getStyle(), coffeeItem.getSize(), Integer.parseInt(coffeeItem.getQuantity()));
        coffeeVM.deleteCoffee(coffeeToBeDeleted);
    }

    ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if(direction == ItemTouchHelper.RIGHT){
               AlertDialog.Builder alertDialog = new AlertDialog.Builder(viewHolder.itemView.getContext());
               alertDialog.setMessage("Delete order?");
               alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       deleteCoffee(viewHolder.getAdapterPosition());
                       coffeeAdapter.notifyDataSetChanged();
                   }
               });
               alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                       coffeeAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                   }
               });
               alertDialog.show();
            }
        }
    };



}