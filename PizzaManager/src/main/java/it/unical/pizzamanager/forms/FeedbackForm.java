package it.unical.pizzamanager.forms;

public class FeedbackForm {

	private Integer quality;
	private Integer fastness;
	private Integer hospitality;

	private String comment;

	public FeedbackForm() {
		this.quality = 0;
		this.fastness = 0;
		this.hospitality = 0;
		this.comment = "";
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getFastness() {
		return fastness;
	}

	public void setFastness(Integer fastness) {
		this.fastness = fastness;
	}

	public Integer getHospitality() {
		return hospitality;
	}

	public void setHospitality(Integer hospitality) {
		this.hospitality = hospitality;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
