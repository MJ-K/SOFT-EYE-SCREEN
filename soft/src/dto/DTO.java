package dto;

public class DTO {
	private int id;
	private String passwd;
	private String name;
	private int isStudent;
	private int isConnect;
	
	public DTO(int id, String passwd, String name, int isStudent, int isConnect) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.isStudent = isStudent;
		this.isConnect = isConnect;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(int isStudent) {
		this.isStudent = isStudent;
	}

	public int getIsConnect() {
		return isConnect;
	}

	public void setIsConnect(int isConnect) {
		this.isConnect = isConnect;
	}
}
