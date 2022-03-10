public class Application { // this is the parent class for tourist,worker,educational,immigrant since they
							// are types of application.

	private Applicant applicant;
	private Passport passport;
	private Photo photo;
	private FinancialStatus financialStatus;
	private Document document;

	public Application() {
		
	}

	public Application(Applicant applicant, Passport passport, Photo photo, FinancialStatus financialStatus,
			Document document) {
		this.applicant = applicant;
		this.passport = passport;
		this.photo = photo;
		this.financialStatus = financialStatus;
		this.document = document;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public FinancialStatus getFinancialStatus() {
		return financialStatus;
	}

	public void setFinancialStatus(FinancialStatus financialStatus) {
		this.financialStatus = financialStatus;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String isFinancialValid() {
		return null;
	}

	public String isDocumentValid() {
		return null;
	}

	public String isDurationValid() {
		return null;
	}

	public boolean checkInvitationLetter() { // for checking whether applicant has an invitation letter or not
		boolean status = false;
		if (getDocument() != null) {
			for (String documentType : getDocument().getTypeofDocumentStrings()) {
				if (documentType.equals("IL")) {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	public boolean checkLetterAcceptance() { // for checking whether applicant has an acceptance letter or not
		boolean status = false;
		if (getDocument() != null) {
			for (String documentType : getDocument().getTypeofDocumentStrings()) {
				if (documentType.equals("LA")) {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	public String isPhotoValid() { // validation for photo

		if (this.photo == null) {
			return "Applicant does not have a photo";
		} else {
			String[] resolutionStrings = getPhoto().getResolution().split("x");
			if (!(resolutionStrings[0].equals(resolutionStrings[1]) && Integer.parseInt(resolutionStrings[0]) >= 600
					&& Integer.parseInt(resolutionStrings[0]) <= 1200)) {
				return "Resolution of photo is not valid";
			} else if (!(getPhoto().getPosition().equals("Neutral Face")
					|| getPhoto().getPosition().equals("Natural Smile"))) {
				return "Position in the photo is not valid";
			}
		}
		return "passed"; // to check later overall application validation
	}

	public String isPassportValid() { // validation for passport

		if (getPassport() == null) {
			return "Applicant does not have a passport";
		} else if (!(getPassport().getPassportNumber().length() == 10 && isNumeric(getPassport().getPassportNumber())
				&& getPassport().getPassportNumber().startsWith("P"))) {
			return "Passport is not valid";
		} else if (getPassport() != null) {

			long monthsBetween = ApplicationManager.timeCalculator(getPassport().getExpirationDate());
			if (monthsBetween < 6) {
				return "Passport expiration date is not valid";
			}

		}
		return "passed"; // to check later overall application validation
	}

	public String calculateVisaDuration(Application application) { // this method is used to calculate visa duration. It
																	// waits for overriding in subclasses since every
																	// subclass has different visa duration calculation
		return null;
	}

	private boolean isNumeric(String string) { // this helper method is used for passport validation in the 122th line
		string = string.substring(string.length() - 3);
		String numbers = "0123456789";
		boolean valid = true;
		for (int i = 0; i < string.length(); i++) {
			if (numbers.indexOf(string.charAt(i)) == -1) {
				valid = false;
			}
		}
		return valid;
	}

}
