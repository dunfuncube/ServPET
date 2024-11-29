package com.servPet.pgFav.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.servPet.meb.model.MebVO;
import com.servPet.pg.model.PgVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_FAVORITE")
public class PgFavVO implements java.io.Serializable {
	private static final long serializableUID = 1L;
	
	@Id
	@Column(name = "PG_FAV_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pgFavId;
	
	// 關聯到 MEMBER 表格內的 MEB_ID 欄位
	@ManyToOne
	@JoinColumn(name = "MEB_ID")
	private MebVO mebVO;
	
	// 關聯到 Product_Details 表格內的 PD_ID 欄位
	@ManyToOne
	@JoinColumn(name = "PG_ID")
	private PgVO PgVO;
	
	@Column(name = "PG_FAV_STATUS")
	private String pgFavStatus;
	
}
