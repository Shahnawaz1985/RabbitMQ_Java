package com.eric.messaging.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.eric.pojo.beans.User;
import com.eric.pojo.beans.helper.Address;
import com.eric.pojo.beans.helper.EmployementDetails;
import com.eric.pojo.beans.helper.Name;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Shahnawaz
 *
 */
public class PojoUtility {
	
	public static User createUser() {
		
		User user = new User();
		UUID uuid = UUID.randomUUID();
		user.setId(uuid);
		
		Name name = new Name();
		name.setSalutation("Mr.");
		name.setFirstName("Md");
		name.setMiddleName("Shahnawaz");
		name.setLastName("Akhter");
		
		user.setName(name);
		
		EmployementDetails empDetails = new EmployementDetails();
		empDetails.setEmployeeId("JSC-"+new Random().nextInt(10000));
		empDetails.setJobGrade("Band-8");
		empDetails.setDesignation("Solutions Architect");
		empDetails.setDepartment("Product Strategy");
		empDetails.setSalary(5000000.00);
		empDetails.setBonus_per_annum(125.00);
		
		user.setEmpDetails(empDetails);
		
		Address address = new Address();
		address.setAddressLine_1("Boulevyard, Sector 125");
		address.setAddressLine_2("near Metro Station");
		address.setLandmark("Opposite HNA Hospital");
		address.setPostalZip("8765439");
		address.setCity("Gurugram");
		address.setState("Haryana");
		
		user.setAddress(address);
		
		return user;		
	}
	
	public static byte[] getBytes(User user) {
		byte[] bytes;
		
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(boas);
			oos.writeObject(user);
			oos.flush();
			bytes = boas.toByteArray();
		}catch(IOException ioe) {
			bytes = new byte[] {};
			System.out.println("Exception occurred during object to bytes conversion.");
			ioe.printStackTrace();
		}finally {
			try {
				oos.close();
				boas.close();
			} catch (IOException e) {
				System.out.println("Exception occurred during ObjectOutputStream and ByteArrayOutputStream cleanup.");
				e.printStackTrace();
			}
			
		}
		return bytes;
	}
	
	public static User fromBytes(byte[] body) {
        User  user = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(body);
            ois = new ObjectInputStream(bis);
            user = (User) ois.readObject();
            ois.close();
            bis.close();
        }
        catch (IOException e) {
        	user = null;
        	System.out.println("Exception occurred during bytes to object conversion.");
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally {
			try {
				bis.close();
				ois.close();
			} catch (IOException e) {
				System.out.println("Exception occurred during ObjectInputStream and ByteArrayInputStream cleanup.");
				e.printStackTrace();
			}
		}
        return user;
    }
	
	/**
	 * @param user user
	 * @return Json String
	 */
	public static String generateJson(final User user) {
		ObjectMapper mapper = null;
		String jsonString = null;
		if(null != user) {
			mapper = new ObjectMapper();
			
			try {
				jsonString = mapper.writeValueAsString(user);
			}catch(IOException ex) {
				System.out.println("Exception occurred while write Object to Json string.");
				//throw new RuntimeException("Object to Json conversion failed!");
			}
		}else {
			throw new IllegalArgumentException("User doesnot exist for Json conversion!");
		}
		return jsonString;
	}
	
	/**
	 * @param jsonString jsonString
	 * @return user object converted from json String
	 */
	public static User generateUserFromJson(final String jsonString) {
		ObjectMapper mapper = null;
		User user = null;
		if(!StringUtils.isEmpty(jsonString)) {
			mapper = new ObjectMapper();
			try {
				user = mapper.readValue(jsonString, User.class);
			}catch(IOException ex) {
				System.out.println("Exception occurred while reading Object from Json string.");
				//throw new RuntimeException("Json to Object conversion failed!");
			}
		}else {
			throw new IllegalArgumentException("Json string is not valid for user object creation!");
		}
		return user;
	}
	
	public static boolean validateUser(final User user) {
		boolean isValidated = false;
		if(null != user && null != user.getAddress() && null != user.getEmpDetails() && null != user.getName()) {
			isValidated = true;
		}else {
			isValidated = false;
		}
		return isValidated;
	}

}
