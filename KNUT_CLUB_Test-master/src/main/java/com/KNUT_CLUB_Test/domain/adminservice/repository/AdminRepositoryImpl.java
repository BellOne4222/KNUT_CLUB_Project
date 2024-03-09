package com.KNUT_CLUB_Test.domain.adminservice.repository;

import com.KNUT_CLUB_Test.domain.adminservice.Admin;
import com.KNUT_CLUB_Test.domain.adminservice.AdminMypageDTO;
import com.KNUT_CLUB_Test.domain.adminservice.UserDetailDTO;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.web.form.AdminJoinForm;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository{

    @Override
    public String Login(Admin admin) {

        String sql = "SELECT clubId FROM ADMIN WHERE clubId = ? and password = ?";

        String id = "";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, admin.getClubId());
            pst.setString(2, admin.getPassword());
            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getString("clubId");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return id;
    }

    @Override
    public String getAdminName(String id) {
        String name = "";

        String sql = "SELECT name FROM ADMIN WHERE clubId = ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            if(rs.next()) {
                name = rs.getString("name");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return name;
    }

    @Override
    public String getAdminClub(String id) {
        String club = "";

        String sql = "SELECT clubName FROM ADMIN WHERE clubId = ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            if(rs.next()) {
                club = rs.getString("clubName");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return club;
    }

    @Override
    public String getAdminEmail(String id) {
        String email = "";

        String sql = "SELECT email FROM ADMIN WHERE clubId = ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            if(rs.next()) {
                email = rs.getString("email");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return email;
    }

    @Override
    public String getAdminPhone(String id) {
        String phone = "";

        String sql = "SELECT phone FROM ADMIN WHERE clubId = ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            if(rs.next()) {
                phone = rs.getString("phone");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return phone;
    }

    @Override
    public List<Member> getMemberList(String club, String field, String query, int page) {

        List<Member> memberList = new ArrayList<>();

        String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS n, MEMBER.* " +
                "FROM MEMBER, (SELECT @ROWNUM := ?)TMP WHERE club = ? AND grade = 1 AND " + field + " LIKE ? limit ?, 10;";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, (page - 1) * 10);
            pst.setString(2, club);
            pst.setString(3, "%" + query + "%");
            pst.setInt(4, (page - 1) * 10);

            rs = pst.executeQuery();

            while(rs.next()) {
                int n = rs.getInt("n");
                int num = rs.getInt("num");
                String name  = rs.getString("name");
                String studentID = rs.getString("studentID");
                String department = rs.getString("department");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                boolean grade = rs.getBoolean("grade");

                Member member = new Member(
                        n
                        , num
                        , name
                        , studentID
                        , department
                        , phone
                        , gender
                        , grade
                );
                memberList.add(member);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return memberList;
    }

    @Override
    public List<Member> getPermissionList(String club, String field, String query, int page) {
        List<Member> permissionList = new ArrayList<>();

        String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS n, MEMBER.* " +
                "FROM MEMBER, (SELECT @ROWNUM := ?)TMP WHERE club = ? AND grade = 0 AND " + field + " LIKE ? limit ?, 10;";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, (page - 1) * 10);
            pst.setString(2, club);
            pst.setString(3, "%" + query + "%");
            pst.setInt(4, (page - 1) * 10);

            rs = pst.executeQuery();

            while(rs.next()) {
                int n = rs.getInt("n");
                int num = rs.getInt("num");
                String name  = rs.getString("name");
                String studentID = rs.getString("studentID");
                String department = rs.getString("department");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                boolean grade = rs.getBoolean("grade");

                Member member = new Member(
                        n
                        , num
                        , name
                        , studentID
                        , department
                        , phone
                        , gender
                        , grade
                );
                permissionList.add(member);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return permissionList;
    }

    @Override
    public List<UserDetailDTO> getUserDTO(int id) {

        List<UserDetailDTO> profile = new ArrayList<>();

        String sql = "select num, name, studentId, email ,phone, department, birth, address, img from member where num = ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            while (rs.next()) {
                int num = rs.getInt("num");
                String name = rs.getString("name");
                String studentId = rs.getString("studentId");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String department = rs.getString("department");
                String birth = rs.getString("birth");
                String address = rs.getString("address");
                String img = rs.getString("img");


                UserDetailDTO dto = new UserDetailDTO (
                        num
                        , name
                        , studentId
                        , email
                        , phone
                        , department
                        , birth
                        , address
                        , img
                );
                profile.add(dto);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return profile;
    }

    @Override
    public void getAdminUpdate(String clubName, String name, String email, String phone) {

        String sql = "UPDATE admin SET clubName = ?, name = ?, email = ?, phone = ?";

        Connection conn = null;
        PreparedStatement pst = null;


        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, clubName);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, phone);

            int rs = pst.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    @Override
    public int getMemberCount(String club, String field, String query) {
        int count = 0;

        String sql = "SELECT COUNT(num) as count FROM MEMBER WHERE club = ? and grade = 1";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, club);
            rs = pst.executeQuery();

            if(rs.next())
                count = rs.getInt("count");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return count;
    }

    @Override
    public int getPermissionCount(String club, String field, String query) {
        int count = 0;

        String sql = "SELECT COUNT(num) as count FROM MEMBER WHERE club = ? and grade = 0";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, club);
            rs = pst.executeQuery();

            if(rs.next())
                count = rs.getInt("count");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return count;
    }

    @Override
    public int delMemberAll(int[] ids) {
        int result = 0;

        String params = "";

        for (int i=0; i<ids.length; i++) {
            params += ids[i];
            if(i<ids.length-1)
                params += ",";
        }

        String sql = "UPDATE MEMBER SET club = '', motive = '', grade = 0 WHERE num IN ("+params+")";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            st = conn.createStatement();

            result = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (st != null)
                    st.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return result;
    }

    @Override
    public int delNonMemberAll(int[] ids) {
        int result = 0;

        String params = "";

        for (int i=0; i<ids.length; i++) {
            params += ids[i];
            if(i<ids.length-1)
                params += ",";
        }

        String sql = "UPDATE MEMBER SET club = '', motive = '' WHERE num IN ("+params+")";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            st = conn.createStatement();

            result = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (st != null)
                    st.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return result;
    }

    @Override
    public boolean getJoin(AdminJoinForm joinForm) {

        List<AdminJoinForm> adminList = new ArrayList<>();
        boolean check = false;

        String sql = "INSERT INTO ADMIN(clubId, clubName, password, name, email, phone, permission)" +
                "values (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pst = null;
        int rs = 0;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, joinForm.getClubId());
            pst.setString(2, joinForm.getClubName());
            pst.setString(3, joinForm.getPassword());
            pst.setString(4, joinForm.getName());
            pst.setString(5, joinForm.getEmail());
            pst.setString(6, joinForm.getPhone());
            pst.setBoolean(7, false);

            if (joinForm.getClubId().equals("") || joinForm.getPassword().equals("") || joinForm.getName().equals("") ||
                joinForm.getEmail().equals("") || joinForm.getPhone().equals("")) {
                check = false;
            }
            else {
                rs = pst.executeUpdate();
                adminList.add(joinForm);
                check =  true;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return check;
    }

    @Override
    public List<AdminMypageDTO> getClubProfile(String clubId) {

        List<AdminMypageDTO> profile = new ArrayList<>();

        String sql = "select clubName, name, email, phone, img from admin where clubId = ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, clubId);

            rs = pst.executeQuery();

            while (rs.next()) {
                String clubName = rs.getString("clubName");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String img = rs.getString("img");


                AdminMypageDTO AdminMypageDTO = new AdminMypageDTO(
                        clubName
                        , name
                        , email
                        , phone
                        , img
                );
                profile.add(AdminMypageDTO);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return profile;
    }

    @Override
    public void cancelClub(String id) {

        List<Member> list = new ArrayList<>();

        String sql = "DELETE FROM admin WHERE clubId = ?";

        Connection conn = null;
        PreparedStatement pst = null;


        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            int rs = pst.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void permissionUser(int id) {
        String sql = "UPDATE member SET grade = 1 where num = ?";

        Connection conn = null;
        PreparedStatement pst = null;


        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            int rs = pst.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void uploadProfileAdmin(String file, String id) {
        String sql = "UPDATE ADMIN SET img = ? WHERE clubId = ?";
        Connection conn = null;
        PreparedStatement pst = null;


        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, file);
            pst.setString(2, id);

            int rs = pst.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
