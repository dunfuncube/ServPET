package com.servPet.pdoItem.model;

import com.servPet.pdDetails.model.PdDetailsVO;
import com.servPet.pdo.model.PdoVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_ITEM_DETAILS")
@IdClass(PdoItemId.class)
public class PdoItemVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "PDO_ID", referencedColumnName = "PDO_ID")
	private PdoVO pdoVO;
	
	@Id
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