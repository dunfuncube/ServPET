package com.servPet.pdFav.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PdFavRepository extends JpaRepository<PdFavVO, Integer> {

    // 查詢特定會員的所有收藏商品
    List<PdFavVO> findByMebVO_MebId(Integer mebId);

    // 查詢特定會員是否收藏了特定商品
    Optional<PdFavVO> findByMebVO_MebIdAndPdDetailsVO_PdId(Integer mebId, Integer pdId);

    // 取消收藏 (狀態設為 1)
    @Transactional
    @Modifying
    @Query(value = "UPDATE PRODUCT_FAVORITE SET PD_FAV_STATUS = '1' WHERE MEB_ID = ?1 AND PD_ID = ?2", nativeQuery = true)
    void cancelFavorite(Integer mebId, Integer pdId);

    // 恢復收藏 (狀態設為 0)
    @Transactional
    @Modifying
    @Query(value = "UPDATE PRODUCT_FAVORITE SET PD_FAV_STATUS = '0' WHERE MEB_ID = ?1 AND PD_ID = ?2", nativeQuery = true)
    void restoreFavorite(Integer mebId, Integer pdId);
}