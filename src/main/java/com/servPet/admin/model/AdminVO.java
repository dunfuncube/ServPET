package com.servPet.admin.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 註1: classpath必須有javax.persistence-api-x.x.jar 
 * 註2: Annotation可以添加在屬性上，也可以添加在getXxx()方法之上
 */

@Entity  //要加上@Entity才能成為JPA的一個Entity類別
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminVO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ADMIN_ID")  // 對應資料庫的 admin_id 欄位
    @NotNull(message="請輸入管理員編號")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自動生成ID
    private Integer adminId;  // 屬性名稱改為 admin_id，符合Java命名慣例
    
    @Column(name = "ADMIN_NAME")  // 對應資料庫的 admin_name 欄位
    @Size(min = 2, max = 30, message = "請設定長度在{min}到{max}之間的名稱")
    private String adminName;  // 屬性名稱改為 admin_name，符合Java命名慣例
    
    @Column(name = "UPFILES")
//	@NotEmpty(message="員工照片: 請上傳照片") --> 由EmpController.java 第60行處理錯誤信息
    private byte[] upFiles;  // 若資料表中有存檔案，對應到資料庫的 UPFILES 欄位
    
    @Column(name = "ADMIN_ROLE")
    @NotEmpty(message = "請選擇管理員身分")
    private String adminRole;  // 管理員身分，對應資料庫的 admin_role
    
    @Column(name = "ADMIN_ACC_STATUS")
    @NotEmpty(message = "請選擇管理員狀態")
    private String adminAccStatus; // 預設為 "啟用"，管理員帳號狀態，對應資料庫的 admin_acc_status
    
    @Column(name = "ADMIN_ACC")
    @Size(min = 2, max = 12, message = "請設定長度在{min}到{max}之間的帳號")
    private String adminAcc;  // 管理員帳號，對應資料庫的 admin_acc
    
    @Column(name = "ADMIN_PWD")
    @Size(min = 8, max = 12, message = "請設定長度在{min}到{max}之間的密碼")
    private String adminPwd;  // 管理員密碼，對應資料庫的 admin_pwd
    
}

