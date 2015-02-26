package ecommerce.rmall.ws;

import java.util.Set;

public class ShipmentDTO {
	private DeliveryDTO delivery;
	private Set<OrderItemDTO> details;
	private String status;
}
