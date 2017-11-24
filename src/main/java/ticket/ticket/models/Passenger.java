package ticket.ticket.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "pnic")
	private String pnic;

	@Column(name = "pname")
	private String pname;

	@Column(name = "paddress")
	private String paddress;

	@Column(name = "pmobile")
	private String pmobile;

	@Column(name = "pgender")
	private String pgender;

	@Column(name = "pdob")
	private String pdob;

	@Column(name = "passengerEmail")
	private String passengerEmail;

	@Column(name = "cardNo")
	private String cardNo;

	@Column(name = "issueDate")
	private String issueDate;

	@Column(name = "expiryDate")
	private String expiryDate;

	@Column(name = "validity")
	private String validity;

	@Column(name = "balance")
	private Double balance;

	public String getPassengerEmail() {
		return passengerEmail;
	}

	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPnic() {
		return pnic;
	}

	public void setPnic(String pnic) {
		this.pnic = pnic;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public String getPmobile() {
		return pmobile;
	}

	public void setPmobile(String pmobile) {
		this.pmobile = pmobile;
	}

	public String getPgender() {
		return pgender;
	}

	public void setPgender(String pgender) {
		this.pgender = pgender;
	}

	public String getPdob() {
		return pdob;
	}

	public void setPdob(String pdob) {
		this.pdob = pdob;
	}

	public Passenger(){

	}

	public Passenger( String  pnic, String pname , String paddress,
					  String pmobile, String pgender,String pdob,String passengerEmail,
					  String cardNo,String issueDate,String expiryDate,
					  String validity, Double balance) {

		this.pnic = pnic;
		this.pname = pname;
		this.paddress = paddress;
		this.pmobile = pmobile;
		this.pgender = pgender;
		this.pdob = pdob;
		this.passengerEmail = passengerEmail;
		this.cardNo = cardNo;
		this.issueDate = issueDate;
		this.expiryDate = expiryDate;
		this.validity = validity;
		this.balance = balance;
	}

}