package com.servPet.ps.model;

import com.servPet.ps.model.PsVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsRepository extends JpaRepository<PsVO, Integer> {
    List<PsVO> findByPsIdOrPsNameContainingOrPsAreaContainingOrTotalStarsOrRatingTimesOrReportTimes(
            Integer psId, String psName, String psArea, Integer totalStars, Integer ratingTimes, Integer reportTimes
    );
}
