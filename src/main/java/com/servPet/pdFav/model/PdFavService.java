package com.servPet.pdFav.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service ("pdFavService")
public class PdFavService {

    @Autowired
    PdFavRepository pdFavRepository;

    // 查詢特定會員的所有收藏商品
    public List<PdFavVO> getAllFavoritesByMebId(Integer mebId) {
        return pdFavRepository.findByMebVO_MebId(mebId);
    }

    // 查詢特定會員是否收藏了特定商品
    public Optional<PdFavVO> getFavoriteByMebIdAndPdId(Integer mebId, Integer pdId) {
        return pdFavRepository.findByMebVO_MebIdAndPdDetailsVO_PdId(mebId, pdId);
    }

    // 新增或更新收藏（狀態設為收藏）
    @Transactional
    public PdFavVO addFavorite(PdFavVO pdFav) {
        // 檢查該會員是否已經收藏該商品，如果已存在則只更新狀態
        Optional<PdFavVO> existingFav = pdFavRepository.findByMebVO_MebIdAndPdDetailsVO_PdId(pdFav.getMebVO().getMebId(), pdFav.getPdDetailsVO().getPdId());
        if (existingFav.isPresent()) {
            PdFavVO fav = existingFav.get();
            fav.setPdFavStatus("0"); // 0 表示收藏
            return pdFavRepository.save(fav);
        }
        return pdFavRepository.save(pdFav);
    }

    // 取消收藏 (狀態設為取消收藏)
    @Transactional
    public void cancelFavorite(Integer mebId, Integer pdId) {
        pdFavRepository.cancelFavorite(mebId, pdId);
    }

    // 恢復收藏 (狀態設為收藏)
    @Transactional
    public void restoreFavorite(Integer mebId, Integer pdId) {
        pdFavRepository.restoreFavorite(mebId, pdId);
    }

    // 刪除收藏
    @Transactional
    public void deleteFavorite(Integer pdFavId) {
        pdFavRepository.deleteById(pdFavId);
    }
    
    // 根據收藏 ID 查詢收藏商品
    public PdFavVO getFavoriteById(Integer pdFavId) {
        return pdFavRepository.findById(pdFavId).orElse(null);
    }

    // 根據複合查詢條件取得收藏商品列表
    public List<PdFavVO> getAllFavorites(Map<String, String[]> map) {
        // 此處可根據具體需求實作複合查詢邏輯
        return pdFavRepository.findAll();
    }
    
} 
