package com.servPet.adminPer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.servPet.admin.model.AdminVO;
import com.servPet.fnc.model.FncVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * AdminPerVO 對應到資料庫的 ADMIN_PER 表格。
 * 記錄管理員的權限。
 */
@Entity
@Table(name = "ADMIN_PER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminPerVO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主鍵自動遞增
    private Integer adminPerId;  // 權限類別編號，從 26000 開始自動遞增

    @ManyToOne  // 透過 ManyToOne 關聯
    @JoinColumn(name = "ADMIN_ID", referencedColumnName = "ADMIN_ID")  // 參照 ADMIN 表格的 ADMIN_ID
    private AdminVO adminVO;  // 這是管理員 VO，對應資料庫的 ADMIN_ID

    @ManyToOne  // 透過 ManyToOne 關聯
    @JoinColumn(name = "FNC_ID", referencedColumnName = "FNC_ID")  // 參照 FNC 表格的 FNC_ID
    private FncVO fncVO;  // 這是權限功能 VO，對應資料庫的 FNC_ID

}