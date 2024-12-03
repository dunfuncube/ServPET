package com.servPet.pgOdd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_ORDER_DETAIL")
public class PgOddVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@Column(name = "PGO_ID", nullable = false)
	private Integer pgoId;


	@Column(name = "SVC_ID", nullable = false)
	private Integer svcId;

	@Column(name = "SVC_PRICE", nullable = false)
	private Integer svcPrice;

	public Integer getPgoId() {
		return pgoId;
	}

	public void setPgoId(Integer pgoId) {
		this.pgoId = pgoId;
	}

	public Integer getSvcId() {
		return svcId;
	}

	public void setSvcId(Integer svcId) {
		this.svcId = svcId;
	}

	public Integer getSvcPrice() {
		return svcPrice;
	}

	public void setSvcPrice(Integer svcPrice) {
		this.svcPrice = svcPrice;
	}


	
	

}

//@ManyToOne
//@JoinColumn(name = "PGO_ID")
//private PgOrderVO pgOrderVO;
//
//@OneToOne
//@JoinColumn(name = "SVC_ID")
//private PgSvcItemVO pgSvcItemVO;
//
//@OneToOne
//@JoinColumn(name = "SVC_PRICE")
//private PgSvcVO pgSvcVO;
