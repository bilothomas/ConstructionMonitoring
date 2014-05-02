package com.leanstartup.monitor.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.leanstartup.monitor.util.UsageFeeCalculator;
import com.leanstartup.monitor.util.UsageFeeCalculatorImpl;

public class UsageFeeCalculatorTest {
	
	UsageFeeCalculator feeCalculatorTester = null;

	@Before
	public void setUp() throws Exception {
		feeCalculatorTester = new UsageFeeCalculatorImpl();
	}

	@SuppressWarnings("unused")
	@Test
	public void testCalculateFeeForInvalidInvoiceAmount() {
		Double invoiceAmount = (double) -60000;
		try {
			Double feeCalculated = feeCalculatorTester.calculateUsageFee(invoiceAmount);
		} catch (IllegalArgumentException exception) {
			assertEquals("Invalid invoice amount. Invoice amount needs to be a positive value.", exception.getMessage());
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

}
