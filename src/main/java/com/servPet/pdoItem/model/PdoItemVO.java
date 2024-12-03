package com.servPet.pdoItem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servPet.pdDetails.model.PdDetailsVO;
import com.servPet.pdo.model.PdoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_ITEM_DETAILS")
//@IdClass(PdoItemId.class)
public class PdoItemVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PDO_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pdoItemId;
	
	@ManyToOne
	@JoinColumn(name = "PDO_ID", referencedColumnName = "PDO_ID")
	private PdoVO pdoVO;
	
	@ManyToOne
	@JoinColumn(name = "PD_ID", referencedColumnName = "PD_ID")
	private PdDetailsVO pdDetailsVO;
	
	@Column(name = "PD_QTY", nullable = false)
	private Integer pdQty;
	
	@Column(name = "PD_PRICE", nullable = false)
	private Integer pdPrice;
	
	@Column(name = "PD_TOTAL_PRICE", nullable = false)
	private Integer pdTotalPrice;
}