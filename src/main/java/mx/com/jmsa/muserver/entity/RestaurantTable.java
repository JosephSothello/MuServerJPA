package mx.com.jmsa.muserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long table_id;
	
	@Column(nullable = false)
	private Integer capacity;

	// Constructors
	public RestaurantTable() {
	}

	public RestaurantTable(Integer capacity) {
		this.capacity = capacity;
	}

	// Getters and Setters
	public Long getTable_id() {
		return table_id;
	}

	public void setTable_id(Long table_id) {
		this.table_id = table_id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "RestaurantTable [table_id=" + table_id + ", capacity=" + capacity + "]";
	}

}
