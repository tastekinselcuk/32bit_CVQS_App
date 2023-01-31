package com.bit.springApp.domain.carDefects;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="car")
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private int carId;	
	
	@Column(name="car_brand")
	private String carBrand;
	
	@Column(name="car_category")
	private String carCategory;
		
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
//	private List<Defect> defects;
//	
//
//	public List<Defect> getDefects() {
//		return defects;
//	}
//
//	public void setDefects(List<Defect> defects) {
//		this.defects = defects;
//	}
		
}
