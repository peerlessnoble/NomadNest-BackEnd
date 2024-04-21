package com.sid.msorder.Repository;


import com.sid.msorder.Entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Long> {
    Optional<Shipping> findByOrderOrderId(Long orderId);
}
