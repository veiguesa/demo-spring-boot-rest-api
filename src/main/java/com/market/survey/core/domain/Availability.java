package com.market.survey.core.domain;

public enum Availability {

	PUBLICY("Publicly available"), OWNER("available to owner only"),NOT_AVAILABLE("Not available"),
	WEB("Web"), MAY_BE_PURCHASED("May be purchased");
	private String value;

	private Availability(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}

