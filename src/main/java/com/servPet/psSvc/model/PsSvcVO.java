//package com.servPet.psSvc.model;
//
//import com.servPet.psSvcItem.model.PsSvcItemVO;
//import com.servPet.ps.model.PsVO;
//import com.servPet.psOdd.model.PsOddVO;
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "PET_SITTER_SERVICE")
//
//public class PsSvcVO implements Serializable {
//
//    //欄位:PS_ID SVC_ID SVC_PRICE
//
//
//    //PK FK
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "PS_ID")
//    private PsVO psVO;
//
//    //FK
//    @ManyToOne
//    @JoinColumn(name = "SVC_ID")
//    private PsSvcItemVO psSvcItemVO;
//
//    //
//    @Column(name = "SVC_PRICE")
//    private Integer svcPrice;
//
//    @OneToMany(mappedBy = "psSvcVO")
//    private Set<PsOddVO> psOddVO = new HashSet<PsOddVO>();
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        PsSvcVO psSvcVO = (PsSvcVO) o;
//        return Objects.equals(psVO, psSvcVO.psVO) && Objects.equals(psSvcItemVO, psSvcVO.psSvcItemVO) && Objects.equals(svcPrice, psSvcVO.svcPrice) && Objects.equals(psOddVO, psSvcVO.psOddVO);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(psVO, psSvcItemVO, svcPrice, psOddVO);
//    }
//}
//



package com.servPet.psSvc.model;

import com.servPet.ps.model.PsVO;
import com.servPet.psOdd.model.PsOddVO;
import com.servPet.psSvcItem.model.PsSvcItemVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;




@Entity
@Table(name = "PET_SITTER_SERVICE")
public class PsSvcVO implements Serializable {

    // Getters and setters
    @EmbeddedId
    private PsSvcId id;

    @Column(name = "SVC_ID")
    private Integer svcId;

    @Column(name = "SVC_PRICE")
    private Integer svcPrice;

//    @OneToMany(mappedBy = "id.psSvcItemVO")
//    private Set<PsOddVO> psOddVO = new HashSet<>();

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsSvcVO psSvcVO = (PsSvcVO) o;
        return Objects.equals(id, psSvcVO.id);
    }

    public PsSvcVO() {
    }

    public PsSvcVO(PsSvcId id, Integer svcId, Integer svcPrice) {
        this.id = id;
        this.svcId = svcId;
        this.svcPrice = svcPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public PsSvcId getId() {
        return id;
    }

    public void setId(PsSvcId id) {
        this.id = id;
    }

    public Integer getSvcId() {
        return svcId;
    }

    public void setSvcId(Integer svcId) {
        this.svcId = svcId;
    }

    public Integer getSvcPrice() {
        return svcPrice;
    }

    public void setSvcPrice(Integer svcPrice) {
        this.svcPrice = svcPrice;
    }

}
