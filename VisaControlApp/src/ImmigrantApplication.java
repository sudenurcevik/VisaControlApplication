
public class ImmigrantApplication extends Application {

	public ImmigrantApplication() {
		super();
	}

	public ImmigrantApplication(Applicant applicant, Passport passport, Photo photo, FinancialStatus financialStatus,
			Document document) {
		super(applicant, passport, photo, financialStatus, document);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String isFinancialValid() { // checking whether immigrant application financially valid or not

		if (getFinancialStatus() == null) {
			return "Applicant does not have a financial status report";
		} else {
			boolean greenCard = false;
			if (checkInvitationLetter()) {

				if (getFinancialStatus().getSavings() >= 25000) {
					return "passed";
				} else {
					for (String documentType : getDocument().getTypeofDocumentStrings()) {

						if (documentType.equals("GC")) {
							greenCard = true;
						}
					}
					if ((greenCard && getFinancialStatus().getSavings() >= 2000)) {
						return "passed";
					} else if ((greenCard == false && getFinancialStatus().getSavings() >= 25000)) {
						return "passed";
					}
				}
				return "Applicant does not have a stable financial status";
			}

			else {
				String errorMessage = "";
				if (getDocument() == null && getFinancialStatus().getSavings() >= 50000) {
					return "passed";
				} else {
					for (String documentType : getDocument().getTypeofDocumentStrings()) {

						if (documentType.equals("GC")) {
							greenCard = true;
						}
					}
					if ((greenCard && getFinancialStatus().getSavings() >= 4000)) {
						return "passed";
					} else if ((greenCard == false && getFinancialStatus().getSavings() >= 50000)) {
						return "passed";
					} else {
						errorMessage = "Application does not have a financial status";
					}

				}
				return errorMessage;
			}

		}

	}

	@Override
	public String calculateVisaDuration(Application application) { // returns "permanent" since any calculation is not
																	// needed for an immigrant.

		return "Permanent";
	}

}
