package com.servPet.pgOdd.model;

import javax.persistence.*;

import com.servPet.pgOrder.model.PgOrderVO;
import com.servPet.pgSvc.model.PgSvcVO;
import com.servPet.pgSvcItem.model.PgSvcItemVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_ORDER_DETAIL")
public class PgOddVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@ManyToOne
	@JoinColumn(name = "PGO_ID")
	private PgOrderVO pgOrderVO;

	@OneToOne
	@JoinColumn(name = "SVC_ID")
	private PgSvcItemVO pgSvcItemVO;

	@OneToOne
	@JoinColumn(name = "SVC_PRICE")
	private PgSvcVO pgSvcVO;

	public PgOrderVO getPgOrderVO() {
		return pgOrderVO;
	}

	public void setPgOrderVO(PgOrderVO pgOrderVO) {
		this.pgOrderVO = pgOrderVO;
	}

	public PgSvcItemVO getPgSvcItemVO() {
		return pgSvcItemVO;
	}

	public void setPgSvcItemVO(PgSvcItemVO pgSvcItemVO) {
		this.pgSvcItemVO = pgSvcItemVO;
	}

	public PgSvcVO getPgSvcVO() {
		return pgSvcVO;
	}

	public void setPgSvcVO(PgSvcVO pgSvcVO) {
		this.pgSvcVO = pgSvcVO;
	}
	
	

}
