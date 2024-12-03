package com.servPet.psOrder.model;

import javax.validation.constraints.NotEmpty;

import com.servPet.meb.model.MebVO;
import com.servPet.pet.model.PetVO;
import com.servPet.ps.model.PsVO;
import com.servPet.psSvcItem.model.PsSvcItemVO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_SITTER_ORDER")
public class PsOrderVO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id // 主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用自增的方式來生成主鍵
    @Column(name = "PSO_ID")
    private Integer psoId;

//    @ManyToOne
//    @JoinColumn(name = "MEB_ID") // 外來鍵
//    private MebVO mebVO;
private Integer MebId; 
//    @ManyToOne
//    @JoinColumn(name = "PS_ID") // 外來鍵
//    private PsVO psVO;
private Integer PsId;
//    @ManyToOne
//    @JoinColumn(name = "PET_ID") // 外來鍵
//    private PetVO petVO;
private Integer PetId;
//    @ManyToOne
//    @JoinColumn(name = "SVC_ID")  // 外來鍵對應到 `PET_SITTER_SERVICE_ITEM` 表中的 `SVC_ID` 欄位
//    private PsSvcItemVO psSvcItemVO;  // 新增此屬性以保持與 `PsSvcItemVO` 的雙向關聯
private Integer SvcId;

    @Column(name = "BOOKING_DATE", nullable = false)
// @Future(message="請選擇今日之後的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "請選擇要預約的日期")
    private LocalDateTime bookingDate;

    @Column(name = "BOOKING_TIME", nullable = false)
    @NotEmpty(message = "請選擇要預約的時段")
    private String bookingTime;

    @Column(name = "BOOKING_STATUS")
    private String bookingStatus;

//    @Column(name = "SVC_ID", nullable = false)
//    @NotEmpty(message = "請選擇要預約的美容項目")
//    private Integer svcId;

    @Column(name = "APPR_STATUS")
    private String apprStatus;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Column(name = "STAR")
    private Integer star;

    @Column(name = "RATING_COMMENT")
    private String ratingComment;

    @Column(name = "SUS_POINT")
    private Integer susPoint;

}