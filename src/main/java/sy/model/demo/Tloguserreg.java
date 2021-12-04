package sy.model.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tloguserreg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TLOGUSERREG", schema = "")
public class Tloguserreg implements java.io.Serializable {

	// Fields

	private String cid;
	private Date cdatetime;
	private String cmsg;
	private String cname;

	// Constructors

	/** default constructor */
	public Tloguserreg() {
	}

	/** minimal constructor */
	public Tloguserreg(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tloguserreg(String cid, Date cdatetime, String cmsg, String cname) {
		this.cid = cid;
		this.cdatetime = cdatetime;
		this.cmsg = cmsg;
		this.cname = cname;
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

	@Column(name = "CNAME", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}