package com.devops.ecomerce.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import com.devops.ecomerce.models.colabaration.SocialNetwork;

@Service("utilityService")
public class UtilityService implements IUtilityService {
	
	List<ObjectError> errors=new ArrayList<ObjectError>();

	//convert List to JSON
	
	public String getJson(List items) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
        String jsonData="";
        try {
			jsonData=mapper.writeValueAsString(items);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(jsonData);
 		return jsonData;
	}
	
	//Retrieve Image

	public String getImage(String folderName,int id){
		String fileName="";
		File folder=new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/"+folderName+"/");
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        } else {
	            //System.out.println(fileEntry.getName());
	            System.out.println(fileEntry.getName().substring(0,fileEntry.getName().indexOf(".")));
	            if(fileEntry.getName().substring(0,fileEntry.getName().indexOf(".")).equals(String.valueOf(id))){
	            	fileName=fileEntry.getName();
	            	System.out.println(id);
	            }
	        }
	    }
		return fileName;
	}
	
	//Upload Image
	
	public void uploadImage(MultipartFile file,SocialNetwork s){
		String error,fileName=null;
		try {
            fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/network/" + fileName)));
            buffStream.write(bytes);
            buffStream.close();
            error= "You have successfully uploaded " + fileName;
            System.out.println("---------->"+error);
        } catch (Exception e) {
        	error="You failed to upload " + fileName + ": " + e.getMessage();
        	System.out.println(e);
        }
		File oldName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/network/" + fileName);
        File newName = new File("D:/DevOps/workspace/ecomerce/src/main/webapp/resources/images/network/" + s.getId()+fileName.substring(fileName.indexOf(".")));
        System.out.println("new file name:--------------->"+newName);
        if(oldName.renameTo(newName)) {
           System.out.println(s.getId());
        } 
	}
	
	//No of KM between latitudes and longitudes
	
	public double haversine(double lat1, double lng1, double lat2, double lng2) {
	    int r = 6371; // average radius of the earth in km
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lng2 - lng1);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	       Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) 
	      * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = r * c;
	System.out.println(d);
	    return d;
	}
	
	//Reteriving longitudes and latitudes of pincodes
	
	public double findDistance(long... pin) throws IOException{
		String url="http://maps.googleapis.com/maps/api/geocode/json?address=";
		double dLat1=0.0,dLat2=0.0,dLon1=0.0,dLon2=0.0;
		boolean flag=false;
		for(long p:pin){
			
		URL oracle = new URL(url+p);
    	BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
    	String inputLine;
    	StringBuffer sb=new StringBuffer(1000);
    	while ((inputLine = in.readLine()) != null)
    	{
        	System.out.println(inputLine);
        	sb.append(inputLine);
        	
    	}
    	String location=sb.toString().substring(sb.toString().indexOf("geometry"));
    	String[] inps=location.substring(location.indexOf("location")).split(":");
    	for(String a:inps){
    		System.out.println("Sub dary"+a);
    	}
    	in.close();
    	
    	if(!flag){
    		dLat1=Double.parseDouble(inps[2].substring(1, inps[2].indexOf(',')));
    		dLon1=Double.parseDouble(inps[3].substring(1, inps[3].indexOf('}')));
    		flag=true;
    	}else{
    		dLat2=Double.parseDouble(inps[2].substring(1, inps[2].indexOf(',')));
    		dLon2=Double.parseDouble(inps[3].substring(1, inps[3].indexOf('}')));
    	}
		}
		return haversine(dLat1, dLon1, dLat2, dLon2);
	}
	
	//calculating no of days 
	//assuming 200 km are been traveled by order (div 200) 
	//assuming it atleast takes one day to deliver (add 1)
	
	public int delivableDays(long... pin) throws IOException{
		for(long p:pin){
			System.out.println(p);
		}
		return (int)(findDistance(pin)/200)+1;
	}
	
	/*
	public static void main(String[] arg){
		UtilityService us= new UtilityService();
		try{
		us.findDistance(500061,500007);
		}catch(Exception e){}
	}*/

	//Setter and getter for Error object
	
	public void setErrors(List<ObjectError> errors) {
		this.errors=errors;
	}

	public List<ObjectError> getErrors() {
		// TODO Auto-generated method stub
		return errors;
	}
	
}
