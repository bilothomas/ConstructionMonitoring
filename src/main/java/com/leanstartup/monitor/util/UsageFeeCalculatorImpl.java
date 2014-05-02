package com.leanstartup.monitor.util;

public class UsageFeeCalculatorImpl implements UsageFeeCalculator {
	
	public Double calculateUsageFee (Double invoiceAmount) throws IllegalArgumentException {
		Double usageFee = null;
		
		// Validate the given invoice amount
		if (invoiceAmount < 0) {
			throw new IllegalArgumentException("Invalid invoice amount. Invoice amount needs to be a positive value.");
		}

		// Usage Fee is assessed as 20% of the invoice amount till $100000
		// and 10% for the remaining amount of the invoice
		if (invoiceAmount <= 100000) {
			usageFee = invoiceAmount * 0.20;
		} else {
			usageFee = ((100000 * 0.20) + ((invoiceAmount - 100000) * 0.10));
		}

		return usageFee;
	}

}
