package com.example.addressParm.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class Postcode implements Serializable{

	private static final long serialVersionUID = -8364488226978112019L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postcodeId;
	
	@Column(length = 10, nullable = false)
	private String postcode;
	@Column(nullable = false)
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name = "subdistrictId")
	private SubDistrict subdistrict;
	
}
