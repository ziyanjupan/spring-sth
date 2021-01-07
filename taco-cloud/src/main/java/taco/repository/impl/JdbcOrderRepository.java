package taco.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import taco.domain.Order;
import taco.domain.Taco;
import taco.repository.OrderRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiaguangyong
 * @ClassName: JdbcOrderRepository
 * @Description:
 * @Date: 2021/1/7 18:29
 * @Version: 1.0
 * @Modify:
 */
@Repository
public class JdbcOrderRepository implements OrderRepository {
    private SimpleJdbcInsert orderJdbcInserter;
    private SimpleJdbcInsert orderTacoJdbcInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderJdbcInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoJdbcInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        Long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }
        return order;
    }

    private void saveTacoToOrder(Taco taco, Long orderId) {
        Map<String, Object> values = new HashMap<>(2);
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoJdbcInserter.execute(values);
    }

    private Long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        long orderId = orderJdbcInserter.executeAndReturnKey(values)
                .longValue();
        return orderId;
    }
}
