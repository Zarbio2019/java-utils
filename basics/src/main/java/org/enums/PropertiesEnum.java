package org.enums;

// Properties with default values

// package com.bbva.pomc.dto.bankbranch.util;

public enum PropertiesEnum {

	MANAGEMENT_BRANCH_DEFAULT("management-branch-default","0486"),
	GEOLOCATION_RADIUS_INCREASE_VALUE("geolocation-radius-increase-value","500"),
	GEOLOCATION_RADIUS_LIMIT_VALUE("geolocation-radius-limit-value","5000"),
	GEOLOCATION_INITIAL_RADIUS_VALUE("geolocation-initial-value-radius","500"),
	CONTRACTING_BRANCH_DEFAULT("contracting-branch-default","0504");
	
	private String prop;
	
	private String valueDefault;

	private PropertiesEnum(String prop, String valueDefault) {
		this.prop = prop;
		this.valueDefault = valueDefault;
	}

	public String getProp() {
		return prop;
	}

	public String getValueDefault() {
		return valueDefault;
	}
}
/* call:
return applicationConfigurationService.getProperty(PropertiesEnum.MANAGEMENT_BRANCH_DEFAULT.getProp());

when(applicationConfigurationService.getProperty(PropertiesEnum.MANAGEMENT_BRANCH_DEFAULT.getProp()))
     .thenReturn("0804");

String officeDefault = PropertiesEnum.CONTRACTING_BRANCH_DEFAULT.getValueDefault();
*/
