package com.microservice.paymentservice.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.microservice.paymentservice.exception.PaymentServiceCustomException;
import com.microservice.paymentservice.model.TransactionDetails;
import com.microservice.paymentservice.payload.PaymentRequest;
import com.microservice.paymentservice.payload.PaymentResponse;
import com.microservice.paymentservice.repository.TransactionDetailsRepository;
import com.microservice.paymentservice.utils.PaymentMode;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	
	private final TransactionDetailsRepository transactionDetailsRepository;

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		
		log.info("PaymentServiceImpl | doPayment is called");

	    log.info("PaymentServiceImpl | doPayment | Recording Payment details : {}",  paymentRequest);
	    
	    TransactionDetails transactionDetails =
	    		TransactionDetails.builder()
	    		.paymentDate(Instant.now())
	    		 .paymentMode(paymentRequest.getPaymentMode().toString()) 		
	    		 .paymentStatus("SUCCESS")
	    		 .orderId(paymentRequest.getOrderId())
	    		 .referenceNumber(paymentRequest.getReferencenumber())
	    		 .amount(paymentRequest.getAmount())
	    		 .build();
	    
	    transactionDetails=transactionDetailsRepository.save(transactionDetails);
	    log.info("Transaction completed with Id: {} " , transactionDetails.getId());
		
		return transactionDetails.getId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrder(long orderid) {
		// TODO Auto-generated method stub
		
		log.info("PaymentServiceImpl | getPaymentDetailsByOrderId is called");
		log.info("PaymentServiceImpl | getPaymentDetailsByOrderById | Getting payment details for the order id: {}", orderid);
		
		TransactionDetails transactionDetails=
				transactionDetailsRepository.findByOrderId(orderid).orElseThrow(()-> new PaymentServiceCustomException(
						"TRANSACTIONDETAILS WITH GIVEN ID NOT FOUND", "TRANSACTION NOT FOUND"));
		
		
		PaymentResponse paymentResponse=
				PaymentResponse.builder()
				.paymentid(transactionDetails.getId())
				.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				.paymentDate(transactionDetails.getPaymentDate())
				.orderid(transactionDetails.getOrderId())
				.status(transactionDetails.getPaymentStatus())
				.amount(transactionDetails.getAmount())
				.build();
		
		
		log.info("PaymentServiceImpl| getPaymentDetailsByOrderById | PaymentResponse : {} " ,paymentResponse.toString());
		
		return paymentResponse;
	}

}
