package com.servPet.carDetails.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servPet.meb.model.MebVO;
import com.servPet.pdDetails.model.PdDetailsVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHOPPING_CART_DETAILS")
//@IdClass(CartDetailsId.class)
public class CartDetailsVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CART_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	
	@ManyToOne
	@JoinColumn(name = "MEB_ID", referencedColumnName = "MEB_ID") // 後面的MEB_ID應該對應到Meb表格的欄位
	private MebVO mebVO;
	
	//MebVO對應的
//	@OneToMany(mappedBy = "mebVO")
//	private Set<CartDetailsVO> cartDetailsVO = new HashSet();
	
	@ManyToOne
	@JoinColumn(name = "PD_ID", referencedColumnName = "PD_ID")
	private PdDetailsVO pdDetailsVO;
	
	//PdDetailsVO對應的
//	@OneToMany(mappedBy = "pdDetailsVO")
//	private Set<CartDetailsVO> cartDetailsVO = new HashSet();
	
	@Column(name = "QUANTITY")
	private Integer quantity;

}
