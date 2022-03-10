
public class EducationalApplication extends Application {

	public EducationalApplication() {
		super();
	}

	public EducationalApplication(Applicant applicant, Passport passport, Photo photo, FinancialStatus financialStatus,
			Document document) {
		super(applicant, passport, photo, financialStatus, document);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String isFinancialValid() { // checking whether educational application financially valid or not.
		String errorString = "Applicant does not have a stable financial status";

		if (getFinancialStatus() == null) {
			return "Applicant does not have a financial status report";
		} else {
			int income = getFinancialStatus().getIncome();
			int savings = getFinancialStatus().getSavings();
			
			if (checkLetterAcceptance()) {
				
				if (checkInvitationLetter()) {
					if (income < 500) {
						return errorString;
					} else if ((income >= 500 && income < 1000) && savings >= 3000) {
						return "passed";
					} else if ((income >= 1000 && income <= 1500) && savings >= 1500) {
						return "passed";
					} else if (income >= 1500) {
						return "passed";
					}
					return errorString;
				}
				else {
					if (income < 1000) {
						return errorString;
					} else if ((income >= 1000 && income < 2000) && savings >= 6000) {
						return "passed";
					} else if ((income >= 2000 && income <= 3000) && savings >= 3000) {
						return "passed";
					} else if (income >= 3000) {
						return "passed";
					}
					return errorString;
				}
			}

			else {
				return "Applicant does not have a letter of acceptance";
			}

		}
	}

	public String calculateVisaDuration(Application application) {
		String visa = "";
		int expirationDate = (int) ApplicationManager.timeCalculator(getPassport().getExpirationDate());
		int acceptanceDuration=0 ;
		if (application.getDocument()!=null) {
			acceptanceDuration=Integer.parseInt(application.getDocument().getDurationOnMonths());
		}
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
			}
		}
		else {
			if (expirationDate>=48) {
				visa=" 4 years";
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
