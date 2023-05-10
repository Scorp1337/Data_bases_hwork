package org.data_bases_hwork;


import java.sql.*;
import java.util.List;


public class DataInsert {


    static final String URL = "jdbc:postgresql://localhost:5432/databasebooking";
    static final String USER = "postgres";
    static final String PASS = "root";

    private static final String INSERT_ACCOMMODATIONS_SQL = "insert into accommodation" +
            "(id,type,bed_type,max_guests,description) values " + " (?,?,?,?,?);";

    private static final String INSERT_ROOM_FAIR_SQL = "insert into room_fair" + "(id,value,season) VALUES " +
            "(?,?,?);";

    private static final String INSERT_ACCOMMODATION_ROOM_FAIR_RELATION = "insert into accommodation_room_fair_relation"
            + "(id, accommodation_id, room_fair_id) VALUES " + "(?,?,?);";
    final String format = "%20s%20s%12s\n";
    Statement statement = null;
    ResultSet resultSet = null;

    public void insertAccommodation(List<Accommodation> accommodations) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOMMODATIONS_SQL)) {

            for (Accommodation accommodation : accommodations) {

                preparedStatement.setInt(1, accommodation.getId());
                preparedStatement.setString(2, accommodation.getType());
                preparedStatement.setString(3, accommodation.getBed_type());
                preparedStatement.setInt(4, accommodation.getMax_guests());
                preparedStatement.setString(5, accommodation.getDescription());
                preparedStatement.executeUpdate();
                System.out.println("ID: " + accommodation.getId() + " Type: " + accommodation.getType() + " Bed type: "
                        + accommodation.getBed_type() + " Max guests: " + accommodation.getMax_guests() + " Description: "
                        + accommodation.getDescription());

            }
        } catch (SQLException e) {
            System.err.format("SQ State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertRoomFair(List<RoomFair> list) throws SQLException {

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOM_FAIR_SQL);
        ) {

            for (RoomFair roomFair : list) {
                preparedStatement.setInt(1, roomFair.getId());
                preparedStatement.setDouble(2, roomFair.getValue());
                preparedStatement.setString(3, roomFair.getSeason());
                preparedStatement.executeUpdate();

                System.out.println("ID: " + roomFair.getId() + " Value: " + roomFair.getValue() + " Season: "
                        + roomFair.getSeason());


            }
        } catch (SQLException e) {

        }
    }

    public void insertAccommodationRoomFairRelation(List<AccommodationRoomFairRelation> list) throws SQLException {

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOMMODATION_ROOM_FAIR_RELATION);
        ) {

            for (AccommodationRoomFairRelation accommodationRoomFairRelation : list) {
                preparedStatement.setInt(1, accommodationRoomFairRelation.getId());
                preparedStatement.setInt(2, accommodationRoomFairRelation.getAccommodation_id());
                preparedStatement.setInt(3, accommodationRoomFairRelation.getRoom_fair_id());
                preparedStatement.executeUpdate();

                System.out.println("ID: " + accommodationRoomFairRelation.getId() + " Accommodation_id: " +
                        accommodationRoomFairRelation.getAccommodation_id() + " Room_fair_id: "
                        + accommodationRoomFairRelation.getRoom_fair_id());


            }
        } catch (SQLException e) {

        }
    }

    public void printRoomAndPricesPer() throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASS);

        ) {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT accommodation.id, accommodation.type," +
                    " room_fair.value from accommodation\n" +
                    "JOIN accommodation_room_fair_relation ON accommodation.id = accommodation_room_fair_relation.id \n" +
                    "JOIN room_fair ON accommodation_room_fair_relation.id  = room_fair.id");

            boolean hasResults = resultSet.next();
            if (hasResults) {
                System.out.format(format, "ID", "RoomType", "Room price");
                do {
                    System.out.format(format, resultSet.getInt("id"), resultSet.getString("type"),
                            resultSet.getDouble("value"));

                } while (resultSet.next());
            } else System.out.println("No results.");
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + e.getMessage());
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException e) {
            }
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {


            }
        }
    }
}
