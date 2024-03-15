package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Locations {
	
	@Id
	@Column(name = "location_id")
    private Long locationId;
	
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Countries countries;

}
