package org.data_bases_hwork;

import java.sql.SQLException;
import java.util.Arrays;


public class App {
    public static void main(String[] args) throws SQLException {
        DataInsert accommodation = new DataInsert();
        DataInsert room_fair = new DataInsert();
        DataInsert accommodationRoomFairRelation = new DataInsert();

        accommodation.insertAccommodation(Arrays.asList(
                new Accommodation(1, "Comfort room", "Queen size ", 3,
                        "All our hotel rooms are elegantly furnished with handmade furniture include luxury " +
                                "en-suite facilities with complimentary amenities pack, flat screen LCD TV, " +
                                "tea/coffee making facilities, fan, hairdryer and the finest pure white linen and " +
                                "towels."),
                new Accommodation(2, "Deluxe room", "King size ", 4,
                        "Our Deluxe room also provides views over landscaped gardens. " +
                                "It has a seating area, digital safe and mini fridge. This room can be configured with"
                                + " either 2 single beds or zip and linked to provide a large double bed."),

                new Accommodation(3, "Budget room", "Single bed ", 2,
                        "As our smallest budget rooms, the Compact bedrooms are suited for single occupancy "
                                + "or short-stay double occupancy as they have limited space and storage.")));

        room_fair.insertRoomFair(Arrays.asList(
                new RoomFair(1, 99.5, "Summer"),
                new RoomFair(2, 155.5, "Summer"),
                new RoomFair(3, 60, "Summer")));

        accommodationRoomFairRelation.insertAccommodationRoomFairRelation(Arrays.asList(
                new AccommodationRoomFairRelation(1,1,1),
                new AccommodationRoomFairRelation(2,2,2),
                new AccommodationRoomFairRelation(3,3,3)
        ));

        accommodation.printRoomAndPricesPer();


    }
}


