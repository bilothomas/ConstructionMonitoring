package com.leanstartup.monitor.util;

public class UsageFeeCalculatorImpl implements UsageFeeCalculator {
	
	public Double calculateUsageFee (Double invoiceAmount) throws IllegalArgumentException {
		Double usageFee = null;
		
		// Validate the given invoice amount
		if (invoiceAmount < 0) {
			throw new IllegalArgumentException("Invalid invoice amount. Invoice amount needs to be a positive value.");
		}
		
		// Calculate the Usage Fee
		usageFee = UsageFeeCalculationHelper.calculateFee(invoiceAmount);
		
		return usageFee;
	}

	public Double calculateUsageFee (Double costBasis, Double invoiceAmount) throws IllegalArgumentException {
		Double usageFee = null;

		// Validate the given invoice amount and cost basis
		if (costBasis < 0) {
			throw new IllegalArgumentException("Invalid cost basis. Cost Basis needs to be a positive value.");
		}
		if (invoiceAmount < 0) {
			throw new IllegalArgumentException("Invalid invoice amount. Invoice amount needs to be a positive value.");
		}

		// Calculate the Usage Fee
		Double previousInvoiceUsageFee = UsageFeeCalculationHelper.calculateFee(costBasis);
		Double totalInvoiceUsageFee = UsageFeeCalculationHelper.calculateFee(costBasis + invoiceAmount);
		usageFee = totalInvoiceUsageFee - previousInvoiceUsageFee;
		if (usageFee < 0) {
			throw new RuntimeException("Invalid Usage Fee calculated.");
		}

		return usageFee;		
	}

	/*
	 * Helper class for performing the calculation of usage fee
	 */
	private static class UsageFeeCalculationHelper {

		/*
		 * Usage Fee is assessed as 
		 *  Range 1: 20% of the invoice amount from  $0 to $100000
		 *  Range 2: 10% of the invoice amount above $100000 and up to $500000
		 *  Range 3: 5% for the remaining amount of the invoice amount
		 *  
		 */
		public static Double calculateFee (Double invoiceAmount) {
			
			// As of now three value ranges are supported.
			// Whenever a new range is added the below arrays need to be updated 
			// to include the values for its minimum range, maximum range and percentage
			Double[] rangeMinimum = {0.0, 100000.0, 500000.0};
			Double[] rangeMaximum = {100000.0, 500000.0, Double.MAX_VALUE};
			Double[] percentage = {20.0, 10.0, 5.0};

			boolean hasRemainingAmount = true;
			int range = 0;
			Double usageFee = 0.0;
			while (hasRemainingAmount) {
				Double currentTierAmount = 0.0;
				
				if (invoiceAmount > rangeMaximum[range]) {
					currentTierAmount = (rangeMaximum[range] - rangeMinimum[range]);
				} else {
					currentTierAmount = invoiceAmount - rangeMinimum[range];
					hasRemainingAmount = false;
				}
				
				usageFee += currentTierAmount * (percentage[range] / 100);
				range++;				
			}
			return usageFee;
		}
	}
}
