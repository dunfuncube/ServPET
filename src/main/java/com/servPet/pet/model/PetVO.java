package com.servPet.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PET")
public class PetVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petId; // PET_ID 作為主鍵
	
//	@OneToMany(mappedBy = "petVO")
//	private Set<PgOrderVO> pgOrderVO = new HashSet <PgOrderVO>();

	@Column(name = "MEB_ID")
	private Long mebId; // MEB_ID

	@Column(name = "PET_NAME")
	private String petName; // PET_NAME

	@Column(name = "PET_TYPE")
	private String petType; // PET_TYPE

	@Column(name = "PET_IMG")
	private String petImg; // PET_IMG

	public PetVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getter 和 Setter
	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public Long getMebId() {
		return mebId;
	}

	public void setMebId(Long mebId) {
		this.mebId = mebId;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetImg() {
		return petImg;
	}

	public void setPetImg(String petImg) {
		this.petImg = petImg;
	}

	@Override
	public String toString() {
		return "PetVO [petId=" + petId + ", mebId=" + mebId + ", petName=" + petName + ", petType=" + petType
				+ ", petImg=" + petImg + "]";
	}

	// 可以根據需要添加 toString, equals, hashCode 等方法
}
