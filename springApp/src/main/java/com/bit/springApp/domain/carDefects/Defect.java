package com.bit.springApp.domain.carDefects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="car_defect")
public class Defect { //Hataları giren operatör burayı dolduracak

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="defect_id")
	private int defectId;	
	
//	@ManyToOne(targetEntity = Car.class, fetch = FetchType.EAGER)
//	@JoinColumn(name="car_id", foreignKey = @ForeignKey(foreignKeyDefinition = "car_fk"))
//	private int carId;	
	
	@Column(name="defect_location")
	private String defectLocation;
		
	@Column(name="damage_record")
	private int damageRecord;
}
