package Dashboard.Source;

public class SourcesData {
	private Integer Sid;
	private String Name;
	private String Lastname;
	private String Willaya;
	public SourcesData(Integer Sid,String Name,String Lastname,String Willaya) {
		
		this.Sid=Sid;
		this.Name=Name;
		this.Lastname=Lastname;
		this.Willaya=Willaya;
	}
	public Integer  getSid() {
		return this.Sid;
	}

	public String  getName() {
		return this.Name;
	}
	public String  getLastname() {
		return this.Lastname;
	}
	public String  getWillaya() {
		return this.Willaya;
	}
}
