package com.servPet.psCompl.model;

import com.servPet.psCompl.hibernateUtilCompositeQuery.HibernateUtil_CompositeQuery_psCompl;
import com.servPet.psCompl.model.PsComplRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("psComplService")
public class PsComplService {

    @Autowired
    private PsComplRepository psComplRepository;

    @Autowired
    private SessionFactory sessionFactory;

    // 新增檢舉 (Add a new complaint)
    public void addPsCompl(PsComplVO psComplVO) {
        psComplRepository.save(psComplVO);  // 使用 JPA psComplRepository 進行儲存
    }

    // 處理文件上傳 (Handle file upload)
    public void handleFileUpload(PsComplVO psComplVO, MultipartFile[] files) throws IOException {
        // 檢查每個檔案並將它們存儲到對應的屬性中
        for (int i = 0; i < files.length; i++) {
            if (files[i] != null && !files[i].isEmpty()) {
                byte[] fileBytes = files[i].getBytes();  // 將檔案轉換為 byte[]
                switch (i) {
                    case 0:
                        psComplVO.setPsComplUpfiles1(fileBytes);
                        break;
                    case 1:
                        psComplVO.setPsComplUpfiles2(fileBytes);
                        break;
                    case 2:
                        psComplVO.setPsComplUpfiles3(fileBytes);
                        break;
                    case 3:
                        psComplVO.setPsComplUpfiles4(fileBytes);
                        break;
                    default:
                        break;  // 可處理更多檔案
                }
            }
        }
    }

    // 更新檢舉 (Update a complaint)
    @Transactional
    public void updatePsCompl(PsComplVO psComplVO) {
        // 确保数据库中相关的 psComplDate 字段在更新时不丢失
        psComplRepository.save(psComplVO);  // 使用 JPA psComplRepository 進行儲存
    }

    // 根據 PS_COMPL_ID 查詢單一檢舉 (Get one complaint by PS_COMPL_ID)
    public PsComplVO getOnePsCompl(Integer psComplId) {
        Optional<PsComplVO> optional = psComplRepository.findById(psComplId);
        return optional.orElse(null);  // 如果存在，返回該檢舉，否則返回 null
    }

    // 查詢所有檢舉 (Get all complaints)
    public List<PsComplVO> getAll() {
        return psComplRepository.findAll();  // 返回所有檢舉資料
    }

    // 根據保母編號查詢檢舉 (Get complaints by PS_ID)
    public List<PsComplVO> getByPsId(Integer psId) {
        return psComplRepository.findByPsId(psId);  // 根據保母編號查詢檢舉
    }

    // 根據會員編號查詢檢舉 (Get complaints by MEB_ID)
    public List<PsComplVO> getByMebId(Integer mebId) {
        return psComplRepository.findByMebId(mebId);  // 根據會員編號查詢檢舉
    }

    // 根據檢舉處理狀態查詢檢舉 (Get complaints by PS_COMPL_STATUS)
    public List<PsComplVO> getByPsComplStatus(String psComplStatus) {
        return psComplRepository.findByPsComplStatus(psComplStatus);  // 根據檢舉狀態查詢檢舉
    }

    // 根據檢舉日期範圍查詢檢舉 (Get complaints by date range)
    public List<PsComplVO> getByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return psComplRepository.findByDateRange(startDate, endDate);  // 根據日期範圍查詢
    }

    // 根據檢舉描述模糊查詢檢舉 (Get complaints by psComplDes like)
    public List<PsComplVO> getByPsComplDesLike(String psComplDes) {
        return psComplRepository.findByPsComplDesLike(psComplDes);  // 根據檢舉描述模糊查詢
    }

    // 複合查詢檢舉資料 (Composite query for complaints)
    public List<PsComplVO> getAll(Map<String, String[]> map) {
        // 你的查詢邏輯，這裡是範例，實際應根據你的需求來查詢
        return psComplRepository.findAll();  // 假設你有一個 repository 處理資料查詢
    }
}