package com.rikkei.mcp.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Thực thể JPA {@code shipment} — MỘT ĐƠN HÀNG VẬN CHUYỂN.
 *
 * <p>CHÚ Ý: Entity này chứa nhiều trường PII nhạy cảm.
 * TUYỆT ĐỐI KHÔNG được trả trực tiếp ra khỏi MCP Tool.
 * Luôn ánh xạ sang {@code ShipmentPublicStatusDTO} trước khi trả cho AI Agent.</p>
 */
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    private Long id;

    /** Mã vận đơn công khai — an toàn để lộ. */
    private String trackingCode;

    /** PII — Họ và tên khách hàng (KHÔNG lộ). */
    private String customerFullName;

    /** PII — Số điện thoại khách hàng (KHÔNG lộ). */
    private String customerPhone;

    /** PII — Địa chỉ nhà riêng khách hàng (KHÔNG lộ). */
    private String customerAddress;

    /** PII — Số dư ví điện tử khách hàng (KHÔNG lộ). */
    private BigDecimal customerWalletBalance;

    /** Tên nhân viên giao hàng. */
    private String shipperName;

    /** Vị trí hiện tại của đơn hàng. */
    private String currentLocation;

    /** Trạng thái đơn hàng. */
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    /** Ngày dự kiến giao. */
    private LocalDate estimatedDeliveryDate;

    // ---- getters / setters (minh họa xử lý entity) ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrackingCode() { return trackingCode; }
    public void setTrackingCode(String trackingCode) { this.trackingCode = trackingCode; }

    public String getCustomerFullName() { return customerFullName; }
    public void setCustomerFullName(String customerFullName) { this.customerFullName = customerFullName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    public BigDecimal getCustomerWalletBalance() { return customerWalletBalance; }
    public void setCustomerWalletBalance(BigDecimal customerWalletBalance) { this.customerWalletBalance = customerWalletBalance; }

    public String getShipperName() { return shipperName; }
    public void setShipperName(String shipperName) { this.shipperName = shipperName; }

    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }

    public ShipmentStatus getStatus() { return status; }
    public void setStatus(ShipmentStatus status) { this.status = status; }

    public LocalDate getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }
}