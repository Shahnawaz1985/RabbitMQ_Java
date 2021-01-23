package com.eric.pojo.beans.helper;

import java.io.Serializable;

/**
 * @author Shahnawaz
 *
 */
public class EmployementDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3322843986836581686L;
	
	private String employeeId;
	private String designation;
	private String department;
	private String jobGrade;
	private Double salary;
	private Double bonus_per_annum;
	
	public EmployementDetails() {
		
	}

	public EmployementDetails(String employeeId, String designation, String department, String jobGrade, Double salary,
			Double bonus_per_annum) {
		super();
		this.employeeId = employeeId;
		this.designation = designation;
		this.department = department;
		this.jobGrade = jobGrade;
		this.salary = salary;
		this.bonus_per_annum = bonus_per_annum;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getBonus_per_annum() {
		return bonus_per_annum;
	}

	public void setBonus_per_annum(Double bonus_per_annum) {
		this.bonus_per_annum = bonus_per_annum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonus_per_annum == null) ? 0 : bonus_per_annum.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((jobGrade == null) ? 0 : jobGrade.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
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
		EmployementDetails other = (EmployementDetails) obj;
		if (bonus_per_annum == null) {
			if (other.bonus_per_annum != null)
				return false;
		} else if (!bonus_per_annum.equals(other.bonus_per_annum))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (jobGrade == null) {
			if (other.jobGrade != null)
				return false;
		} else if (!jobGrade.equals(other.jobGrade))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployementDetails [employeeId=");
		builder.append(employeeId);
		builder.append(", designation=");
		builder.append(designation);
		builder.append(", department=");
		builder.append(department);
		builder.append(", jobGrade=");
		builder.append(jobGrade);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", bonus_per_annum=");
		builder.append(bonus_per_annum);
		builder.append("]");
		return builder.toString();
	}
	
}
