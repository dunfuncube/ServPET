package com.servPet.psCompl.hibernateUtilCompositeQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.servPet.psCompl.model.PsComplVO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import java.util.*;

public class HibernateUtil_CompositeQuery_psCompl {

    // 這個方法根據欄位名稱和值生成對應的 Predicate
    public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<PsComplVO> root, String columnName, String value) {
        Predicate predicate = null;

        // 根據欄位名稱和類型生成查詢條件
        if ("psComplId".equals(columnName)) {  // Integer 類型
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
        } else if ("psId".equals(columnName)) {  // Integer 類型
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
        } else if ("mebId".equals(columnName)) {  // Integer 類型
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
        } else if ("psComplResult".equals(columnName)) {  // String 類型
            predicate = builder.like(root.get(columnName), "%" + value + "%");
        } else if ("psComplDes".equals(columnName)) {  // String 類型
            predicate = builder.like(root.get(columnName), "%" + value + "%");
        } else if ("psComplStatus".equals(columnName)) {  // String 類型
            predicate = builder.equal(root.get(columnName), value);
        }

        return predicate;
    }

    // 這個方法根據傳入的參數地圖生成動態查詢
    @SuppressWarnings("unchecked")
    public static List<PsComplVO> getAllC(Map<String, String[]> map, Session session) {
        Transaction tx = session.beginTransaction();
        List<PsComplVO> list = null;
        try {
            // 1. 創建 CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // 2. 創建 CriteriaQuery
            CriteriaQuery<PsComplVO> criteriaQuery = builder.createQuery(PsComplVO.class);
            // 3. 創建 Root
            Root<PsComplVO> root = criteriaQuery.from(PsComplVO.class);

            // 用來保存所有查詢條件的 Predicate 列表
            List<Predicate> predicateList = new ArrayList<Predicate>();

            // 從 map 中取出所有的過濾條件
            Set<String> keys = map.keySet();
            int count = 0;

            // 遍歷 map 中的每一個條件
            for (String key : keys) {
                String value = map.get(key)[0];
                if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
                    count++;
                    // 根據欄位名稱和值創建對應的 Predicate 並加入列表
                    predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
                    System.out.println("傳送過濾條件的數量: count = " + count);
                }
            }

            System.out.println("Predicate 列表大小: " + predicateList.size());

            // 將條件應用到 CriteriaQuery
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            // 默認按 psComplId 升序排序
            criteriaQuery.orderBy(builder.asc(root.get("psComplId")));

            // 4. 創建並執行查詢
            Query query = session.createQuery(criteriaQuery);
            list = query.getResultList();

            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null)
                tx.rollback();
            throw ex;
        } finally {
            session.close();
        }

        return list;
    }
}
