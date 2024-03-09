package com.KNUT_CLUB_Test.domain.memberservice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageService {

//    public List<Member> getMemberList(String club) {
//
//        List<Member> list = new ArrayList<>();
//
//        String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS n, MEMBER.* " +
//                "FROM MEMBER, (SELECT @ROWNUM := 0)TMP WHERE club = ? and authority = 3";
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
//            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, club);
//            rs = pst.executeQuery();
//
//            while(rs.next()) {
//                int n = rs.getInt("n");
//                int num = rs.getInt("num");
//                String name  = rs.getString("name");
//                String studentID = rs.getString("studentID");
//                String department = rs.getString("department");
//                String phone = rs.getString("phone");
//                club = rs.getString("club");
//                String motive = rs.getString("motive");
//
//                Member member = new Member(
//                        n
//                        , num
//                        , name
//                        , department
//                        , studentID
//                        , phone
//                        , club
//                        , motive
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

//    public List<Member> getPermissionList(String club) {
//
//        List<Member> list = new ArrayList<>();
//
//        String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS n, MEMBER.* " +
//                "FROM MEMBER, (SELECT @ROWNUM := 0)TMP WHERE club = ? and authority = 4";
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
//            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, club);
//            rs = pst.executeQuery();
//
//            while(rs.next()) {
//                int n = rs.getInt("n");
//                int num = rs.getInt("num");
//                String name  = rs.getString("name");
//                String studentID = rs.getString("studentID");
//                String department = rs.getString("department");
//                String phone = rs.getString("phone");
//                club = rs.getString("club");
//                String motive = rs.getString("motive");
//
//                Member member = new Member(
//                        n
//                        , num
//                        , name
//                        , studentID
//                        , department
//                        , phone
//                        , club
//                        , motive
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

    public void joinClub(String id, String club, String motive) {

        String sql = "UPDATE MEMBER SET club = ?, motive = ? WHERE studentID = ?";

        Connection conn = null;
        PreparedStatement pst = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, club);
            pst.setString(2, motive);
            pst.setString(3, id);
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

    public String getClubName(int num) {
        String name = "";

        String sql = "SELECT name FROM PROMOTION WHERE num = ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, num);
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

//    public List<Member> getMemberListDetail(int num) {
//
//        List<Member> list = new ArrayList<>();
//
//        String sql = "SELECT * FROM MEMBER WHERE num= ? ";
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
//            pst.setInt(1, num);
//
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String department = rs.getString("department");
//                String studentID = rs.getString("studentID");
//                String phone = rs.getString("phone");
//                String club = rs.getString("club");
//                String motive = rs.getString("motive");
//
//                Member member = new Member(
//                        num
//                        ,name
//                        ,department
//                        ,studentID
//                        ,phone
//                        ,club
//                        ,motive
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

    public int permissionMemberAll(int[] ids) {
        int result = 0;

        String params = "";

        for (int i = 0; i < ids.length; i++) {
            params += ids[i];
            if (i < ids.length - 1)
                params += ",";
        }

        String sql = "UPDATE MEMBER SET authority = 3 WHERE num IN ("+params+")";

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

    public int delMemberAll(int[] ids) {
        int result = 0;

        String params = "";

        for (int i=0; i<ids.length; i++) {
            params += ids[i];
            if(i<ids.length-1)
                params += ",";
        }

        String sql = "UPDATE MEMBER SET club = '', motive = '', authority = 4 WHERE num IN ("+params+")";

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

    public int delNonMemberAll(int[] ids) {
        int result = 0;

        String params = "";

        for (int i=0; i<ids.length; i++) {
            params += ids[i];
            if(i<ids.length-1)
                params += ",";
        }

        String sql = "UPDATE MEMBER SET club = '', motive = '', authority = 4 WHERE num IN ("+params+")";

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
}
