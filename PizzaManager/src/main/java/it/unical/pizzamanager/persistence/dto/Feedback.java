package it.unical.pizzamanager.persistence.dto;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7166272043751535111L;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codice")
	private Integer codice;

	@Column(name = "qualityRating", nullable = false)
	private Integer qualityRating;
	
	@Column(name = "fastnessRating", nullable = false)
	private Integer fastnessRating;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name = "hospitalRating", nullable = false)
	private String hospitalRating;
	
	public Feedback()
	{
		codice=0;
		qualityRating=0;
		fastnessRating=0;
		text="";
		hospitalRating="";
		user=null;
	}

	public Feedback(User user, Integer codice, Integer qualityRating, Integer fastnessRating, String text,
			String hospitalRating) {
		super();
		this.user = user;
		this.codice = codice;
		this.qualityRating = qualityRating;
		this.fastnessRating = fastnessRating;
		this.text = text;
		this.hospitalRating = hospitalRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	public Integer getQualityRating() {
		return qualityRating;
	}

	public void setQualityRating(Integer qualityRating) {
		this.qualityRating = qualityRating;
	}

	public Integer getFastnessRating() {
		return fastnessRating;
	}

	public void setFastnessRating(Integer fastnessRating) {
		this.fastnessRating = fastnessRating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHospitalRating() {
		return hospitalRating;
	}

	public void setHospitalRating(String hospitalRating) {
		this.hospitalRating = hospitalRating;
	}
	
}