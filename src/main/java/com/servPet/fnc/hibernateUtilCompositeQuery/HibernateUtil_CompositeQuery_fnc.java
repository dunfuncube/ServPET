package com.servPet.fnc.hibernateUtilCompositeQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import java.util.*;

import com.servPet.fnc.model.FncVO;

public class HibernateUtil_CompositeQuery_fnc {

    // This method generates a Predicate based on the column name and value
    public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<FncVO> root, String columnName, String value) {
        Predicate predicate = null;

        // Handling different column types for dynamic query
        if ("fncId".equals(columnName)) {  // For Integer type
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
        } else if ("fncName".equals(columnName)) {  // For String type
            predicate = builder.like(root.get(columnName), "%" + value + "%");
        } else if ("fncDes".equals(columnName)) {  // For String type
            predicate = builder.like(root.get(columnName), "%" + value + "%");
        } else if ("fncAcc".equals(columnName)) {  // For String type
            predicate = builder.like(root.get(columnName), "%" + value + "%");
        } else if ("fncPwd".equals(columnName)) {  // For String type
            predicate = builder.like(root.get(columnName), "%" + value + "%");
        } else if ("upFiles".equals(columnName)) {  // For byte[] type (binary file data), no specific condition, can be left out in queries
            // You could add logic if needed, but generally file data wouldn't be used in this way in queries
        }

        return predicate;
    }

    // This method generates the dynamic query based on the given map of parameters
    @SuppressWarnings("unchecked")
    public static List<FncVO> getAllC(Map<String, String[]> map, Session session) {
        Transaction tx = session.beginTransaction();
        List<FncVO> list = null;
        try {
            // 1. Create CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // 2. Create CriteriaQuery
            CriteriaQuery<FncVO> criteriaQuery = builder.createQuery(FncVO.class);
            // 3. Create Root
            Root<FncVO> root = criteriaQuery.from(FncVO.class);

            // List to hold all the query predicates (conditions)
            List<Predicate> predicateList = new ArrayList<Predicate>();

            // Retrieve the keys from the map (which represent the columns to filter by)
            Set<String> keys = map.keySet();
            int count = 0;

            // Iterate over the map to create the predicates based on the filter criteria
            for (String key : keys) {
                String value = map.get(key)[0];
                if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
                    count++;
                    // Add the predicate to the list based on the column and value
                    predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
                    System.out.println("Number of filter conditions sent: count = " + count);
                }
            }

            System.out.println("Predicate list size: " + predicateList.size());

            // Apply the conditions to the CriteriaQuery
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            // Default order by 'fncId' ascending
            criteriaQuery.orderBy(builder.asc(root.get("fncId")));

            // 4. Create and execute the query
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
