package com.microservice.paymentservice.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class PaymentMode {
	
	private String cash;

	public static PaymentMode valueOf(String paymentMode) {
		PaymentMode cash=new PaymentMode();
		return cash;
	}

	
	
	

	
}
