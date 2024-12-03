package com.servPet.pgSvcItem.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.servPet.pgOdd.model.PgOddVO;
import com.servPet.pgOrder.model.PgOrderVO;
import com.servPet.pgSvc.model.PgSvcVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_SERVICE_ITEM")
public class PgSvcItemVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SVC_ID")
	private Integer svcId;

//	@OneToMany(mappedBy = "pgSvcItemVO")
//	@JoinColumn(name= "PGO_ID", referencedColumnName = "SVC_ID")
//	private Set<PgOrderVO> pgOrderVO = new HashSet<PgOrderVO>();
//	@OneToMany(mappedBy = "pgSvcItemVO")
//	private Set<PgOddVO> pgOddVO = new HashSet<PgOddVO>();
//	@OneToMany(mappedBy = "pgSvcItemVO")
//	private Set<PgSvcVO> pgSvcVO = new HashSet<PgSvcVO>();

	@Column(name = "SVC_DESCR", nullable = false)
	@NotEmpty(message = "請簡短描述服務項目")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,200}$", message = "服務簡介: 只能是中、英文字母、數字和_ , 且長度必須在2到200之間")
	private String svcDescr;

	@Column(name = "SVC_NAME", nullable = false)
	@NotEmpty(message = "請填寫服務項目名稱")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$", message = "服務項目: 只能是中、英文字母、數字, 且長度必須在2到10之間")
	private String svcName;

}
