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
public class District implements Serializable{
	
	private static final long serialVersionUID = -2348697967620405961L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long districtId;
	
	@Column(length = 200, nullable = false)
	private String districtName;
	@Column(nullable = false)
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name = "provinceId")
	private Province province;
	
	@JsonIgnore
	@OneToMany(mappedBy = "district")
	private List<SubDistrict> subdistricts;
}
