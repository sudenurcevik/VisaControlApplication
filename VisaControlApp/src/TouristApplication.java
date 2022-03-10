
public class TouristApplication extends Application {

	public TouristApplication() {
		super();
	}

	public TouristApplication(Applicant applicant, Passport passport, Photo photo, FinancialStatus financialStatus,
			Document document) {
		super(applicant, passport, photo, financialStatus, document);

		// TODO Auto-generated constructor stub
	}

	@Override
	public String isFinancialValid() { // checking whether tourist application financially valid or not

		if (getFinancialStatus() == null) {
			return "Applicant does not have a financial status report";

		} else {
			int income = getFinancialStatus().getIncome();
			int savings = getFinancialStatus().getSavings();
			String errorString = "Applicant does not have a stable financial status";
			if (checkInvitationLetter()) {
				if (income < 1000) {
					return errorString;
				} else if (((income < 1500 && income >= 1000) && savings >= 6000)) {
					return "passed";
				} else if ((income < 2000 && income >= 1500) && savings >= 3000) {
					return "passed";
				} else if (income >= 2000) {
					return "passed";
				}
			} else {
				if (income < 2000) {
					return errorString;
				} else if (((income < 3000 && income >= 2000) && savings >= 12000)) {
					return "passed";
				} else if ((income <= 4000 && income >= 3000) && savings >= 6000) {
					return "passed";
				} else if (income > 4000) {
					return "passed";
				}
			}
			return errorString;
		}

	}

	@Override
	public String calculateVisaDuration(Application application) { // this method calculates visa duration and returns
																	// it as a string expression.
		double DC = 0;
		double visa = 0;
		int income=0;
		int savings=0;
		if (application.getFinancialStatus()!=null ) {
			income = application.getFinancialStatus().getIncome();
			savings = application.getFinancialStatus().getSavings();
		}
		boolean canTakeVisa = true;
		double visaDuration = 0;
		if (application.checkInvitationLetter()) {
			DC = ((income - 2000) * 6 + savings) / 6000;
		} else {
			DC = ((income - 2000) * 6 + savings) / 12000;
		}

		if (DC >= 1 && DC < 2) {
			visaDuration = 0.5;
		} else if (DC >= 2 && DC < 4) {
			visaDuration = 1;
		} else if (DC >= 4) {
			visaDuration = 5;
		}
		else if (DC<1) {
			canTakeVisa=false;
		}
		
		
		if (canTakeVisa) {

			double expirationDate = (double) ApplicationManager.timeCalculator(getPassport().getExpirationDate());
			if (expirationDate>=(visaDuration*12)) {
				visa=visaDuration;
			}
			else {
				if (expirationDate<12) {
					visa=0.5;
				}
				else if (expirationDate>=12 && expirationDate<60) {
					visa=1;
				}
				else if (expirationDate>=60) {
					visa=5;
				}
			}
		}

		if (visa < 1) {
			visa *= 12;
			return (int) visa + " months";

		}
		else if (visa==1) {
			return (int) visa + " year";
		}
		return (int) visa + " years";
	}

}
