package com.thoughtworks.twistexamples.pages;

import java.util.HashMap;

public class ShippingMethods {
	private HashMap<String, String> shippingMethods = new HashMap<String, String>();
	private HashMap<String, String> shippingMethodDescriptions = new HashMap<String, String>();
	
	public ShippingMethods() {
		populateShippingMethods();
		populateShippingMethodDescriptions();
	}

	private void populateShippingMethodDescriptions() {
		shippingMethodDescriptions.put("FEDEX Next Afternoon", "FEDEX Next Afternoon");
		shippingMethodDescriptions.put("FEDEX Express", "FEDEX Express");
		
	}

	private void populateShippingMethods() {
		shippingMethods.put("FEDEX Next Afternoon", "NEXT_PM@FEDEX");
		shippingMethods.put("FEDEX Express", "EXPRESS@FEDEX");
	}
	
	public String getShippingMethod(String paymentOption){
		return shippingMethods.get(paymentOption);
	}

	public String getShippingMethodDescription(String paymentOption) {
		return shippingMethodDescriptions.get(paymentOption);
	}
}
