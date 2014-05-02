package com.leanstartup.monitor.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsageFeeCalculatorTest {
	
	UsageFeeCalculator feeCalculatorTester = null;

	@Before
	public void setUp() throws Exception {
		feeCalculatorTester = new UsageFeeCalculatorImpl();
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
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		Double expectedFee = (double)10000;
		assertEquals(expectedFee, feeCalculated);
	}

	@Test
	public void testCalculateFeeForValidInvoiceAmount2 () {
		Double invoiceAmount = (double) 200000;
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		Double expectedFee = (double)30000;
		assertEquals(expectedFee, feeCalculated);
	}
	
	@Test
	public void testCalculateFeeForValidInvoiceAmount3 () {
		Double invoiceAmount = (double) 600000;
		Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		Double expectedFee = (double)65000;
		assertEquals(expectedFee, feeCalculated);
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
