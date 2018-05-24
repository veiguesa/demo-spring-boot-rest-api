package com.market.survey.core.domain;


public enum Type {
	TEST("Product test"), SEGMENTATION("segmentation"), CUSTOMER_SATISFACTION("Customer satisfaction"), ADVERTISING(
			"Advertising effect"), MEDIA("Media"), COVERAGE("coverage");
	private String value;

	private Type(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
