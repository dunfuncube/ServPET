package com.servPet.pgCompl.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 實體類別對應資料表 PET_GROOMER_COMPLAINT
 * 
 * 註1: classpath必須有javax.persistence-api-x.x.jar
 * 註2: Annotation 可以添加在屬性上，也可以添加在 getXxx() 方法上
 */

@Entity  // 表示該類別是一個 JPA 實體
@Table(name = "PET_GROOMER_COMPLAINT")  // 對應資料表 PET_GROOMER_COMPLAINT
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PGComplVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PG_COMPL_ID")  // 對應資料庫的 PG_COMPL_ID 欄位
    private Integer pgComplId;  // 檢舉單編號

    @Column(name = "PG_ID")  // 對應資料庫的 PG_ID 欄位
    private Integer pgId;  // 美容師編號

    @Column(name = "MEB_ID")  // 對應資料庫的 MEB_ID 欄位
    private Integer mebId;  // 會員編號

    @Column(name = "PG_COMPL_DATE")  // 對應資料庫的 PG_COMPL_DATE 欄位
    private LocalDateTime pgComplDate;  // 檢舉日期，使用 LocalDateTime 處理日期時間

    @Column(name = "PG_COMPL_RESULT")  // 對應資料庫的 PG_COMPL_RESULT 欄位
    @Size(max = 255, message = "處理結果不能超過 255 個字元")
    private String pgComplResult;  // 檢舉處理結果

    @Column(name = "PG_COMPL_RES_DATE")  // 對應資料庫的 PG_COMPL_RES_DATE 欄位
    private LocalDateTime pgComplResDate;  // 檢舉處理日期，使用 LocalDateTime 處理日期時間

    @Column(name = "PG_COMPL_DES")  // 對應資料庫的 PG_COMPL_DES 欄位
    @NotEmpty(message = "請填寫檢舉描述")
    @Size(max = 255, message = "描述不能超過 255 個字元")
    private String pgComplDes;  // 檢舉描述

    @Column(name = "PG_COMPL_STATUS")  // 對應資料庫的 PG_COMPL_STATUS 欄位
    @Size(min = 1, max = 1, message = "狀態只能是 0 或 1")
    private String pgComplStatus = "0";  // 案件處理狀態，預設值為 0: 未處理

}