package com.thoughtworks.twistexamples.pages;

import java.util.HashMap;

public class PaymentMethods {
	private HashMap<String, String> paymentMethods = new HashMap<String, String>();
	private HashMap<String, String> paymentMethodDescriptions = new HashMap<String, String>();
	
	public PaymentMethods() {
		populatePaymentMethods();
		populatePaymentMethodDescriptions();
	}

	private void populatePaymentMethodDescriptions() {
		paymentMethodDescriptions.put("COD", "Cash On Delivery");
		
	}

	private void populatePaymentMethods() {
		paymentMethods.put("COD", "EXT_COD");
	}
	
	public String getPaymentMethod(String paymentOption){
		return paymentMethods.get(paymentOption);
	}

	public String getPaymentMethodDescription(String paymentOption) {
		return paymentMethodDescriptions.get(paymentOption);
	}
}
