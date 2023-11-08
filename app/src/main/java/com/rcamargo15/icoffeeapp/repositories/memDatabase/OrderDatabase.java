package com.rcamargo15.icoffeeapp.repositories.memDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {OrderEntity.class}, version = 2)
public abstract class OrderDatabase extends RoomDatabase {

    public abstract OrderDao orderDao();
}
