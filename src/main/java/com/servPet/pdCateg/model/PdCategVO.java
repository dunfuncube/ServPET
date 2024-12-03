package com.servPet.pdCateg.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.servPet.pdDetails.model.PdDetailsVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_CATEGORY")
public class PdCategVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PD_CATEGORY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pdCategory;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "pdCategVO")
	@OrderBy("pdId asc")
	private Set<PdDetailsVO> pdDetails = new HashSet();
}