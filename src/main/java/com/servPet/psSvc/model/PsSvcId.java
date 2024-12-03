package com.servPet.psSvc.model;

import com.servPet.ps.model.PsVO;
import com.servPet.psSvcItem.model.PsSvcItemVO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Embeddable
public class PsSvcId implements Serializable {

    // Getters, setters, equals, hashCode methods
    @ManyToOne
    @JoinColumn(name = "PS_ID")
    private PsVO psVO;

    @ManyToOne
    @JoinColumn(name = "SVC_ID")
    private PsSvcItemVO psSvcItemVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsSvcId psSvcId = (PsSvcId) o;
        return Objects.equals(psVO, psSvcId.psVO) &&
                Objects.equals(psSvcItemVO, psSvcId.psSvcItemVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(psVO, psSvcItemVO);
    }
}