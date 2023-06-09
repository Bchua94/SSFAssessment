package vttp2023.batch3.ssf.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

public class Utility {

		  
	 	public static boolean callExternalApi(String username,String password) {
	        String apiUrl = "https://authservice-production-e8b2.up.railway.app/api/authenticate"; // Replace with the actual API URL
	        
	        // Create headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        // Create the request body
	        Map<String, String> requestBody = new HashMap<>();
	        requestBody.put("username", username);
	        requestBody.put("password", password);
	        
	        // Create the request entity
	        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
	        
	        // Create RestTemplate instance
	        RestTemplate restTemplate = new RestTemplate();
	        
	        try {
	        	// Make the POST request
		        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
		        
		        // Handle the response
		        if (response.getStatusCode().is2xxSuccessful()) {
		            String responseBody = response.getBody();
		            System.out.println(responseBody);
		            return true;
		            // Process the response body as needed
		        } else {
		        	System.out.println(response);
		        	return false;
		        }
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	    }
}
