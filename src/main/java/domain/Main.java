package domain;
import java.time.LocalDateTime;
import java.time.Month;


public class Main {

    public static void main(String[] args) {

        //LocalDateTime now = LocalDateTime.now();
        LocalDateTime now = LocalDateTime.of(2020, Month.FEBRUARY, 7, 19, 30, 40);
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,true,12,13);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,true,12,14);
        Order order = new Order(1, new PersonTicket());

        order.addSeatReservation(movieticket);
        order.addSeatReservation(movieticket1);


        System.out.println("Order Price: €" + order.calculatePrice());
    }
}
