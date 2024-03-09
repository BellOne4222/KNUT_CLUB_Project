package com.KNUT_CLUB_Test.domain.adminservice.service;

import com.KNUT_CLUB_Test.domain.adminservice.Admin;
import com.KNUT_CLUB_Test.domain.adminservice.AdminMypageDTO;
import com.KNUT_CLUB_Test.domain.adminservice.UserDetailDTO;
import com.KNUT_CLUB_Test.domain.adminservice.repository.AdminRepository;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.web.form.AdminJoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    @Override
    public String Login(Admin admin) {
        return adminRepository.Login(admin);
    }

    @Override
    public String getAdminName(String id) {
        return adminRepository.getAdminName(id);
    }

    @Override
    public String getAdminClub(String id) {
        return adminRepository.getAdminClub(id);
    }

    @Override
    public String getAdminEmail(String id) {
        return adminRepository.getAdminEmail(id);
    }

    @Override
    public String getAdminPhone(String id) {
        return adminRepository.getAdminPhone(id);
    }

    @Override
    public List<Member> getMemberList(String club, String field, String query, int page) {
        return adminRepository.getMemberList(club, field, query, page);
    }

    @Override
    public List<Member> getPermissionList(String club, String field, String query, int page) {
        return adminRepository.getPermissionList(club, field, query, page);
    }

    @Override
    public List<UserDetailDTO> getUserDTO(int id) {
        return adminRepository.getUserDTO(id);
    }

    @Override
    public void getAdminUpdate(String clubName, String name, String email, String phone) {
        adminRepository.getAdminUpdate(clubName, name, email, phone);
    }

    @Override
    public int getMemberCount(String club, String field, String query) {
        return adminRepository.getMemberCount(club, field, query);
    }

    @Override
    public int getPermissionCount(String club, String field, String query) {
        return adminRepository.getPermissionCount(club, field, query);
    }

    @Override
    public int delMemberAll(int[] ids) {
        return adminRepository.delMemberAll(ids);
    }

    @Override
    public int delNonMemberAll(int[] ids) {
        return adminRepository.delNonMemberAll(ids);
    }

    @Override
    public boolean getJoin(AdminJoinForm joinForm) {
        return adminRepository.getJoin(joinForm);
    }

    @Override
    public List<AdminMypageDTO> getClubProfile(String clubId) {
        return adminRepository.getClubProfile(clubId);
    }

    @Override
    public void cancelClub(String id) {
        adminRepository.cancelClub(id);
    }

    @Override
    public void permissionUser(int id) {
        adminRepository.permissionUser(id);
    }

    @Override
    public void uploadProfileAdmin(String file, String id) {
        adminRepository.uploadProfileAdmin(file, id);
    }
}
