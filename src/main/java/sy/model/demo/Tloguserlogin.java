package sy.model.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tloguserlogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TLOGUSERLOGIN", schema = "")
public class Tloguserlogin implements java.io.Serializable {

	// Fields

	private String cid;
	private String cname;
	private Date cdatetime;
	private String cmsg;

	// Constructors

	/** default constructor */
	public Tloguserlogin() {
	}

	/** minimal constructor */
	public Tloguserlogin(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tloguserlogin(String cid, String cname, Date cdatetime, String cmsg) {
		this.cid = cid;
		this.cname = cname;
		this.cdatetime = cdatetime;
		this.cmsg = cmsg;
	}

	// Property accessors
	@Id
	@Column(name = "CID", nullable = false, length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(name = "CNAME", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CDATETIME", length = 7)
	public Date getCdatetime() {
		return this.cdatetime;
	}

	public void setCdatetime(Date cdatetime) {
		this.cdatetime = cdatetime;
	}

	@Column(name = "CMSG", length = 200)
	public String getCmsg() {
		return this.cmsg;
	}

	public void setCmsg(String cmsg) {
		this.cmsg = cmsg;
	}

}