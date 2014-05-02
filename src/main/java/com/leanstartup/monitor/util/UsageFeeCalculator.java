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
	 * @return usage fee
	 * @throws IllegalArgumentException
	 */
	Double calculateUsageFee (Double invoiceAmount) throws IllegalArgumentException;
	
	/**
	 * Calculate usage fee based on the given invoice amount and cost basis	(total invoice paid to date)
	 * 
	 * @param costBasis
	 * @param invoiceAmount
	 * @return usage fee
	 * @throws IllegalArgumentException
	 */
	Double calculateUsageFee (Double costBasis, Double invoiceAmount) throws IllegalArgumentException;	
}
