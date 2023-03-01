package com.bit.springApp.domain;


import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="cars")
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private int carId;	

	@Column(name="car_model")
	private String carModel;
	
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Defect> defects;
		
}

	
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
		

