package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch3.ssf.dto.LoginForm;
import vttp2023.batch3.ssf.utility.Utility;

@Controller
public class FrontController {

	// TODO: Task 2, Task 3, Task 4, Task 6
	
	 @GetMapping("/login")
	    public String showLoginForm(Model model) {
		 
	        return "view0.html";
	 }
	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		// Invalidate the session
        session.invalidate();
        
        return "redirect:/login";
	 }
	 
		
		  @PostMapping("/login") public String successLogin(@ModelAttribute("loginForm") LoginForm loginForm,Model model,HttpSession session) {
		  
		if(loginForm.getUsername().length() < 3 || loginForm.getPassword().length() < 3)
		  {
		
		  model.addAttribute("error","username or password consists of atleast 3 chars");
		 
		  return "view0.html"; 
		  }
		  if(session.getAttribute("disabled") != null && (boolean) session.getAttribute("disabled")) {
			  return "view2.html";
		  }
		  if(loginForm.getCaptcha().equals("")) { 
			  model.addAttribute("error", "Please Enter Captcha!");
		 
		 return "view0.html"; 
		 } 
		  if(!loginForm.getCaptcha().equals(loginForm.getExpectedResult())) {
			  model.addAttribute("error", "Incorrect Captcha!");
				 
				 return "view0.html"; 
		  }
		  
		  boolean isUserAuthenticated = Utility.callExternalApi(loginForm.getUsername(),loginForm.getPassword());
		  if(isUserAuthenticated) {
			  resetLoginAttempts(session);
			  session.setAttribute("disabled",false);
			  session.setAttribute("username", loginForm.getUsername());
			  return "view1.html"; 
		  }else {
			  model.addAttribute("error","Incorrect Credentials!");
			  incrementLoginAttempts(session);
			   if(isMaxLoginAttemptsExceeded(session)) {
				   disableUser(session);
			  	return "view2.html";
			   }
		  }	  
		  
		  return "view0.html";
		 }
		 
	  private void incrementLoginAttempts(HttpSession session) {
		    Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
		    if (loginAttempts == null) {
		        loginAttempts = 1;
		    } else {
		        loginAttempts++;
		    }
		    session.setAttribute("loginAttempts", loginAttempts);
		}
		 	
		private void disableUser(HttpSession session) {
			    session.setAttribute("disabled", true);
		}


		private boolean isMaxLoginAttemptsExceeded(HttpSession session) {
			    Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
			    return loginAttempts != null && loginAttempts >= 3;
			}
		private void resetLoginAttempts(HttpSession session) {
		    session.removeAttribute("loginAttempts");
		}

		@PostMapping("/my/logout")
		public String performLogout() {
  		  return "redirect:/home";
}
}
