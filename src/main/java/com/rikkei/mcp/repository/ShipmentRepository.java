package com.rikkei.mcp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rikkei.mcp.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Optional<Shipment> findByTrackingCode(String trackingCode);
}