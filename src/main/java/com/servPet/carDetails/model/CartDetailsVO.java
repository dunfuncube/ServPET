package com.servPet.carDetails.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SHOPPING_CART_DETAILS")
@IdClass(CartDetailsId.class)
public class CartDetailsVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "PD_ID", referencedColumnName = "PD_ID")
	private PdDetailsVO pdDetailsVO;
	
	@Column(name = "QUANTITY")
	private Integer quantity;

}
