package com.servPet.pgSvc.model;
//美容師服務清單

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_SERVICE")
public class PgSvcVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@Column(name = "PG_ID", nullable = false)
	private Integer pgId;

	@Column(name = "SVC_ID", nullable = false)
	private Integer svcId;
	
	@Column(name = "SVC_TYPE", nullable = false)
	@NotEmpty(message = "請選擇服務項目體型")
	private String svcType ; 
	

	@Column(name = "SVC_PRICE", nullable = false)
	@NotEmpty(message = "價格請勿空白")
	private Integer svcPrice;


	public Integer getPgId() {
		return pgId;
	}


	public void setPgId(Integer pgId) {
		this.pgId = pgId;
	}


	public Integer getSvcId() {
		return svcId;
	}


	public void setSvcId(Integer svcId) {
		this.svcId = svcId;
	}


	public String getSvcType() {
		return svcType;
	}


	public void setSvcType(String svcType) {
		this.svcType = svcType;
	}


	public Integer getSvcPrice() {
		return svcPrice;
	}


	public void setSvcPrice(Integer svcPrice) {
		this.svcPrice = svcPrice;
	}
	
	

}

//@ManyToOne
//@JoinColumn(name = "PG_ID")
//private PgVO pgVO;
//
//@ManyToOne
//@JoinColumn(name = "SVC_ID")
//private PgSvcItemVO pgSvcItemVO;
