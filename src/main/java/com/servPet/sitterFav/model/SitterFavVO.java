package com.servPet.sitterFav.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.servPet.meb.model.MebVO;
import com.servPet.ps.model.PsVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PET_SITTER_FAVORITE")
public class SitterFavVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	

	public SitterFavVO(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SITTER_FAVO_ID")
	private Integer sitterFavoId;

    @ManyToOne
    @JoinColumn(name = "MEB_ID", referencedColumnName = "MEB_ID")
	private MebVO mebVO;

    @ManyToOne
    @JoinColumn(name = "PS_ID", referencedColumnName = "PS_ID")
	private PsVO psVO; // 保母編號（外鍵）

	@Column(name = "PS_FAV_STATUS", nullable = false)
	private Integer psFavStatus; // 收藏狀態
	
	
	  public SitterFavVO(MebVO mebVO, PsVO psVO) {
	        this.mebVO = mebVO;
	        this.psVO = psVO;
	    }

	public Integer getSitterFavoId() {
		return sitterFavoId;
	}

	public void setSitterFavoId(Integer sitterFavoId) {
		this.sitterFavoId = sitterFavoId;
	}

	public MebVO getMebVO() {
		return mebVO;
	}

	public void setMebVO(MebVO mebVO) {
		this.mebVO = mebVO;
	}

	public PsVO getPsVO() {
		return psVO;
	}

	public void setPsVO(PsVO psVO) {
		this.psVO = psVO;
	}

	@Override
	public String toString() {
		return "SitterFavVO [sitterFavoId=" + sitterFavoId + ", mebVO=" + mebVO + ", psVO=" + psVO + "]";
	}

}
