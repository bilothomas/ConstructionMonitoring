package com.leanstartup.monitor.model;

public class FeeRange {

	private Double rangeMinimum;
	private Double rangeMaximum;
	private Double rangePercentage;
	
	public FeeRange() {
	}
			
	public FeeRange(Double rangeMinimum, Double rangeMaximum, Double rangePercentage) {
		validateRangeMinimum(rangeMinimum);
		validateRangeMaximum(rangeMaximum);
		validateRangePercentage(rangePercentage);
		validateRangeMinandMax(rangeMinimum, rangeMaximum);
		this.rangeMinimum = rangeMinimum;
		this.rangeMaximum = rangeMaximum;
		this.rangePercentage = rangePercentage;
	}

	public Double getRangeMinimum() {
		return rangeMinimum;
	}
	public void setRangeMinimum(Double rangeMinimum) {
		validateRangeMinimum(rangeMinimum);
		this.rangeMinimum = rangeMinimum;
	}
	public Double getRangeMaximum() {
		return rangeMaximum;
	}
	public void setRangeMaximum(Double rangeMaximum) {
		validateRangeMaximum(rangeMaximum);
		this.rangeMaximum = rangeMaximum;
	}
	public Double getRangePercentage() {
		return rangePercentage;
	}
	public void setRangePercentage(Double rangePercentage) {
		validateRangePercentage(rangePercentage);
		this.rangePercentage = rangePercentage;
	}
	
	private void validateRangeMinimum(Double rangeMinimum) {
		if (rangeMinimum < 0) {
			throw new IllegalArgumentException("Invalid Minimum Range value");
		}
	}
	
	private void validateRangeMaximum(Double rangeMaximum) {
		if (rangeMaximum < 0) {
			throw new IllegalArgumentException("Invalid Maximum Range value");
		}
	}
	
	private void validateRangePercentage(Double rangePercentage) {
		if (rangePercentage < 0) {
			throw new IllegalArgumentException("Invalid Maximum Percentage value");
		}
	}
	
	private void validateRangeMinandMax(Double rangeMinimum, Double rangeMaximum) {
		if (rangeMaximum < rangeMinimum) {
			throw new IllegalArgumentException("Invalid Minimum and Maximum Range values");
		}
	}

}
