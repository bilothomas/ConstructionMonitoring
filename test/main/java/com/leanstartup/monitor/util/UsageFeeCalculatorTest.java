package com.leanstartup.monitor.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.leanstartup.monitor.model.FeeRange;

public class UsageFeeCalculatorTest {
	
	UsageFeeCalculator feeCalculatorTester = null;

	FeeRange[] twoTierFeeRanges = null;
	FeeRange[] threeTierFeeRanges = null;
	FeeRange[] fourTierFeeRanges = null;
/*	FeeRange[] twoTierFeeRanges = {new FeeRange(0.0, 100000.0, 20.0),
			new FeeRange(100000.0, Double.MAX_VALUE, 10.0)};
	FeeRange[] twoTierFeeRanges = {new FeeRange(0.0, 100000.0, 20.0),
			new FeeRange(100000.0, Double.MAX_VALUE, 10.0)};

	FeeRange[] threeTierFeeRanges = {new FeeRange(0.0, 100000.0, 20.0),
			new FeeRange(100000.0, 500000.0, 10.0), 
			new FeeRange(500000.0, Double.MAX_VALUE, 5.0)};

	FeeRange[] fourTierFeeRanges = {new FeeRange(0.0, 100000.0, 20.0), 
			new FeeRange(100000.0, 500000.0, 10.0),
			new FeeRange(500000.0, 700000.0, 5.0),
			new FeeRange(700000.0, Double.MAX_VALUE, 2.5)};*/

	@Before
	public void setUp() throws Exception {
		// Two tier usage Fee Ranges
		twoTierFeeRanges = new FeeRange[2];
		twoTierFeeRanges[0] = new FeeRange(0.0, 100000.0, 20.0);
		twoTierFeeRanges[1] = new FeeRange(100000.0, Double.MAX_VALUE, 10.0);

		// Three tier usage Fee Ranges
		threeTierFeeRanges = new FeeRange[3];
		threeTierFeeRanges[0] = new FeeRange(0.0, 100000.0, 20.0);
		threeTierFeeRanges[1] = new FeeRange(100000.0, 500000.0, 10.0);
		threeTierFeeRanges[2] = new FeeRange(500000.0, Double.MAX_VALUE, 5.0);

		// Four tier usage Fee Ranges
		fourTierFeeRanges = new FeeRange[4];
		fourTierFeeRanges[0] = new FeeRange(0.0, 100000.0, 20.0);
		fourTierFeeRanges[1] = new FeeRange(100000.0, 500000.0, 10.0);
		fourTierFeeRanges[2] = new FeeRange(500000.0, 700000.0, 5.0);
		fourTierFeeRanges[3] = new FeeRange(700000.0, Double.MAX_VALUE, 2.5);

		feeCalculatorTester = new UsageFeeCalculatorImpl(threeTierFeeRanges);
	}

	@Test
	public void testCalculateFeeForInvalidInvoiceAmount() {
		Double invoiceAmount = (double) -65000;
		Double feeCalculated = null;
		try {
			feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		} catch (IllegalArgumentException exception) {
			assertEquals("Invalid invoice amount. Invoice amount needs to be a positive value.", exception.getMessage());
		} finally {
			assertNull(feeCalculated);
		}
	}

	@Test
	public void testCalculateFeeForValidInvoiceAmount1() {
		Double invoiceAmount = (double) 50000;
		feeCalculatorTester = new UsageFeeCalculatorImpl(twoTierFeeRanges);
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		Double expectedFee = (double)10000;
		assertEquals(expectedFee, feeCalculated);
	}

	@Test
	public void testCalculateFeeForValidInvoiceAmount2 () {
		Double invoiceAmount = (double) 200000;
		feeCalculatorTester = new UsageFeeCalculatorImpl(threeTierFeeRanges);
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		Double expectedFee = (double)30000;
		assertEquals(expectedFee, feeCalculated);
	}
	
	@Test
	public void testCalculateFeeForValidInvoiceAmount3 () {
		Double invoiceAmount = (double) 600000;
		feeCalculatorTester = new UsageFeeCalculatorImpl(threeTierFeeRanges);
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		Double expectedFee = (double)65000;
		assertEquals(expectedFee, feeCalculated);
	}

	@Test
	public void testCalculateFeeForValidInvoiceAmount4 () {
/*		FeeRange[] fourTierFeeRanges = {new FeeRange(0.0, 100000.0, 20.0), 
					new FeeRange(100000.0, 500000.0, 10.0),
					new FeeRange(500000.0, 700000.0, 5.0),
					new FeeRange(700000.0, Double.MAX_VALUE, 2.5)};
*/
		Double invoiceAmount = (double) 800000;
		feeCalculatorTester = new UsageFeeCalculatorImpl(fourTierFeeRanges);
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		Double expectedFee = (double)72500;
		assertEquals(expectedFee, feeCalculated);
	}
	
	@Test
	public void testCalculateFeeForValidInvoiceAmount4InvalidRanges () {
		
		Double feeCalculated = null;
		try {
			FeeRange[] invalidFourTierFeeRanges = {new FeeRange(0.0, 100000.0, 20.0), 
					new FeeRange(100000.0, 500000.0, 10.0),
					new FeeRange(800000.0, 700000.0, 5.0),
					new FeeRange(700000.0, Double.MAX_VALUE, 2.5)};

			Double invoiceAmount = (double) 800000;
			feeCalculatorTester = new UsageFeeCalculatorImpl(invalidFourTierFeeRanges);
			feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		} catch (IllegalArgumentException exp) {
			assertEquals(exp.getMessage(), "Invalid Minimum and Maximum Range values");
		} finally {
			assertNull(feeCalculated);
		}
	}
	
	@Test
	public void testCalculateFeeForInvalidCostBasis() {
		Double costBasis = (double) -350000;
		Double invoiceAmount = (double) 1200000;
		Double feeCalculated = null;
		try {
			feeCalculated = feeCalculatorTester.calculateUsageFee(costBasis, invoiceAmount);
		} catch (IllegalArgumentException exception) {
			assertEquals("Invalid cost basis. Cost Basis needs to be a positive value.", exception.getMessage());
		} finally {
			assertNull(feeCalculated);
		}
	}

	@Test
	public void testCalculateFeeForValidCostBasisAndInvoiceAmount () {
		Double costBasis = (double) 400000;
		Double invoiceAmount = (double) 200000;
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(costBasis, invoiceAmount);
		Double expectedFee = (double)15000;
		assertEquals(expectedFee, feeCalculated);
	}
}
