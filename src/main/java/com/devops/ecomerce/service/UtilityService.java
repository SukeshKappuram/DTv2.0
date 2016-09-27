package com.devops.ecomerce.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import com.devops.ecomerce.models.SocialNetwork;

@Service("utilityService")
public class UtilityService implements IUtilityService {
	
	List<ObjectError> errors=new ArrayList<ObjectError>();

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

	public void setErrors(List<ObjectError> errors) {
		this.errors=errors;
	}

	public List<ObjectError> getErrors() {
		// TODO Auto-generated method stub
		return errors;
	}
}
