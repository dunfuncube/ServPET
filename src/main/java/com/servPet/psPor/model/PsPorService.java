package com.servPet.psPor.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PsPorService")
public class PsPorService {

    @Autowired
    private PsPorRepository repository;

    // 新增作品
    public void addPsPor(PsPorVO psPorVO) {
        repository.save(psPorVO);
    }

    // 刪除該美容師單一作品
    public void deletePictureByPsIdAndPicId(Integer psId, Integer picId) {
        if (repository.existsById(picId)) {
            repository.deletePictureByPsIdAndPicId(psId, picId);
        }
    }

    // 一鍵刪除所有作品集（根據美容師 ID）
    public void deleteAllByPsId(Integer psId) {
        repository.deleteAllByPsId(psId);
    }

    // 查詢所有美容師的所有作品
    public List<PsPorVO> getAll() {
        return repository.findAll();
    }

    // 根據美容師 ID 查詢作品集
    public List<PsPorVO> getPsPorByPsId(Integer psId) {
        return repository.findByPsId(psId);
    }

    // 輸入美容師 ID 查詢作品集
    public PsPorVO getOnePsPor(Integer psId) { // 通過這個工具，可以更加安全和清晰地處理返回值為空的情況
        Optional<PsPorVO> optional = repository.findById(psId);
//  return optional.get();
        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

}