package kr.ac.kopo.kihwan.bookmarket.repository;


import kr.ac.kopo.kihwan.bookmarket.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private Map<Long, Order> listOfOrders;
    private long nextOrderId;

    public OrderRepositoryImpl() {
        listOfOrders = new HashMap<>();
        nextOrderId = 2000;
    }

    @Override
    public long saveOrder(Order order) {
        order.setOrderId(nextOrderId);
        listOfOrders.put(order.getOrderId(), order);
        return order.getOrderId();
    }

    private synchronized long getNextOrderId(){
        return nextOrderId++;
    }
}
