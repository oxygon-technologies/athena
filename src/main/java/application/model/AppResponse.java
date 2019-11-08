package application.model;

public class AppResponse {
	
	ResponseCodes responseCode;
	String responseValue;

	public ResponseCodes getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCodes responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}
	
	

}
