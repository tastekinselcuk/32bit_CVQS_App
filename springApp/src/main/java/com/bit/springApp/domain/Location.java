package com.bit.springApp.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "locations")
public class Location {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="location_id")
    private int locationId;
    
	@Column(name="latitude")
    private double latitude;
	
	@Column(name="longitude")
    private double longitude;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Defect> defects = new ArrayList<>();
}