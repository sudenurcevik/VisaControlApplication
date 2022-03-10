
public class Passport {
	private String applicantID;
	private String passportNumber;
	private String expirationDate;
	public Passport(String applicantID, String passportNumber, String expirationDate) {
		super();
		this.applicantID = applicantID;
		this.passportNumber = passportNumber;
		this.expirationDate = expirationDate;
	}
	public String getApplicantID() {
		return applicantID;
	}
	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	

}
