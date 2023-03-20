package com.example.addressParm.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class Province implements Serializable{

	private static final long serialVersionUID = 6624412136600357521L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long provinceId;
	
	@Column(length = 200, nullable = false)
	private String provinceName;
	@Column(nullable = false)
	private Boolean active;
	
	@JsonIgnore
	@OneToMany(mappedBy = "province")
	private List<District> districts;

}
