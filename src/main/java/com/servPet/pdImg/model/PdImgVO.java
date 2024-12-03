package com.servPet.pdImg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.servPet.pdDetails.model.PdDetailsVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_IMAGES")
public class PdImgVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PD_IMG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pdImgId;

	@ManyToOne
	@JoinColumn(name = "PD_ID", nullable = false)
	private PdDetailsVO pdDetailsVO;

	@Lob
	@Column(name = "PD_IMG", nullable = false)
	private byte[] pdImg;

	@Column(name = "IMG_DESC", length = 255, nullable = false)
	@NotEmpty(message = "圖片描述 不能空白")
	private String imgDesc;

	@Column(name = "CREATED_AT", nullable = false)
	@NotNull(message = "創建時間 不能為空")
	private Date createdAt;

}