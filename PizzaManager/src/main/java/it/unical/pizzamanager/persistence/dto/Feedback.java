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

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "feedbacks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Feedback implements Serializable {

	private static final long serialVersionUID = 788082246358843418L;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "quality_rating", nullable = false)
	private Integer qualityRating;

	@Column(name = "comment")
	private String comment;
	
	public Feedback(User user, Integer qualityRating, String comment) {
		this.user = user;
		this.qualityRating = qualityRating;
		this.comment = comment;
	}

	public Feedback() {
		this.id = DatabaseHandler.NO_ID;
		this.user = null;
		this.qualityRating = 0;
		this.comment = "";
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}