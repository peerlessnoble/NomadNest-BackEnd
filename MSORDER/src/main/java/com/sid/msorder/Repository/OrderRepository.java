package com.sid.msorder.Repository;


import com.sid.msorder.Dtos.OrderResponseDto;
import com.sid.msorder.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
