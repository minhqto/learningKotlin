package com.example.minh_myorder.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.minh_myorder.entities.AppDB;
import com.example.minh_myorder.entities.Coffee;
import com.example.minh_myorder.entities.CoffeeDAO;

import java.util.List;

//acts as the API between view model and data layer
public class CoffeeRepository {

    private AppDB appDb;
    private CoffeeDAO coffeeDAO;
    private LiveData<List<Coffee>> allCoffees;

    public CoffeeRepository(Application app){
        this.appDb = AppDB.getInstance(app.getApplicationContext());
        this.coffeeDAO = this.appDb.coffeeDAO();
        this.allCoffees = this.coffeeDAO.getCoffeeOrders();
    }

    public LiveData<List<Coffee>> getAllCoffees(){
        return this.allCoffees;
    }

    public Coffee getCoffeeById(long id){
        return this.coffeeDAO.getCoffeeOrderById(id);
    }

    public void addCoffee(Coffee coffee){
        AppDB.dbWriteExecutor.execute(() -> {
            this.coffeeDAO.coffeeInsert(coffee);
        }) ;
    }

    public void updateCoffee(Coffee coffee){
        AppDB.dbWriteExecutor.execute(() -> {
            this.coffeeDAO.coffeeUpdate(coffee);
        });

    }

    public void deleteCoffee(Coffee coffee){
        AppDB.dbWriteExecutor.execute(() -> {
            this.coffeeDAO.coffeeDelete(coffee);
        });

    }
}
