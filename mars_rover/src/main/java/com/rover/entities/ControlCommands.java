package com.rover.entities;

public enum ControlCommands {
	L("L"), R("R"), M("M"),;

	private String value;

	ControlCommands(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
