package com.servPet.pgSvc.model;
//美容師服務清單

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.servPet.pg.model.PgVO;
import com.servPet.pgSvcItem.model.PgSvcItemVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_SERVICE")
public class PgSvcVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@Column(name = "PG_ID")
	private Integer pgId;

	@Column(name = "SVC_ID")
	private Integer svcId;
	
	@Column(name = "SVC_TYPE", nullable = false)
	@NotEmpty(message = "請選擇服務項目體型")
	private String svcType ; 
	

	@Column(name = "SVC_PRICE", nullable = false)
	@NotEmpty(message = "價格請勿空白")
	private Integer svcPrice;

}

//@ManyToOne
//@JoinColumn(name = "PG_ID")
//private PgVO pgVO;
//
//@ManyToOne
//@JoinColumn(name = "SVC_ID")
//private PgSvcItemVO pgSvcItemVO;
