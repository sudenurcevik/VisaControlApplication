
public class FinancialStatus {
	private String applicantID;
	private int income;
	private int savings;

	public FinancialStatus(String applicantID, int income, int savings) {
		this.applicantID = applicantID;
		this.income = income;
		this.savings = savings;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getSavings() {
		return savings;
	}

	public void setSavings(int savings) {
		this.savings = savings;
	}

}
