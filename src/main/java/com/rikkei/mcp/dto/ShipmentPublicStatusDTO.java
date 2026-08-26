package com.rikkei.mcp.dto;

import java.time.LocalDate;

import com.rikkei.mcp.entity.Shipment;
import com.rikkei.mcp.entity.ShipmentStatus;

/**
 * DTO AN TOÀN (Java Record) để tra cứu trạng thái đơn hàng — áp dụng
 * <b>Least Privilege Data Exposure</b>: chỉ chứa các trường <b>tối thiểu cần thiết</b>,
 * loại bỏ 100% trường PII nhạy cảm (customerFullName, customerPhone, customerAddress,
 * customerWalletBalance).
 *
 * <p>Giảm 0% nguy cơ lộ dữ liệu cá nhân qua Prompt Injection vì dữ liệu PII
 * đơn giản là <b>không tồn tại trong DTO</b> này.</p>
 */
public record ShipmentPublicStatusDTO(
        /** Mã vận đơn công khai. */
        String trackingCode,
        /** Trạng thái đơn hàng (IN_TRANSIT / DELIVERED / DELAYED). */
        ShipmentStatus status,
        /** Vị trí hiện tại của đơn hàng. */
        String currentLocation,
        /** Ngày dự kiến giao. */
        LocalDate estimatedDeliveryDate
) {

    /** Factory ánh xạ từ JPA Entity -> DTO, chỉ copy các trường an toàn. */
    public static ShipmentPublicStatusDTO from(Shipment entity) {
        return new ShipmentPublicStatusDTO(
                entity.getTrackingCode(),
                entity.getStatus(),
                entity.getCurrentLocation(),
                entity.getEstimatedDeliveryDate()
        );
    }
}