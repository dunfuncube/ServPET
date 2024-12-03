package com.servPet.pgOrder.model;


import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.servPet.meb.model.MebVO;
import com.servPet.pet.model.PetVO;
import com.servPet.pg.model.PgVO;
import com.servPet.pgSvcItem.model.PgSvcItemVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_ORDER")
public class PgOrderVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 使用自增的方式來生成主鍵
	@Column(name = "PGO_ID")
	private Integer pgoId;

	@ManyToOne
	@JoinColumn(name = "MEB_ID") // 外來鍵 
	private MebVO mebVO;

	@ManyToOne
	@JoinColumn(name = "PG_ID") // 外來鍵
	private PgVO pgVO;

	@ManyToOne
	@JoinColumn(name = "PET_ID") // 外來鍵
	private PetVO petVO;

	@ManyToOne
	@JoinColumn(name = "SVC_ID") // 外來鍵
	private PgSvcItemVO svcId;

	@Column(name = "BOOKING_DATE", nullable = false)
//	@Future(message="請選擇今日之後的日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotEmpty(message = "請選擇要預約的日期")
	private Date bookingDate;

	@Column(name = "BOOKING_TIME", nullable = false)
	@NotEmpty(message = "請選擇要預約的時段")
	private String bookingTime;

	@Column(name = "BOOKING_STATUS")
	private String bookingStatus;

//	@Column(name = "SVC_ID", nullable = false)
//	@NotEmpty(message = "請選擇要預約的美容項目")
//	private Integer svcId;
	

	@Column(name = "APPR_STATUS")
	private String apprStatus;

	@Column(name = "AMOUNT", nullable = false)
	private Integer amount;

	@Column(name = "STAR")
	private Integer star;

	@Column(name = "RATING_COMMENT")
	private String ratingComment;

	@Column(name = "SUS_POINT")
	private Integer susPoint;

}
