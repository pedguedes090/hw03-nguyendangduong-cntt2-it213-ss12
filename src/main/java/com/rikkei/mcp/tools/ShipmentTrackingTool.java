package com.rikkei.mcp.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.rikkei.mcp.dto.ShipmentPublicStatusDTO;
import com.rikkei.mcp.entity.Shipment;
import com.rikkei.mcp.repository.ShipmentRepository;

/**
 * MCP Tool tra cứu trạng thái đơn hàng — BẢN VÁ BẢO MẬT.
 *
 * <p>KHỐI PHỤC LỖ HỔNG: thay vì trả về trực tiếp JPA Entity {@code Shipment}
 * (chứa toàn bộ trường PII), tool này chỉ trả về {@code ShipmentPublicStatusDTO}
 * đã được lọc sạch PII theo nguyên tắc <b>Least Privilege Data Exposure</b>.</p>
 */
@Component
public class ShipmentTrackingTool {

    private final ShipmentRepository shipmentRepository;

    public ShipmentTrackingTool(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Tool(name = "get_shipment_details",
          description = "Tra cứu trạng thái đơn hàng theo mã vận đơn. CHỈ trả về thông tin công khai, KHÔNG bao gồm thông tin cá nhân của khách hàng.")
    public ShipmentPublicStatusDTO getShipmentDetails(
            @ToolParam(description = "Mã vận đơn, ví dụ: RK-88219") String trackingCode) {

        Shipment shipment = shipmentRepository.findByTrackingCode(trackingCode)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vận đơn: " + trackingCode));

        // BẢN VÁ: ánh xạ Entity -> DTO an toàn NGAY TRƯỚC khi trả về MCP framework.
        // Chỉ 4 trường công khai được phép ra ngoài; mọi PII đều bị lược bỏ.
        return ShipmentPublicStatusDTO.from(shipment);
    }
}