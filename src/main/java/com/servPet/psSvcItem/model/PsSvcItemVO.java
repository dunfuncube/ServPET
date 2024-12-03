package com.servPet.psSvcItem.model;

import com.servPet.psOdd.model.PsOddVO;
import com.servPet.psOrder.model.PsOrderVO;
import com.servPet.psSvc.model.PsSvcVO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PET_SITTER_SERVICE_ITEM")

public class PsSvcItemVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SVC_ID", updatable = false)
    private Integer svcId;

//    @OneToMany(mappedBy = "psSvcItemVO")
//    private Set<PsOrderVO> psOrderVO = new HashSet<PsOrderVO>();
//    @OneToMany(mappedBy = "psSvcItemVO")
//    private Set<PsOddVO> psOddVO = new HashSet<PsOddVO>();
//    @OneToMany(mappedBy = "psSvcItemVO")
//    private Set<PsSvcVO> psSvcVO = new HashSet<PsSvcVO>();


    @Column(name = "SVC_DESCR")
    private String svcDescr;

    @NotEmpty(message = "服務名稱請勿空白")
    @Column(name = "SVC_NAME")
    private String svcName;

}
