package com.sid.msorder.Repository;

import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}