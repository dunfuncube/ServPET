package com.servPet.admin.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servPet.admin.hibernateUtilCompositeQuery.HibernateUtil_CompositeQuery_admin;


@Service("adminService")
public class AdminService {

    @Autowired
    AdminRepository adminrepository;

    @Autowired
    private SessionFactory sessionFactory;

    // 新增管理員
    public void addAdmin(AdminVO adminVO) {
        adminrepository.save(adminVO);  // 使用 JPA adminrepository 進行儲存
    }

    // 更新管理員
    public void updateAdmin(AdminVO adminVO) {
        adminrepository.save(adminVO);  // 使用 JPA adminrepository 進行儲存
    }

    // 刪除管理員
//    public void deleteAdmin(Integer adminId) {
//        if (adminrepository.existsById(adminId)) {
//            adminrepository.deleteByadminId(adminId);  // 根據 adminId 刪除
//        }
//    }

    // 根據 adminId 查詢單一管理員
    public AdminVO getOneAdmin(Integer adminId) {
        Optional<AdminVO> optional = adminrepository.findById(adminId);
        return optional.orElse(null);  // 如果存在，返回該管理員，否則返回 null
    }

    // 查詢所有管理員資料
    public List<AdminVO> getAll() {
        return adminrepository.findAll();  // 返回所有管理員資料
    }

    // 複合查詢所有管理員資料
    public List<AdminVO> getAll(Map<String, String[]> map) {
        return HibernateUtil_CompositeQuery_admin.getAllC(map, sessionFactory.openSession());
    }
    
    //登入頁面邏輯資料
    public AdminVO login(String adminAcc, String adminPwd) {
        AdminVO admin = (AdminVO) adminrepository.findByadminAcc(adminAcc);

        if (admin != null && admin.getAdminPwd().equals(adminPwd)) {
            return admin; // 驗證成功，返回管理員資料
        } else {
            return null; // 登入失敗，返回 null
        }
    }
}