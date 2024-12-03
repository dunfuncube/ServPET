package com.servPet.pgFav.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service ("pgFavService")
public class PgFavService {

    @Autowired
    PgFavRepository pgFavRepository;

    // 查詢特定會員的所有收藏美容師
    public List<PgFavVO> getAllFavoritesByMebId(Integer mebId) {
        return pgFavRepository.findByMebVO_MebId(mebId);
    }

    // 查詢特定會員是否收藏了特定美容師
    public Optional<PgFavVO> getFavoriteByMebIdAndPgId(Integer mebId, Integer pgId) {
        return pgFavRepository.findByMebVO_MebIdAndPgVO_PgId(mebId, pgId);
    }

    // 新增或更新收藏（狀態設為收藏）
    @Transactional
    public PgFavVO addFavorite(PgFavVO pgFav) {
        // 檢查該會員是否已經收藏該美容師，如果已存在則只更新狀態
        Optional<PgFavVO> existingFav = pgFavRepository.findByMebVO_MebIdAndPgVO_PgId(pgFav.getMebVO().getMebId(), pgFav.getPgVO().getPgId());
        if (existingFav.isPresent()) {
            PgFavVO fav = existingFav.get();
            fav.setPgFavStatus("0"); // 0 表示收藏
            return pgFavRepository.save(fav);
        }
        return pgFavRepository.save(pgFav);
    }

    // 取消收藏 (狀態設為取消收藏)
    @Transactional
    public void cancelFavorite(Integer mebId, Integer pgId) {
        pgFavRepository.cancelFavorite(mebId, pgId);
    }

    // 恢復收藏 (狀態設為收藏)
    @Transactional
    public void restoreFavorite(Integer mebId, Integer pgId) {
        pgFavRepository.restoreFavorite(mebId, pgId);
    }

    // 刪除收藏
    @Transactional
    public void deleteFavorite(Integer pgFavId) {
        pgFavRepository.deleteById(pgFavId);
    }
    
    // 根據收藏 ID 查詢收藏美容師
    public PgFavVO getFavoriteById(Integer pgFavId) {
        return pgFavRepository.findById(pgFavId).orElse(null);
    }

//    // 根據複合查詢條件取得收藏美容師列表
//    public List<PgFavVO> getAllFavorites(Map<String, String[]> map) {
//        // 此處可根據具體需求實作複合查詢邏輯
//        return pgFavRepository.findAll();
//    }
    
} 
