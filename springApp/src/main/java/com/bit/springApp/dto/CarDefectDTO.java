package com.bit.springApp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDefectDTO {
	//Car
    private int carId;
    //Fault
    private String defectPartCategory;
    private String defectPartName;
    private String reportedBy;
    private Date reportedDate;
    //Location
    private double latitude;
    private double longitude;
    //Terminal
    private String terminalName;
    
}
