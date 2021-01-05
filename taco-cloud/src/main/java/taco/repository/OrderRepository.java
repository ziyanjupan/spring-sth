package taco.repository;

import taco.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
