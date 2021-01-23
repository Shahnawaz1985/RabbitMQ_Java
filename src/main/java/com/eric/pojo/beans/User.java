package com.eric.pojo.beans;

import java.io.Serializable;
import java.util.UUID;

import com.eric.pojo.beans.helper.Address;
import com.eric.pojo.beans.helper.EmployementDetails;
import com.eric.pojo.beans.helper.Name;

/**
 * @author Shahnawaz
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8406315422403141349L;
	
	private UUID Id;
	private Name name;
	private EmployementDetails empDetails;
	private Address address;
	
	public User() {
		
	}

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public EmployementDetails getEmpDetails() {
		return empDetails;
	}

	public void setEmpDetails(EmployementDetails empDetails) {
		this.empDetails = empDetails;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((empDetails == null) ? 0 : empDetails.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (empDetails == null) {
			if (other.empDetails != null)
				return false;
		} else if (!empDetails.equals(other.empDetails))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [Id=");
		builder.append(Id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", empDetails=");
		builder.append(empDetails);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
}
