package com.leanstartup.monitor.util;

import com.leanstartup.monitor.model.FeeRange;

public class UsageFeeCalculatorImpl implements UsageFeeCalculator {

	private FeeRange[] feeRanges = null;

	public UsageFeeCalculatorImpl() {
	}

	public UsageFeeCalculatorImpl(FeeRange[] feeRanges) {
		this.feeRanges = feeRanges;
	}
	
	public Double calculateUsageFee (Double invoiceAmount) throws IllegalArgumentException {
		Double usageFee = null;
		
		// Validate the given invoice amount
		if (invoiceAmount < 0) {
			throw new IllegalArgumentException("Invalid invoice amount. Invoice amount needs to be a positive value.");
		}
		
		// Calculate the Usage Fee
		usageFee = UsageFeeCalculationHelper.calculateFee(invoiceAmount, feeRanges);
		
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
		Double previousInvoiceUsageFee = UsageFeeCalculationHelper.calculateFee(costBasis, feeRanges);
		Double totalInvoiceUsageFee = UsageFeeCalculationHelper.calculateFee(costBasis + invoiceAmount, feeRanges);
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
		 * Usage Fee is assessed as per the fee ranges passed by the caller.
		 *  
		 */
		public static Double calculateFee (Double invoiceAmount, FeeRange[] feeRanges) {
			
			boolean hasRemainingAmount = true;
			int range = 0;
			Double usageFee = 0.0;
			while (hasRemainingAmount) {
				
				Double currentTierAmount = 0.0;
				
				if (invoiceAmount > feeRanges[range].getRangeMaximum()) {
					currentTierAmount = (feeRanges[range].getRangeMaximum() - feeRanges[range].getRangeMinimum());
				} else {
					currentTierAmount = invoiceAmount - feeRanges[range].getRangeMinimum();
					hasRemainingAmount = false;
				}
				
				usageFee += currentTierAmount * (feeRanges[range].getRangePercentage() / 100);
				range++;				
			}
			return usageFee;
		}		
	}
}
