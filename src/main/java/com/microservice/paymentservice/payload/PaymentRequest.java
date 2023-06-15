package com.microservice.paymentservice.payload;

import com.microservice.paymentservice.utils.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
	
	private Long orderId;
	private Long amount;
	private String referencenumber;
	private PaymentMode paymentMode;

}
