package com.servPet.meb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "MEMBER") // 設定數據庫表名
public class MebVO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 設定主鍵自動生成
	@Column(name = "meb_id") // 對應數據庫中的 MEB_ID 欄位
	private Integer mebId;

//    @OneToMany(mappedBy = "mebVO")
//    private Set<PgOrderVO> pgOrderVO = new HashSet<PgOrderVO>();

	@NotBlank(message = "名字不可為空")
	@Column(name = "meb_name") // 對應數據庫中的 MEB_NAME 欄位
	private String mebName;

	@Email(message = "必須是Email 格式")
	@NotBlank(message = "e-mail不可為空")
	@Column(name = "meb_mail") // 對應數據庫中的 MEB_MAIL 欄位
	private String mebMail;

	@NotBlank(message = "密碼不可為空")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w]{6,16}$", message = "密碼必須為長度6~16位碼大小寫英文加數字")
	@Column(name = "meb_pwd") // 對應數據庫中的 MEB_PWD 欄位
	private String mebPwd;

	@Column(name = "meb_status") // 對應數據庫中的 MEB_STATUS 欄位
	private Integer mebStatus;

	@Column(name = "meb_address") // 對應數據庫中的 MEB_ADDRESS 欄位
	private String mebAddress;

	@Column(name = "meb_phone") // 對應數據庫中的 MEB_PHONE 欄位
	private String mebPhone;

	@Column(name = "meb_sex") // 對應數據庫中的 MEB_SEX 欄位
	private String mebSex;

	@Column(name = "bal") // 對應數據庫中的 BAL 欄位
	private Double bal;

	@Column(name = "meb_img") // 對應數據庫中的 MEB_IMG 欄位
	private byte[] mebImg; // 修正為 byte[] 來存儲圖片的二進制數據

	// 無參構造函數
	public MebVO() {
		super();
	}

	// 包含圖片二進制數據的構造函數
	public MebVO(String mebName, String mebMail, String mebPwd, Integer mebStatus, String mebAddress, String mebPhone,
			String mebSex, Double bal, byte[] mebImg) {
		this.mebName = mebName;
		this.mebMail = mebMail;
		this.mebPwd = mebPwd;
		this.mebStatus = mebStatus;
		this.mebAddress = mebAddress;
		this.mebPhone = mebPhone;
		this.mebSex = mebSex;
		this.bal = bal;
		this.mebImg = mebImg;
	}

	// Getters 和 Setters
	public Integer getMebId() {
		return mebId;
	}

	public void setMebId(Integer mebId) {
		this.mebId = mebId;
	}

	public String getMebName() {
		return mebName;
	}

	public void setMebName(String mebName) {
		this.mebName = mebName;
	}

	public String getMebMail() {
		return mebMail;
	}

	public void setMebMail(String mebMail) {
		this.mebMail = mebMail;
	}

	public String getMebPwd() {
		return mebPwd;
	}

	public void setMebPwd(String mebPwd) {
		this.mebPwd = mebPwd;
	}

	public Integer getMebStatus() {
		return mebStatus;
	}

	public void setMebStatus(Integer mebStatus) {
		this.mebStatus = mebStatus;
	}

	public String getMebAddress() {
		return mebAddress;
	}

	public void setMebAddress(String mebAddress) {
		this.mebAddress = mebAddress;
	}

	public String getMebPhone() {
		return mebPhone;
	}

	public void setMebPhone(String mebPhone) {
		this.mebPhone = mebPhone;
	}

	public String getMebSex() {
		return mebSex;
	}

	public void setMebSex(String mebSex) {
		this.mebSex = mebSex;
	}

	public Double getBal() {
		return bal;
	}

	public void setBal(Double bal) {
		this.bal = bal;
	}

	public byte[] getMebImg() {
		return mebImg;
	}

	public void setMebImg(byte[] mebImg) {
		this.mebImg = mebImg;
	}

	// 覆寫 toString() 方法，以便更好地進行日誌記錄
	@Override
	public String toString() {
		return "MebVO [mebId=" + mebId + ", mebName=" + mebName + ", mebMail=" + mebMail + ", mebPwd=" + mebPwd
				+ ", mebStatus=" + mebStatus + ", mebAddress=" + mebAddress + ", mebPhone=" + mebPhone + ", mebSex="
				+ mebSex + ", bal=" + bal + ", mebImg=" + (mebImg != null ? mebImg.length : 0) + " bytes]";
	}
}
