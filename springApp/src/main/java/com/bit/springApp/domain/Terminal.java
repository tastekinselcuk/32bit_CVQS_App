package com.bit.springApp.domain;

import java.util.List;

import com.bit.springApp.enums.TerminalStatus;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "terminals")
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="terminal_id")
    private int terminalId;

    @Column(name="terminal_name", nullable = false)
    private String terminalName;
    
    @OneToMany(mappedBy = "terminal")
    private List<Defect> defects;

    
    @Enumerated(EnumType.STRING)
    private TerminalStatus status=TerminalStatus.ACTIVE;


}