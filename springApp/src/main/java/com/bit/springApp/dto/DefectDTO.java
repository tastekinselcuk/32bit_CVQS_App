package com.bit.springApp.dto;

import java.util.Date;

import com.bit.springApp.domain.Location;
import com.bit.springApp.domain.Terminal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefectDTO {

	private int defectId;
	private String defectPartCategory;
	private String defectPartName;
	private String reportedBy;
	private Date reportedDate;
	private Location location;
	private Terminal terminal;

}
