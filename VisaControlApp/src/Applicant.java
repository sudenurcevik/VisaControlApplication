
public class Applicant {
	private String applicantID;
	private String applicantName;

	public Applicant() {

		// TODO Auto-generated constructor stub
	}

	public Applicant(String applicantID, String applicantName) {
		super();
		this.applicantID = applicantID;
		this.applicantName = applicantName;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

}
