package com.KNUT_CLUB_Test.domain.eventsrvice.repository;

import com.KNUT_CLUB_Test.domain.eventsrvice.Event;
import com.KNUT_CLUB_Test.domain.eventsrvice.EventPostDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository{

    @Override
    public List<Event> getEventList(String field, String field2, String query, String query2, int page) {

        List<Event> eventList = new ArrayList<>();

        String sql = "SELECT @ROWNUM := @ROWNUM +1 as n, EVENT.*"
                + " FROM EVENT, (SELECT @ROWNUM := 0)TMP WHERE " + field + " Like ? and " + field2 + " LIKE ?";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        if (query.equals("전국")) {
            query = "";
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + query + "%");
            pst.setString(2, "%" + query2 + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                int num = rs.getInt("num");
                String name = rs.getString("name");
                String img = rs.getString("img");

                Event event = new Event(
                        num
                        , name
                        , img
                );
                eventList.add(event);
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
        return eventList;
    }

    @Override
    public List<Event> getEventDetail(int num) {
        List<Event> eventList = new ArrayList<>();

        String sql = "SELECT * FROM EVENT WHERE num= ? ";

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
                String promotion = rs.getString("promotion");
                String img = rs.getString("img");

                 Event event = new Event(
                        name
                        , promotion
                        , img
                );
                eventList.add(event);
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
        return eventList;
    }

    @Override
    public boolean getEventWrite(EventPostDTO dto) {

        List<EventPostDTO> eventList = new ArrayList<>();
        boolean check = false;

        String sql = "INSERT INTO EVENT(campus, type, name, activity, promotion)"
                + " values (?, ?, ?, ?, ?)";

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
            pst.setString(3, dto.getName());
            pst.setString(4, dto.getActivity());
            pst.setString(5, dto.getPromotion());

            if (dto.getCampus().equals("") || dto.getType().equals("") || dto.getName().equals("") || dto.getActivity().equals("") ||
                dto.getPromotion().equals("")) {
                check = false;
            }
            else {
                rs = pst.executeUpdate();
                eventList.add(dto);
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
    public void uploadEventImg(String file, String eventName) {
        String sql = "UPDATE EVENT SET img = ? WHERE  name = ?";
        Connection conn = null;
        PreparedStatement pst = null;


        String dbURL = "jdbc:mysql://localhost:4406/KNUT_CLUB";
        String dbID = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            pst = conn.prepareStatement(sql);
            pst.setString(1, file);
            pst.setString(2, eventName);

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
