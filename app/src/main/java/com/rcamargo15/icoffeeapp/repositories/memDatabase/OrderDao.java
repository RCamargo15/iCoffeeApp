package com.rcamargo15.icoffeeapp.repositories.memDatabase;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface OrderDao {

    @Insert
    void insert(OrderEntity orderEntity);
}
