package com.microservice.paymentservice.payload;

import java.time.Instant;

import com.microservice.paymentservice.utils.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
	
	
	private Long paymentid;
	private PaymentMode paymentMode;
	private String status;
	private Long orderid;
	private Instant paymentDate;
	private Long amount;

}
