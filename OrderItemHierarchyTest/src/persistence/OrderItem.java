package persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class OrderItem implements Serializable {

	private static final long serialVersionUID = -8134068295126775027L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "theorder")
	private Order theorder;

	public OrderItem() {
		this.id = -1;
		this.theorder = new Order();
	}

	public OrderItem(Order order) {
		this.id = -1;
		this.theorder = order;
	}
}
