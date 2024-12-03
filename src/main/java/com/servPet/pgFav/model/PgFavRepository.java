package com.servPet.pgFav.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PgFavRepository extends JpaRepository<PgFavVO, Integer> {
	
	// 查詢特定會員的所有收藏美容師
    List<PgFavVO> findByMebId_MebId(Integer mebId);

    // 查詢特定會員是否收藏了特定美容師
    Optional<PgFavVO> findByMebVO_MebIdAndPgVO_PgId(Integer mebId, Integer pgId);

    // 新增收藏（使用 JpaRepository 內建的 save 方法）

    // 取消收藏 (收藏狀態： 0-收藏、1-取消收藏)
    @Transactional
    @Modifying
    @Query(value = "UPDATE PET_GROOMER_FAVORITE SET PG_FAV_STATUS = '1' WHERE MEB_ID = ?1 AND PG_ID = ?2", nativeQuery = true)
    void cancelFavorite(Integer mebId, Integer pgId);

    // 恢復收藏
    @Transactional
    @Modifying
    @Query(value = "UPDATE PET_GROOMER_FAVORITE SET PG_FAV_STATUS = '0' WHERE MEB_ID = ?1 AND PG_ID = ?2", nativeQuery = true)
    void restoreFavorite(Integer mebId, Integer pgId);
}

