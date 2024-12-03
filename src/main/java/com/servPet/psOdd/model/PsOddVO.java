package com.servPet.psOdd.model;


import com.servPet.psOrder.model.PsOrderVO;
import com.servPet.psSvcItem.model.PsSvcItemVO;
import com.servPet.psSvc.model.PsSvcVO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PET_SITTER_ORDER_DETAIL")

public class PsOddVO implements Serializable {
    @Id
//    @ManyToOne
//    @JoinColumn(name = "PSO_ID")
//    private PsOrderVO psOrderVO;
private Integer PsoID;
//    @ManyToOne
//    @JoinColumn(name = "SVC_ID")
//    private PsSvcItemVO psSvcItemVO;
    private Integer SvcId;
//    @ManyToOne
//    @JoinColumn(name = "SVC_PRICE")
//    private PsSvcVO psSvcVO;
    private Integer SvcPrice;
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        PsOddVO psOddVO = (PsOddVO) o;
//        return Objects.equals(psOrderVO, psOddVO.psOrderVO) && Objects.equals(psSvcItemVO, psOddVO.psSvcItemVO) && Objects.equals(psSvcVO, psOddVO.psSvcVO);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(psOrderVO, psSvcItemVO, psSvcVO);
//    }
}
