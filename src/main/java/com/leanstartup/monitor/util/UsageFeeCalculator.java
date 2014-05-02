package com.leanstartup.monitor.util;

/**
 * Utility for calculating usage fee for the Construction monitoring application
 * 
 * @author BiloThomas
 *
 */
public interface UsageFeeCalculator {

	/**
	 * Calculate usage fee based on the given invoice amount
	 * 
	 * @param invoiceAmount
	 * @return
	 * @throws IllegalArgumentException
	 */
	Double calculateUsageFee (Double invoiceAmount) throws IllegalArgumentException;
}
