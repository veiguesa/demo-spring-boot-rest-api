package com.market.survey.core.domain;

public enum Channel {
	PHONE("Phone"), PDA("PDA"), PAPER("Paper"), WEB("Web"), CAPI("Capi");
	private String value;

	private Channel(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
