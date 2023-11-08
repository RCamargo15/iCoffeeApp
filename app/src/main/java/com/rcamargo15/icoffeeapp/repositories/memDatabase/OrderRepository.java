package com.rcamargo15.icoffeeapp.repositories.memDatabase;

import android.content.Context;
import androidx.room.Room;
import com.rcamargo15.icoffeeapp.models.Order;
import java.time.format.DateTimeFormatter;

public class OrderRepository  {

    public void saveOrderIntoMemDatabase(Order clientOrder, final Context context){
        OrderDatabase orderDatabase = Room.databaseBuilder(
                        context.getApplicationContext(),
                        OrderDatabase.class, "orders")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        OrderDao orderDao = orderDatabase.orderDao();
            OrderEntity entity = new OrderEntity(clientOrder.getOrderNumber(),
                    clientOrder.getClientName(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy").format(clientOrder.getOrderDate()),
                    clientOrder.getTotalOrderPrice(),
                    clientOrder.getPaymentType().getPaymentName());

            orderDao.insert(entity);
    }
}
