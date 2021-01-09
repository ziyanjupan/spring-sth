package taco.repository;

import org.springframework.data.repository.CrudRepository;
import taco.domain.Order;

public interface OrderRepository extends CrudRepository<Order,Long> {
//    Order save(Order order);
}
