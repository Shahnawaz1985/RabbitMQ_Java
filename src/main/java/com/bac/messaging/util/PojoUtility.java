package com.bac.messaging.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

import com.bac.pojo.beans.User;
import com.bac.pojo.beans.helper.Address;
import com.bac.pojo.beans.helper.EmployementDetails;
import com.bac.pojo.beans.helper.Name;

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
		empDetails.setEmployeeId("JSC-1081");
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

}
