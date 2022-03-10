import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ApplicationManager {

	private static ArrayList<Application> applications = new ArrayList<>(); // this array keeps Application objects such
																			// as touristApplication, workerApplication,
																			// educationalApplication, and
																			// immigrantApplication

	private static Application createApplicationWithID(String id) { // this static helper method can create and return
																	// an appropriate application object by checking
																	// ids.
		Application application;
		switch (id.substring(0, 2)) {

		case "11": {
			application = new TouristApplication();
			application.setApplicant(new Applicant());
			application.getApplicant().setApplicantID(id);
			break;
		}
		case "23": {
			application = new WorkerApplication();
			application.setApplicant(new Applicant());
			application.getApplicant().setApplicantID(id);
			break;
		}
		case "25": {
			application = new EducationalApplication();
			application.setApplicant(new Applicant());
			application.getApplicant().setApplicantID(id);
			break;
		}
		case "30": {
			application = new ImmigrantApplication();
			application.setApplicant(new Applicant());
			application.getApplicant().setApplicantID(id);
			break;
		}
		default: {
			application = new TouristApplication(); // this is a trivial operation to avoid getting an error.

			break;
		}
		}
		return application;
	}

	private static void fillingApplicationArrayByApplication() throws FileNotFoundException { // this helper method uses the
																								// array that holds
																								// lines of the given
																								// file and gathers each
																								// dictinct type of
																								// information in an
																								// arraylist that holds
																								// application objects.
		ArrayList<String[]> lineStrings = FileIO.read();
		for (String[] strings : lineStrings) {
			String typeString = strings[0];
			if (typeString.equals("A")) {
				Application application = createApplicationWithID(strings[1]);
				application.getApplicant().setApplicantName(strings[2]);
				applications.add(application);
			}

		}
		for (String[] strings : lineStrings) {
			String id = strings[1];
			for (Application application : applications) {
				if (application.getApplicant().getApplicantID().equals(id)) {
					switch (strings[0]) {
					case "S": {
						application.setPassport(new Passport(id, strings[2], strings[3]));
						break;

					}
					case "P": {
						application.setPhoto(new Photo(id, strings[2], strings[3]));
						break;

					}
					case "D": { // some applicants cannot have duration time so this case is also handled.

						if (application.getDocument() == null) {
							if (strings.length == 4) {
								application.setDocument(new Document(id, strings[3]));
								application.getDocument().addDocument(strings[2]);

							} else {
								application.setDocument(new Document(id));
								application.getDocument().addDocument(strings[2]);
							}
						} else {
							application.getDocument().addDocument(strings[2]);
						}
						break;

					}
					case "F": {
						application.setFinancialStatus(
								new FinancialStatus(id, Integer.parseInt(strings[2]), Integer.parseInt(strings[3])));
						break;
					}
					default: {
						break;

					}
					}

				}
			}
		}
	}

	private static String checkApplication(Application application) { // this helper method checks whether application
																		// is proper or not. This method is used in
																		// printableOutput method.
		String errorMessageString = "";

		ArrayList<String> errors = new ArrayList<>();
		errors.add(application.isPassportValid());
		errors.add(application.isPhotoValid());
		errors.add(application.isFinancialValid());
		for (String error : errors) {
			if (error.equals("passed")) {
				errorMessageString = "passed";
			} else {
				errorMessageString = error;
				break;
			}
		}
		return errorMessageString;

	}

	public static ArrayList<String> printableOutput() throws FileNotFoundException { // returns the sorted arraylist that holds strings as output
														// lines.
		fillingApplicationArrayByApplication();
		ArrayList<String> lines = new ArrayList<>();
		String outputLine = "";
		for (Application application : applications) {
			String messageString = checkApplication(application);
			String[] temp = application.getClass().toString().split(" ");
			String visaType = temp[1].substring(0, (temp[1].length() - 11));
			if (messageString.equals("passed") && !(application.calculateVisaDuration(application).equals("rejected"))) {
				outputLine = "Applicant ID:" + application.getApplicant().getApplicantID() + "," + "Name: "
						+ application.getApplicant().getApplicantName() + "," + "Visa Type: " + visaType
						+ ", Status: Accepted, Visa Duration: " + application.calculateVisaDuration(application);
			}
			else if (application.calculateVisaDuration(application).equals("rejected")) {
				outputLine = "Applicant ID:" + application.getApplicant().getApplicantID() + "," + "Name: "
						+ application.getApplicant().getApplicantName() + "," + "Visa Type: " + visaType
						+ ", Status: Rejected, Reason: Passport expiration date is not valid" ;
			}
			else {
				outputLine = "Applicant ID:" + application.getApplicant().getApplicantID() + "," + "Name: "
						+ application.getApplicant().getApplicantName() + "," + "Visa Type: " + visaType
						+ ", Status: Rejected, Reason: " + checkApplication(application);
			}
			lines.add(outputLine);
		}
		lines.sort(null);
		return lines;
	}

	public static long timeCalculator(String date) { // this general method calculates the time between given date and
														// the current date.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.parse(date, formatter);
		long monthsBetween = ChronoUnit.MONTHS.between(date1, date2);
		return monthsBetween;

	}

}
