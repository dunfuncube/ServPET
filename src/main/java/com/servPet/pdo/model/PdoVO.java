package com.servPet.pdo.model;

//import com.servPet.cartDetails.model.CartDetailsVO;
import com.servPet.pdDetails.model.PdDetailsVO;
import com.servPet.pdoItem.model.PdoItemVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_ORDER")
public class PdoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PDO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pdoId;

	@ManyToOne
	@JoinColumn(name = "MEB_ID", nullable = false)
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "mebVO")
	private Set<CartDetailsVO> cartDetailsVO = new HashSet();

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "pdoVO")
	private Set<PdoItemVO> pdoItemVO = new HashSet();

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "pdDetailsVO")
	private Set<PdDetailsVO> pdDetailsVO = new HashSet();

	@Column(name = "MEB_NAME", nullable = false, length = 10)
	private String mebName;

	@Column(name = "PDO_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date pdoDate;

	@Column(name = "PD_TOTAL_PRICE", nullable = false)
	private Integer pdTotalPrice;

	@Column(name = "PDO_UPDATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date pdoUpdateTime;

	@Column(name = "PDO_STATUS", nullable = false, length = 1)
	private String pdoStatus;

	@Column(name = "PAYMENT_STATUS", nullable = false, length = 1)
	private String paymentStatus;

	@Column(name = "SHIPPING_ADDR", nullable = false, length = 255)
	private String shippingAddr;

	@Column(name = "SHIPPING_METHOD", length = 1)
	private String shippingMethod;

	@Column(name = "PDO_REVIEW_RATE")
	private Integer pdoReviewRate;

	@Column(name = "PDO_REVIEW_COMM", length = 255)
	private String pdoReviewComm;

	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

	@Column(name = "SHIPPING_STATUS", length = 1)
	private String shippingStatus;
}