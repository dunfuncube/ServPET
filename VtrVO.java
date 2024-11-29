package com.servPet.vtr.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VALUE_TRADING_RECORDS")
public class VtrVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VTR_ID")
	private Integer vtrId; // 使用 Integer 类型

	@Column(name = "MEB_ID", nullable = false)
	private Integer mebId; // 使用 Integer 类型

	@Column(name = "UPD_TIME")
	private LocalDateTime updTime;

	@Column(name = "CRT_TIME", nullable = false)
	private LocalDateTime crtTime;

	@Column(name = "MONEY", nullable = false)
	private Double money;

	@Column(name = "TRA_TYPE", nullable = false)
	private String traType;

	// 默认构造器
	public VtrVO() {
		super();
	}

	// Getters and setters
	public Integer getVtrId() {
		return vtrId;
	}

	public void setVtrId(Integer vtrId) {
		this.vtrId = vtrId;
	}

	public Integer getMebId() {
		return mebId;
	}

	public void setMebId(Integer mebId) {
		this.mebId = mebId;
	}

	public LocalDateTime getUpdTime() {
		return updTime;
	}

	public void setUpdTime(LocalDateTime updTime) {
		this.updTime = updTime;
	}

	public LocalDateTime getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(LocalDateTime crtTime) {
		this.crtTime = crtTime;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getTraType() {
		return traType;
	}

	public void setTraType(String traType) {
		this.traType = traType;
	}

	@Override
	public String toString() {
		return "VtrVO [vtrId=" + vtrId + ", mebId=" + mebId + ", updTime=" + updTime + ", crtTime=" + crtTime
				+ ", money=" + money + ", traType=" + traType + "]";
	}
}
