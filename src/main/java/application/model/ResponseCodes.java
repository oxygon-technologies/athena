package application.model;

public enum ResponseCodes {
	SUCCESS(001),
	ERROR(005),
	ERROR_INVALID_PASSWORD(006),
	ERROR_INVALID_USERNAME(007);
	
	private int numVal;

	ResponseCodes(int numVal) {
        this.numVal = numVal;
    }
}
