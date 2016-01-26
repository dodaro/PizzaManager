package it.unical.pizzamanager.persistence.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Image implements Serializable {

	private static final long serialVersionUID = 6300028937200318178L;

	private String relative_path;

	private String absolute_path;

	public Image() {
		relative_path = "";
		absolute_path = "";
	}

	public Image(String absolute_path, String relative_path) {
		this.relative_path = relative_path;
		this.absolute_path = absolute_path;
	}

	public String getAbsolute_path() {
		return absolute_path;
	}

	public void setAbsolute_path(String absolute_path) {
		this.absolute_path = absolute_path;
	}

	public String getRelative_path() {
		return relative_path;
	}

	public void setRelative_path(String relative_path) {
		this.relative_path = relative_path;
	}
}
