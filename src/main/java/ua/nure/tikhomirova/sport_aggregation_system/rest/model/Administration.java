package ua.nure.tikhomirova.sport_aggregation_system.rest.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the administration database table.
 * 
 */
@Entity
@Table(name="administration")
@NamedQuery(name="Administration.findAll", query="SELECT a FROM Administration a")
public class Administration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private byte secretar;

	//bi-directional many-to-one association to SportCompetition
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sportCompetitionId")
	private SportCompetition sportcompetition;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;

	public Administration() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getSecretar() {
		return this.secretar;
	}

	public void setSecretar(byte secretar) {
		this.secretar = secretar;
	}

	public SportCompetition getSportcompetition() {
		return this.sportcompetition;
	}

	public void setSportcompetition(SportCompetition sportcompetition) {
		this.sportcompetition = sportcompetition;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}