package com.bac.pojo.beans.helper;

import java.io.Serializable;

/**
 * @author Shahnawaz
 *
 */
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2823037833762509396L;
	
	private String addressLine_1;
	private String addressLine_2;
	private String landmark;
	private String city;
	private String postalZip;
	private String state;
	
	public Address() {
		
	}

	public Address(String addressLine_1, String addressLine_2, String landmark, String city, String postalZip,
			String state) {
		super();
		this.addressLine_1 = addressLine_1;
		this.addressLine_2 = addressLine_2;
		this.landmark = landmark;
		this.city = city;
		this.postalZip = postalZip;
		this.state = state;
	}

	public String getAddressLine_1() {
		return addressLine_1;
	}

	public void setAddressLine_1(String addressLine_1) {
		this.addressLine_1 = addressLine_1;
	}

	public String getAddressLine_2() {
		return addressLine_2;
	}

	public void setAddressLine_2(String addressLine_2) {
		this.addressLine_2 = addressLine_2;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalZip() {
		return postalZip;
	}

	public void setPostalZip(String postalZip) {
		this.postalZip = postalZip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressLine_1 == null) ? 0 : addressLine_1.hashCode());
		result = prime * result + ((addressLine_2 == null) ? 0 : addressLine_2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((landmark == null) ? 0 : landmark.hashCode());
		result = prime * result + ((postalZip == null) ? 0 : postalZip.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressLine_1 == null) {
			if (other.addressLine_1 != null)
				return false;
		} else if (!addressLine_1.equals(other.addressLine_1))
			return false;
		if (addressLine_2 == null) {
			if (other.addressLine_2 != null)
				return false;
		} else if (!addressLine_2.equals(other.addressLine_2))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (landmark == null) {
			if (other.landmark != null)
				return false;
		} else if (!landmark.equals(other.landmark))
			return false;
		if (postalZip == null) {
			if (other.postalZip != null)
				return false;
		} else if (!postalZip.equals(other.postalZip))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressLine_1=");
		builder.append(addressLine_1);
		builder.append(", addressLine_2=");
		builder.append(addressLine_2);
		builder.append(", landmark=");
		builder.append(landmark);
		builder.append(", city=");
		builder.append(city);
		builder.append(", postalZip=");
		builder.append(postalZip);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
