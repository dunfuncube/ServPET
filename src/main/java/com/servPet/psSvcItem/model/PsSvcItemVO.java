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

    //服務項目編號多對一
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PSO_ID", nullable = false) // 指定外鍵列名為 PSO_ID
    private PsOrderVO psOrderVO;



    @Column(name = "SVC_DESCR")
    private String svcDescr;

    @NotEmpty(message = "服務名稱請勿空白")
    @Column(name = "SVC_NAME")
    private String svcName;

}
