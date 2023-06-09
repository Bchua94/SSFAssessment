package vttp2023.batch3.ssf.dto;


public class LoginForm {
    private String username;
    private String password;
    private String captcha;
    private String expectedResult;
    private String noOfAttempts;
    private boolean isUserDisable;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	public String getNoOfAttempts() {
		return noOfAttempts;
	}
	public void setNoOfAttempts(String noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}
	public boolean isUserDisable() {
		return isUserDisable;
	}
	public void setUserDisable(boolean isUserDisable) {
		this.isUserDisable = isUserDisable;
	}
    
    

}