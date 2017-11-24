package ticket.ticket.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "complains1")
public class complains1 implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "passengerName")
	private String passengerName;

	@Column(name = "pemail")
	private String pemail;

	@Column(name = "complainDate")
	private String complainDate;

	@Column(name = "complainDesc")
	private String complainDesc;

	@Column(name = "complainStatus")
	private String complainStatus;



	public complains1(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPemail() {
		return pemail;
	}

	public void setPemail(String pemail) {
		this.pemail = pemail;
	}

	public String getComplainDate() {
		return complainDate;
	}

	public void setComplainDate(String complainDate) {
		this.complainDate = complainDate;
	}

	public String getComplainDesc() {
		return complainDesc;
	}

	public void setComplainDesc(String complainDesc) {
		this.complainDesc = complainDesc;
	}

	public String getComplainStatus() {
		return complainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
	}

	public complains1(String  passengerName, String pemail , String complainDate,
					  String complainDesc, String complainStatus) {

		this.passengerName = passengerName;
		this.pemail = pemail;
		this.complainDate = complainDate;
		this.complainDesc = complainDesc;
		this.complainStatus = complainStatus;
	}

}