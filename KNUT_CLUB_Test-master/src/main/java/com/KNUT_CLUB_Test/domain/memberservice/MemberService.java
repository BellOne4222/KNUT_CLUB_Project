package com.KNUT_CLUB_Test.domain.memberservice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberService {

//    public List<Member> getJoin(String name, String studentID, String password, String department,
//                                String birth, String gender, String email, String phone,
//                                String address, String detailAddress) {
//
//        List<Member> list = new ArrayList<>();
//
//        String sql = "INSERT INTO MEMBER(name, studentID, password, department, birth, gender, email," +
//                " phone, address, detailAddress, authority) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 4)";
//
//        Connection conn = null;
//        PreparedStatement pst = null;
//        int rs = 0;
//
//        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
//        String dbID = "root";
//        String dbPassword = "root";
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, name);
//            pst.setString(2, studentID);
//            pst.setString(3, password);
//            pst.setString(4, department);
//            pst.setString(5, birth);
//            pst.setString(6, gender);
//            pst.setString(7, email);
//            pst.setString(8, phone);
//            pst.setString(9, address);
//            pst.setString(10, detailAddress);
//
//            rs = pst.executeUpdate();
//
//            Member member = new Member (
//                    name
//                    ,studentID
//                    ,password
//                    ,department
//                    ,birth
//                    ,gender
//                    ,email
//                    ,phone
//                    ,address
//                    ,detailAddress
//            );
//            list.add(member);
//
//
//
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                if (pst != null)
//                    pst.close();
//
//                if (conn != null)
//                    conn.close();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//        return list;
//    }

//    public List<Member> getMemberProfile(String studentID) {
//
//        List<Member> list = new ArrayList<>();
//
//        String sql = "SELECT name, email, studentID, department, club FROM MEMBER WHERE studentID = ?;";
//
//        Connection conn = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//
//        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
//        String dbID = "root";
//        String dbPassword = "root";
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, studentID);
//
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String department = rs.getString("department");
//                String club = rs.getString("club");
//                studentID = rs.getString("studentID");
//
//                Member member = new Member(
//                        name
//                        , email
//                        , studentID
//                        , department
//                        , club
//                );
//                list.add(member);
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//        finally {
//            try {
//                if (rs != null)
//                    rs.close();
//
//                if (pst != null)
//                    pst.close();
//
//                if (conn != null)
//                    conn.close();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//        return list;
//    }

    public String getMemberName(String id) {
        String name = "";

        String sql = "SELECT name FROM MEMBER WHERE studentID = ?";

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

    public String getMemberClub(String id) {
        String club = "";

        String sql = "SELECT club FROM MEMBER WHERE studentID = ?";

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
                club = rs.getString("club");
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

    public String getMemberAuthority(String id) {
        String authority = "";

        String sql = "SELECT authority FROM MEMBER WHERE studentID = ?";

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
                authority = rs.getString("authority");
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
        return authority;
    }

    public String getMemberDepartment(String id) {
        String department = "";

        String sql = "SELECT department FROM MEMBER WHERE studentID = ?";

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
                department = rs.getString("department");
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
        return department;
    }

    public String getMemberPhone(String id) {
        String phone = "";

        String sql = "SELECT phone FROM MEMBER WHERE studentID = ?";

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

    public int checkMember(String id) {
        int count = 0;

        String sql = "SELECT COUNT(studentID) as count FROM MEMBER WHERE studentID = ?";

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
                count = rs.getInt("count");
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
        return count;
    }

    public List<Member> updateMemberProfile(String name,String studentID,String email,String department,String club) {
        List<Member> list = new ArrayList<>();

        String sql = "UPDATE MEMBER SET name = ?, studentID = ?, email = ?, department = ?, club = ? WHERE studentID = ?";

        Connection conn = null;
        PreparedStatement pst = null;


        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, studentID);
            pst.setString(3, email);
            pst.setString(4, department);
            pst.setString(5, club);
            pst.setString(6, studentID);
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
        return list;
    }
}


