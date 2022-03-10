
public class Photo { 
	private String applicationID;
	private String resolution;
	private String position;
	
	public Photo(String applicationID, String resolution, String position) {
		super();
		this.applicationID = applicationID;
		this.resolution = resolution;
		this.position = position;
	}
	
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
