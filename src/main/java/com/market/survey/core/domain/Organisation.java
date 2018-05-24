package com.market.survey.core.domain;


public enum Organisation {
	OMNIBUS("Omnibus"), ADHOC("ad-hoc"), SYNDICATED("syndicated");
	private String value;

	private Organisation(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
