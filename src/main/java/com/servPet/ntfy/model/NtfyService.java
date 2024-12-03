package com.servPet.ntfy.model;

import com.servPet.ntfy.model.NtfyVO;
import com.servPet.ntfy.model.NtfyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NtfyService {

    @Autowired
    private NtfyRepository ntfyRepository;


    // 獲取會員的所有通知
    public List<NtfyVO> getAllNotificationsForMember(Integer mebId) {
        return ntfyRepository.findByMebVO_MebIdOrderByDateDesc(mebId);
    }

    // 獲取會員的未讀通知
    public List<NtfyVO> getUnreadNotificationsForMember(Integer mebId) {
        return ntfyRepository.findByMebVO_MebIdAndStatusOrderByDateDesc(mebId, 0);
    }

    // 標記通知為已讀
    @Transactional
    public void markNotificationAsRead(Integer ntfyMgmtId) {
        NtfyVO notification = ntfyRepository.findById(ntfyMgmtId)
            .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(1); // 設置為已讀
        ntfyRepository.save(notification);
    }
    
    		//		=========================== 後台操作 =========================== //
    
    
    // 獲取所有通知（用於管理員）
    public List<NtfyVO> getAllNotifications() {
        return ntfyRepository.findAllByOrderByDateDesc();
    }

    
    @Transactional
    public NtfyVO createNotification(NtfyVO ntfy) {
        System.out.println("Creating notification: " + ntfy);
        if (ntfy.getDate() == null) {
            ntfy.setDate(LocalDateTime.now());
        }
        if (ntfy.getStatus() == null) {
            ntfy.setStatus(0); // 設置為未讀
        }
        NtfyVO savedNtfy = ntfyRepository.save(ntfy);
        System.out.println("Saved notification: " + savedNtfy);
        return savedNtfy;
    }
    
    
    // 更新通知
    @Transactional
    public NtfyVO updateNotification(NtfyVO ntfy) {
        NtfyVO existingNtfy = ntfyRepository.findById(ntfy.getNtfyMgmtId())
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        
        existingNtfy.setTitle(ntfy.getTitle());
        existingNtfy.setContent(ntfy.getContent());
        existingNtfy.setStatus(ntfy.getStatus());
        
        return ntfyRepository.save(existingNtfy);
    }

    // 刪除通知
    @Transactional
    public void deleteNotification(Integer ntfyMgmtId) {
        if (!ntfyRepository.existsById(ntfyMgmtId)) {
            throw new RuntimeException("通知未找到，無法刪除");
        }
        ntfyRepository.deleteById(ntfyMgmtId);
    }

    // 獲取單個通知
    public NtfyVO getNotificationById(Integer ntfyMgmtId) {
        return ntfyRepository.findById(ntfyMgmtId).orElse(null);
    }
}

