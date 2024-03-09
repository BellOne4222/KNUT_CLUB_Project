package com.KNUT_CLUB_Test.domain.clubservice.repository;

import com.KNUT_CLUB_Test.domain.clubservice.Club;
import com.KNUT_CLUB_Test.domain.clubservice.NewClubDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClubRepositoryImpl implements ClubRepository {
    @Override
    public List<Club> getClubList(String campus, String type, String cWord, String tWord) {
        List<Club> clubList = new ArrayList<>();

        String sql = "SELECT @ROWNUM := @ROWNUM +1 as n, PROMOTION.*"
                + " FROM PROMOTION, (SELECT @ROWNUM := 0)TMP WHERE " + campus + " Like ? and " + type + " LIKE ?";

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
            pst.setString(1, "%" + cWord + "%");
            pst.setString(2, "%" + tWord + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                int num = rs.getInt("num");
                String name = rs.getString("name");
                String img = rs.getString("img");

                Club club = new Club(
                        num
                        , name
                        , img
                );
                clubList.add(club);
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
        return clubList;

    }

    @Override
    public List<Club> getClubDetail(int num) {
        List<Club> clubList = new ArrayList<>();

        String sql = "SELECT * FROM PROMOTION WHERE num= ? ";

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
            pst.setInt(1, num);

            rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String promotion = rs.getString("promotion");
                String img = rs.getString("img");

                Club club = new Club(
                        name
                        , type
                        , promotion
                        , img
                );
                clubList.add(club);
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
        return clubList;
    }

    @Override
    public void joinClub(String id, String club, String motive) {

        String sql = "UPDATE MEMBER SET club = ?, motive = ?, grade = 0 WHERE studentID = ?";

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

            System.out.println("club = " + club);

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
    }

    @Override
    public boolean createClub(NewClubDTO dto) {

        List<NewClubDTO> list = new ArrayList<>();
        boolean check = false;

        String sql = "insert into promotion (campus, type, name, activity, promotion) " +
                "values (?, ?, ?, ?, ?)";

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
            pst.setString(1, dto.getCampus());
            pst.setString(2, dto.getType());
            pst.setString(3, dto.getClubName());
            pst.setString(4, dto.getActivity());
            pst.setString(5, dto.getPromotion());

            if (dto.getCampus().equals("") || dto.getType().equals("") || dto.getActivity().equals("") ||
                dto.getPromotion().equals("")) {
                check = false;
            } else {
                rs = pst.executeUpdate();
                list.add(dto);
                check = true;
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
    public void uploadClubImg(String file, String clubName) {
        String sql = "UPDATE PROMOTION SET img = ? WHERE  name = ?";
        Connection conn = null;
        PreparedStatement pst = null;


        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, file);
            pst.setString(2, clubName);

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
