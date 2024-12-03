package com.servPet.ps.model;


import com.servPet.ps.model.PsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PsService")
public class PsService {

    @Autowired
    private PsRepository repository;

    // 新增
    public void addPs(PsVO psVO) {
        repository.save(psVO);
    }

    // 修改
    public void update(PsVO psVO) {
        repository.save(psVO);
    }

    // 刪除
    public void deletePs(Integer psId) {
        if (repository.existsById(psId)) {
            repository.deleteById(psId);
        }
    }

    // 查詢所有
    public List<PsVO> getAll() {
        return repository.findAll();
    }

    // 根據 ID 查詢
    public PsVO getOnePs(Integer psId) {
        Optional<PsVO> optional = repository.findById(psId);
        return optional.orElse(null);
    }

    // 根據條件查詢
    public List<PsVO> searchPs(Integer psId, String psName, String psArea, Integer totalStars, Integer ratingTimes, Integer reportTimes) {
        return repository.findByPsIdOrPsNameContainingOrPsAreaContainingOrTotalStarsOrRatingTimesOrReportTimes(
                psId, psName, psArea, totalStars, ratingTimes, reportTimes
        );
    }
}
