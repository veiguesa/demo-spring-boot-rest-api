package com.market.survey.core.domain;


public enum RegistrationType {
	SELF("Self completion"), F2F("F2F"), interview("interview"), GROUP("Group"), DISCUSSION("discussion"), OBSERVATION(
			"observation"), REGISTRATION("registration");
	private String value;

	private RegistrationType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
