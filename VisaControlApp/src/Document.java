import java.util.ArrayList;

public class Document {
	private String applicantID;
	private String durationOnMonths;
	private ArrayList<String> typeofDocumentStrings = new ArrayList<>(); // this array holds the document types such as
																			// LA, IL, GC

	public ArrayList<String> getTypeofDocumentStrings() {
		return typeofDocumentStrings;
	}

	public void setTypeofDocumentStrings(ArrayList<String> typeofDocumentStrings) {
		this.typeofDocumentStrings = typeofDocumentStrings;
	}

	public Document(String id) {
		this.applicantID = id;
	}

	public Document(String applicantID, String durationOnMonths) {
		this.applicantID = applicantID;
		this.durationOnMonths = durationOnMonths;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public String getDurationOnMonths() {
		return durationOnMonths;
	}

	public void setDurationOnMonths(String durationOnMonths) {
		this.durationOnMonths = durationOnMonths;
	}

	public void addDocument(String string) {
		this.typeofDocumentStrings.add(string);
	}

}
