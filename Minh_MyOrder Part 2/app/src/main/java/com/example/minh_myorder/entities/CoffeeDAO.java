package com.example.minh_myorder.entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CoffeeDAO {
    @Query("SELECT * FROM coffee_table")
    public LiveData<List<Coffee>> getCoffeeOrders();

    @Query("SELECT * FROM coffee_table WHERE coffee_id = :id")
    public Coffee getCoffeeOrderById(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void coffeeInsert(Coffee coffee);

    @Delete
    public void coffeeDelete(Coffee coffee);

    @Update
    public void coffeeUpdate(Coffee coffee);
}