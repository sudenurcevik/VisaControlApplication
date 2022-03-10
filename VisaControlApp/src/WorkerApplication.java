
public class WorkerApplication extends Application {

	public WorkerApplication() {
		super();
	}

	public WorkerApplication(Applicant applicant, Passport passport, Photo photo, FinancialStatus financialStatus,
			Document document) {
		super(applicant, passport, photo, financialStatus, document);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String isFinancialValid() { // checking whether worker application financially valid or not.

		if (getFinancialStatus() == null) {
			return "Applicant does not have a financial status report";
		} else if (getFinancialStatus().getSavings() < 2000) {
			return "Applicant does not have a stable financial status";
		} else {
			if (getFinancialStatus().getSavings() >= 2000 && checkLetterAcceptance()) {
				return "passed";
			}
		}
		return "Application does not have a letter of acceptance";
	}

	public String calculateVisaDuration(Application application) {
		String visa = "";
		int acceptanceDuration=0 ;
		if (application.getDocument()!=null) {
			acceptanceDuration=Integer.parseInt(application.getDocument().getDurationOnMonths());
		}
		int expirationDate = (int) ApplicationManager.timeCalculator(getPassport().getExpirationDate());
		if (expirationDate<12) {
			visa="rejected";
		}
		else if (expirationDate>acceptanceDuration) {
			if (acceptanceDuration>0 && acceptanceDuration<=12) {
				visa=" 1 year";
			}
			else if (acceptanceDuration>12 && acceptanceDuration<=24) {
				visa=" 2 years";
			}
			else if (acceptanceDuration>24) {
				visa=" 5 years";
			}
		}
		else {
			if (expirationDate>=60) {
				visa=" 5 years";
			}
			else if (expirationDate>=24) {
				visa=" 2 years";
			}
			else if (expirationDate>=12) {
				visa=" 1 year";
			}
		}
	
		return visa;
	}

}
