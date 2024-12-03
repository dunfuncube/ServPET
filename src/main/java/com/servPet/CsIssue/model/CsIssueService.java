package com.servPet.CsIssue.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("csIssueService")
public class CsIssueService {

    @Autowired
    private CsIssueRepository repository;

    @Autowired
    private PlatformAdminRepository adminRepository;

    @Transactional
    public CsIssueVO addCsIssue(CsIssueVO csIssueVO) {
        if (csIssueVO.getCsQaId() == null) {
            if (csIssueVO.getQaContent() != null && (csIssueVO.getReContent() == null || csIssueVO.getReContent().isEmpty())) {
                csIssueVO.setQaSta("0"); // 新問題未回覆
            }
        } else if (csIssueVO.getReContent() != null) {
            csIssueVO.setQaSta("1"); // 已回覆
        }

        if (csIssueVO.getCsQaId() == null && csIssueVO.getQaDate() == null) {
            csIssueVO.setQaDate(LocalDateTime.now());
        }

        return repository.save(csIssueVO);
    }

    public CsIssueVO getOneCsIssue(Integer csQaId) {
        return repository.findById(csQaId)
                .orElseThrow(() -> new RuntimeException("找不到指定的問題編號: " + csQaId));
    }

    public List<CsIssueVO> getAll() {
        return repository.findAll();
    }

    public List<CsIssueVO> getByStatus(String status) {
        return repository.findByQaSta(status);
    }

    public Page<CsIssueVO> getAllWithPagination(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<CsIssueVO> getByMember(MebVO mebId) {
        return repository.getByMember(mebId);
    }

    public List<CsIssueVO> getAllOrderedByStatusAndMemberId() {
        return repository.findAllOrderByStatusAndMemberId();
    }

    public PlatformAdminVO getAdminById(Integer adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("找不到管理員編號: " + adminId));
    }
}
