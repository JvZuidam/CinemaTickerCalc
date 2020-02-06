package domain;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,true,12,13);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,true,12,14);
        Order order = new Order(1, true);

        order.addSeatReservation(movieticket);
        order.addSeatReservation(movieticket1);

        order.getOrderNr();
        order.calculatePrice();
    }
}
