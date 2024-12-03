package com.servPet.meb.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MebService {

    @Autowired
    private MebRepository mebRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 新增會員
    public void addMember(MebVO member) {
        System.out.println("嘗試新增會員：" + member);
        if (mebRepository.findByMebMail(member.getMebMail()).isPresent()) {
            throw new IllegalArgumentException("Email 已被使用");
        }
        mebRepository.save(member);
        System.out.println("會員新增成功：" + member);
    }


    // 查詢所有會員
    public List<MebVO> getAllMembers() {
        return mebRepository.findAll();
    }

    // 根據 ID 查詢會員
    public Optional<MebVO> getMemberById(Integer id) {
        return mebRepository.findById(id);
    }

    // 根據 Email 查詢會員
    public Optional<MebVO> getMemberByEmail(String email) {
        return mebRepository.findByMebMail(email);
    }

    // 更新會員資訊
    public MebVO updateMember(MebVO member) {
        validateMember(member);

        if (!mebRepository.existsById(member.getMebId())) {
            throw new MemberNotFoundException("會員不存在");
        }
        return mebRepository.save(member);
    }

    // 刪除會員
    @Transactional
    public void deleteMember(Integer id) {
        if (!mebRepository.existsById(id)) {
            throw new MemberNotFoundException("會員不存在");
        }
        mebRepository.deleteById(id);
    }

    // 驗證會員對象
    private void validateMember(MebVO member) {
        if (member == null || member.getMebMail() == null || member.getMebMail().isEmpty()) {
            throw new IllegalArgumentException("會員資料不完整，Email 不能為空");
        }
        if (member.getMebPwd() == null || member.getMebPwd().isEmpty()) {
            throw new IllegalArgumentException("會員資料不完整，密碼不能為空");
        }
        
    }    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
