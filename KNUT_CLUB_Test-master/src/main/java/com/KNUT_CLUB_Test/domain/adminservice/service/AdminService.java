package com.KNUT_CLUB_Test.domain.adminservice.service;

import com.KNUT_CLUB_Test.domain.adminservice.Admin;
import com.KNUT_CLUB_Test.domain.adminservice.AdminMypageDTO;
import com.KNUT_CLUB_Test.domain.adminservice.UserDetailDTO;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.web.form.AdminJoinForm;

import java.util.List;

public interface AdminService {

    String Login(Admin admin);

    String getAdminName(String id);

    String getAdminClub(String id);

    String getAdminEmail(String id);

    String getAdminPhone(String id);

    List<Member> getMemberList(String club, String field, String query, int page);

    List<Member> getPermissionList(String club, String field, String query, int page);

    List<UserDetailDTO> getUserDTO(int id);

    void getAdminUpdate(String clubName,String name,String email,String phone);


    int getMemberCount(String club, String field, String query);

    int getPermissionCount(String club, String field, String query);

    int delMemberAll(int[] ids);

    int delNonMemberAll(int[] ids);

    boolean getJoin(AdminJoinForm joinForm);

    List<AdminMypageDTO> getClubProfile(String dto);

    void cancelClub(String id);

    void permissionUser(int id);

    void uploadProfileAdmin(String file, String id);
}
