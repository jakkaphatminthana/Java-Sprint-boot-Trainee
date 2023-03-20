package com.example.addressParm.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class SubDistrict implements Serializable{

	private static final long serialVersionUID = 4108627861200237789L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subdistrictId;
	
	@Column(length = 200, nullable = false)
	private String subdistrictName;
	@Column(nullable = false)
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name = "districtId")
	private District district;
	
	@JsonIgnore
	@OneToMany(mappedBy = "subdistrict")
	private List<Postcode> postcodes;
	
}
