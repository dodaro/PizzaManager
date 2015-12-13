package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pizzas")
public class Pizza {

	public static final int SIZE_NORMAL = 0;
	public static final int SIZE_MAXI = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "size")
	private Integer size;

	public Pizza(String name, Integer size) {
		this.id = -1;
		this.name = name;
		this.size = size;
	}

	public Pizza() {
		this.id = -1;
		this.name = "";
		this.size = SIZE_NORMAL;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
