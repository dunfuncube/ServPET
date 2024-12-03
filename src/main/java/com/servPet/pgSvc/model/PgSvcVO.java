package com.servPet.pgSvc.model;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.servPet.pg.model.PgVO;
import com.servPet.pgSvcItem.model.PgSvcItemVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_SERVICE")
public class PgSvcVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@ManyToOne
	@JoinColumn(name = "PG_ID")
	private PgVO pgVO;

	@ManyToOne
	@JoinColumn(name = "SVC_ID")
	private PgSvcItemVO pgSvcItemVO;

	@Column(name = "SVC_PRICE", nullable = false)
	@NotEmpty(message = "價格請勿空白")
	private Integer svcPrice;

//	@OneToMany(mappedBy = "pgSvcVO")
//	private Set<PgOddVO> pgOddVO = new HashSet<PgOddVO>();

}
