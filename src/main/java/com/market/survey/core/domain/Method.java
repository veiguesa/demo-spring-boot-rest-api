package com.market.survey.core.domain;

public enum Method {
QUANTITATIVE("Qualitative"),QUALITATIVE("Qualitative");
	private String value;

	private Method(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
