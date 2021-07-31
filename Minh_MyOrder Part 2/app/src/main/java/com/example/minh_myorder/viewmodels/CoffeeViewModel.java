package com.example.minh_myorder.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.minh_myorder.entities.AppDB;
import com.example.minh_myorder.entities.Coffee;
import com.example.minh_myorder.repositories.CoffeeRepository;

import java.util.List;

public class CoffeeViewModel extends AndroidViewModel {
    private CoffeeRepository coffeeRepo;
    private LiveData<List<Coffee>> allCoffees;

    public CoffeeViewModel(Application app){
        super(app);
        this.coffeeRepo = new CoffeeRepository(app);
        this.allCoffees = this.coffeeRepo.getAllCoffees();
    }

    public LiveData<List<Coffee>> getAllCoffees(){
        return this.allCoffees;
    }

    public Coffee getCoffeeById(long id){
        return this.coffeeRepo.getCoffeeById(id);
    }

    public void addCoffee(Coffee coffee){
        AppDB.dbWriteExecutor.execute(() -> {
            this.coffeeRepo.addCoffee(coffee);
        });
    }

    public void updateCoffee(Coffee coffee){
        AppDB.dbWriteExecutor.execute(() -> {
            this.coffeeRepo.updateCoffee(coffee);
        });
    }

    public void deleteCoffee(Coffee coffee){
        AppDB.dbWriteExecutor.execute(() -> {
            this.coffeeRepo.deleteCoffee(coffee);
        });
    }

}
