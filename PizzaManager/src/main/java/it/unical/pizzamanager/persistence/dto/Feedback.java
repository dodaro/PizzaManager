package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedbacks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Feedback implements Serializable {

	private static final long serialVersionUID = 788082246358843418L;

	private static final int NO_ID = -1;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "quality_rating", nullable = false)
	private Integer qualityRating;

	@Column(name = "fastness_rating", nullable = false)
	private Integer fastnessRating;

	@Column(name = "hospitality_rating", nullable = false)
	private Integer hospitalityRating;

	@Column(name = "text", nullable = false)
	private String text;

	public Feedback() {
		id = NO_ID;
		user = new User();
		qualityRating = 0;
		fastnessRating = 0;
		hospitalityRating = 0;
		text = "";
	}

	public Feedback(User user, Integer qualityRating, Integer fastnessRating,
			Integer hospitalityRating, String text) {
		this.id = NO_ID;
		this.user = user;
		this.qualityRating = qualityRating;
		this.fastnessRating = fastnessRating;
		this.text = text;
		this.hospitalityRating = hospitalityRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getHospitalityRating() {
		return hospitalityRating;
	}

	public void setHospitalityRating(Integer hospitalityRating) {
		this.hospitalityRating = hospitalityRating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}