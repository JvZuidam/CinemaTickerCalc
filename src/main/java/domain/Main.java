package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,true,12,13);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,true,12,14);
        Order order = new Order(0, true);

        order.addSeatReservation(movieticket);
        order.addSeatReservation(movieticket1);

        order.calculatePrice();
    }
}
