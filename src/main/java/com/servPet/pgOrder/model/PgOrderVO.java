package com.servPet.pgOrder.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_ORDER")
public class PgOrderVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 使用自增的方式來生成主鍵
	@Column(name = "PGO_ID")
	private Integer pgoId;

	@Column(name = "MEB_ID", nullable = false)
	private Integer mebId;

	@Column(name = "PG_ID", nullable = false)
	private Integer pgId;

	@Column(name = "PET_ID", nullable = false)
	@NotEmpty(message = "請選擇須接受服務的寵物")
	private Integer petId;

	@Column(name = "SVC_ID", nullable = false)
	@NotEmpty(message = "請選擇要預約的美容項目")
	private Integer svcId;

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

	public Integer getPgoId() {
		return pgoId;
	}

	public void setPgoId(Integer pgoId) {
		this.pgoId = pgoId;
	}

	public Integer getMebId() {
		return mebId;
	}

	public void setMebId(Integer mebId) {
		this.mebId = mebId;
	}

	public Integer getPgId() {
		return pgId;
	}

	public void setPgId(Integer pgId) {
		this.pgId = pgId;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer getSvcId() {
		return svcId;
	}

	public void setSvcId(Integer svcId) {
		this.svcId = svcId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getApprStatus() {
		return apprStatus;
	}

	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getRatingComment() {
		return ratingComment;
	}

	public void setRatingComment(String ratingComment) {
		this.ratingComment = ratingComment;
	}

	public Integer getSusPoint() {
		return susPoint;
	}

	public void setSusPoint(Integer susPoint) {
		this.susPoint = susPoint;
	}

//	@ManyToOne
//	@JoinColumn(name = "MEB_ID") // 外來鍵 
//	private MebVO mebVO;
//
//	@ManyToOne
//	@JoinColumn(name = "PG_ID") // 外來鍵
//	private PgVO pgVO;
//
//	@ManyToOne
//	@JoinColumn(name = "PET_ID") // 外來鍵
//	private PetVO petVO;
//
//	@ManyToOne
//	@JoinColumn(name = "SVC_ID") // 外來鍵
//	private PgSvcItemVO svcId;

}
