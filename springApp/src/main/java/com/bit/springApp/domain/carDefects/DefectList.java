package com.bit.springApp.domain.carDefects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="defect_list")
public class DefectList {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="defect_id")
	private int defectId;	
	
	@Column(name="defect_name")
	private int carName;	
	
	
	
}
