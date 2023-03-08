package com.bit.springApp.domain;

import java.util.Date;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "defects")
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="defect_id")
    private int defectId;

    @Column(name = "defect_part_category")
    private String defectPartCategory;


    @Column(name = "defect_part_name")
    private String defectPartName;
    
    @Column(name = "reported_by")
    private String reportedBy;
    
    @Column(name = "reported_date")
    private Date reportedDate;
    
    @Column(name = "is_deleted")
    private boolean deleted = false;
    
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;


}
