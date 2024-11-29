package com.servPet.pgpic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//import com.pet_groomer.model.PetGroomerVO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_GROOMER_PICTURE")
public class PgPicVO implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id // 主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 使用自增的方式來生成主鍵
	@Column(name = "PIC_ID")
	private Integer picId;

//	@ManyToOne
//	@JoinColumn(name = "PG_ID") // 外來鍵
//	private PgVO pgVO;
	
	@Column(name = "PG_ID", updatable= false)
	private Integer pgId;
	

	@Lob
	@Column(name = "PG_SVC_PIC")
	private byte[] pgSvcPic;

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}


	public Integer getPgId() {
		return pgId;
	}

	public void setPgId(Integer pgId) {
		this.pgId = pgId;
	}

	public byte[] getPgSvcPic() {
		return pgSvcPic;
	}

	public void setPgSvcPic(byte[] pgSvcPic) {
		this.pgSvcPic = pgSvcPic;
	}

	

}
