package com.leanstartup.monitor.util;

public class UsageFeeCalculatorImpl implements UsageFeeCalculator {
	
	public Double calculateUsageFee (Double invoiceAmount) throws IllegalArgumentException {
		Double usageFee = null;
		
		// Validate the given invoice amount
		if (invoiceAmount < 0) {
			throw new IllegalArgumentException("Invalid invoice amount. Invoice amount needs to be a positive value.");
		}

		// Usage Fee is assessed as 
		//  Range 1: 20% of the invoice amount from $0 to $100000
		//  Range 2: 10% of the invoice amount above $100000 and up to $500000
		//  Range 3: 5% for the remaining amount of the invoice amount
		if (invoiceAmount <= 100000) {
			usageFee = invoiceAmount * 0.20;
		} else if (invoiceAmount <= 500000) {
			usageFee = ((100000 * 0.20) + ((invoiceAmount - 100000) * 0.10));
		} else {
			usageFee = ((100000 * 0.20) + (400000 * 0.10) + ((invoiceAmount - 500000) * 0.05));
		}

		return usageFee;
	}

}
