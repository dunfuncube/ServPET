package com.servPet.psCompl.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.servPet.meb.model.MebVO;
import com.servPet.ps.model.PsVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 實體類別對應資料表 PET_SITTER_COMPLAINT
 * 
 * 註1: classpath必須有javax.persistence-api-x.x.jar
 * 註2: Annotation 可以添加在屬性上，也可以添加在 getXxx() 方法上


@Entity  // 表示該類別是一個 JPA 實體
@Table(name = "PET_SITTER_COMPLAINT")  // 對應資料表 PET_SITTER_COMPLAINT
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class PsComplVO implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PS_COMPL_ID")  // 對應資料庫的 PS_COMPL_ID 欄位
    private Integer psComplId;  // 檢舉單編號


    @ManyToOne
    @JoinColumn(name = "PS_ID", referencedColumnName = "PS_ID", insertable=false, updatable=false, nullable = false)
    private PsVO psVO;  // 代表保母編號，對應 `PET_SITTER` 表格

    @Column(name = "PS_ID")
    private Integer psId;  // 只保存 psId，而不需要加載 PsVO
    
    @ManyToOne
    @JoinColumn(name = "MEB_ID", referencedColumnName = "MEB_ID",insertable=false, updatable=false, nullable = false)
    private MebVO mebVO;  // 代表會員編號，對應 `MEMBER` 表格
    
    @Column(name = "MEB_ID")
    private Integer mebId;  // 只保存 mebId，而不需要加載 MebVO

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    @Column(name = "PS_COMPL_DATE")  // 對應資料庫的 PS_COMPL_DATE 欄位
    private LocalDateTime psComplDate;  // 檢舉日期，使用 LocalDateTime 處理日期時間

    @Column(name = "PS_COMPL_RESULT")  // 對應資料庫的 PS_COMPL_RESULT 欄位

    @Size(max = 255, message = "檢舉回覆不能超過 255 個字元")
    private String psComplResult;  // 檢舉處理結果

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    @Column(name = "PS_COMPL_RES_DATE")  // 對應資料庫的 PS_COMPL_RES_DATE 欄位
    private LocalDateTime psComplResDate;  // 檢舉處理日期，使用 LocalDateTime 處理日期時間

    @Column(name = "PS_COMPL_DES")  // 對應資料庫的 PS_COMPL_DES 欄位
    @NotEmpty(message = "請填寫檢舉描述")
    @Size(max = 255, message = "描述不能超過 255 個字元")
    private String psComplDes;  // 檢舉描述

    @Column(name = "PS_COMPL_STATUS")  // 對應資料庫的 PS_COMPL_STATUS 欄位

    @Size(min = 1, max = 1)
    private String psComplStatus = "0";  // 案件處理狀態，預設值為 0: 未處理

    @Lob
    @Column(name = "PS_COMPL_UPFILES_1")  // 對應資料庫的 PS_COMPL_UPFILES_1 欄位
    private byte[] psComplUpfiles1;  // 檢舉圖片1，使用 byte[] 存儲 BLOB 類型資料

    @Lob
    @Column(name = "PS_COMPL_UPFILES_2")  // 對應資料庫的 PS_COMPL_UPFILES_2 欄位
    private byte[] psComplUpfiles2;  // 檢舉圖片2，使用 byte[] 存儲 BLOB 類型資料

    @Lob
    @Column(name = "PS_COMPL_UPFILES_3")  // 對應資料庫的 PS_COMPL_UPFILES_3 欄位
    private byte[] psComplUpfiles3;  // 檢舉圖片3，使用 byte[] 存儲 BLOB 類型資料

    @Lob
    @Column(name = "PS_COMPL_UPFILES_4")  // 對應資料庫的 PS_COMPL_UPFILES_4 欄位
    private byte[] psComplUpfiles4;  // 檢舉圖片4，使用 byte[] 存儲 BLOB 類型資料
}
