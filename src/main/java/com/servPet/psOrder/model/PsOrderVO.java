package com.servPet.psOrder.model;

import javax.validation.constraints.NotEmpty;

import com.servPet.meb.model.MebVO;
import com.servPet.pet.model.PetVO;
import com.servPet.ps.model.PsVO;
import com.servPet.psSvcItem.model.PsSvcItemVO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PET_SITTER_ORDER")
public class PsOrderVO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id // 主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用自增的方式來生成主鍵
    @Column(name = "PSO_ID")
    private Integer psoId;

    @ManyToOne
    @JoinColumn(name = "MEB_ID") // 外來鍵
    private MebVO mebVO;
//private Integer MebId;

    @ManyToOne
    @JoinColumn(name = "PS_ID") // 外來鍵
    private PsVO psVO;
//private Integer PsId;

    @OneToOne(cascade = CascadeType.ALL) // 設定一對一關聯，級聯操作
    @JoinColumn(name = "PET_ID") // 外來鍵
    private PetVO pet;
//private Integer petId;

    @OneToMany(mappedBy = "svcId", cascade = CascadeType.ALL)
    private List<PsSvcItemVO> svcItem;
//private Integer svcId;

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

    public Integer getPsoId() {
        return psoId;
    }

    public void setPsoId(Integer psoId) {
        this.psoId = psoId;
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

    public PetVO getPet() {return pet;}

    public void setPet(PetVO pet) {this.pet = pet;}

    public List<PsSvcItemVO> getSvcItem() {return svcItem;}

    public void setSvcItem(List<PsSvcItemVO> svcItem) {this.svcItem = svcItem;}

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getApprStatus() {
        return apprStatus;
    }

    public void setApprStatus(String apprStatus) {
        this.apprStatus = apprStatus;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getRatingComment() {
        return ratingComment;
    }

    public void setRatingComment(String ratingComment) {
        this.ratingComment = ratingComment;
    }

    public Integer getSusPoint() {
        return susPoint;
    }

    public void setSusPoint(Integer susPoint) {
        this.susPoint = susPoint;
    }
}