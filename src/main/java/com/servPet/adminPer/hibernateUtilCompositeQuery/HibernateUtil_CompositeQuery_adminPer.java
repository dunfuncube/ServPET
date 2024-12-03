package com.servPet.adminPer.hibernateUtilCompositeQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import java.util.*;

import com.servPet.adminPer.model.AdminPerVO;

public class HibernateUtil_CompositeQuery_adminPer {

    // This method generates a Predicate based on the column name and value
    public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<AdminPerVO> root, String columnName, String value) {
        Predicate predicate = null;

        // Handling different column types for dynamic query
        if ("adminPerId".equals(columnName)) {  // For Integer type
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
        } else if ("adminId".equals(columnName)) {  // For Integer type (AdminVO)
            // Assuming AdminPerVO has a ManyToOne relation with AdminVO, so we need to query through the AdminVO
            predicate = builder.equal(root.get("adminVO").get("adminId"), Integer.valueOf(value));
        } else if ("fncId".equals(columnName)) {  // For Integer type (FncVO)
            // Assuming AdminPerVO has a ManyToOne relation with FncVO, so we need to query through the FncVO
            predicate = builder.equal(root.get("fncVO").get("fncId"), Integer.valueOf(value));
        }

        return predicate;
    }

    // This method generates the dynamic query based on the given map of parameters
    @SuppressWarnings("unchecked")
    public static List<AdminPerVO> getAllC(Map<String, String[]> map, Session session) {
        Transaction tx = session.beginTransaction();
        List<AdminPerVO> list = null;
        try {
            // 1. Create CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // 2. Create CriteriaQuery
            CriteriaQuery<AdminPerVO> criteriaQuery = builder.createQuery(AdminPerVO.class);
            // 3. Create Root
            Root<AdminPerVO> root = criteriaQuery.from(AdminPerVO.class);

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
            // Default order by 'adminPerId' ascending
            criteriaQuery.orderBy(builder.asc(root.get("adminPerId")));

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