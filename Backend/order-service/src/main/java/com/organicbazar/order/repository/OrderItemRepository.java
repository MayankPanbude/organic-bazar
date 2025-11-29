package com.organicbazar.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.organicbazar.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	 @Query("SELECT DISTINCT oi.order.id FROM OrderItem oi WHERE oi.productId IN :productIds")
	 List<Long> findOrderIdsByProductIds(@Param("productIds") List<Long> productIds);
}
