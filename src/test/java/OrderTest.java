import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class OrderTest {

    public boolean isPositiveNumber(int number) {

        boolean result = false;
        if (number >= 1) {
            result = true;
        }
        return result;
    }

    @Test
    public void checkIfOrderNrIsPositive() {
        //Arrange
        Order order = new Order(1, new StudentTicket());
        OrderTest orderTest = new OrderTest();

        //Act
        int result = order.getOrderNr();

        //Assert
        assertTrue(orderTest.isPositiveNumber(result));
    }

    @Test
    public void checkIfEverySecondTicketIsFreeForStudent(){
        //Arrange
        Order order = new Order(1, new StudentTicket());
        LocalDateTime now = LocalDateTime.now();
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,false,12,13);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,false,12,14);

        //Act
        order.addSeatReservation(movieticket);
        order.addSeatReservation(movieticket1);

        double result = order.calculatePrice();
        double amountTickets = order.retrieveTickets().size();

        //Assert
        assertEquals(12.99, result );
        assertEquals(2, amountTickets );
    }

    @Test
    public void checkIfEverySecondTicketOnCorrectDayForNotStudentsIsFree(){
        //Arrange
        Order order = new Order(1, new PersonTicket());
        LocalDateTime now = LocalDateTime.of(2020, Month.FEBRUARY, 3, 19, 30, 40);
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,false,12,13);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,false,12,14);

        //Act
        order.addSeatReservation(movieticket);
        order.addSeatReservation(movieticket1);

        double result = order.calculatePrice();
        double amountTickets = order.retrieveTickets().size();

        //Assert
        assertEquals(12.99, result );
        assertEquals(2, amountTickets );
    }

    @Test
    public void checkIfEverySecondTicketOnInCorrectDayForNotStudentsIsNotFree(){
        //Arrange
        Order order = new Order(1, new PersonTicket());
        LocalDateTime now = LocalDateTime.of(2020, Month.FEBRUARY, 7, 19, 30, 40);
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,false,12,13);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,false,12,14);

        //Act
        order.addSeatReservation(movieticket);
        order.addSeatReservation(movieticket1);

        double result = order.calculatePrice();
        double amountTickets = order.retrieveTickets().size();

        //Assert
        assertEquals(25.98, result );
        assertEquals(2, amountTickets );
    }

    @Test
    public void checkIfNotStudentGetDiscountWhenGroupSizeIs6OrMorePeopleWeekend(){
        //Arrange
        Order order = new Order(1, new PersonTicket());
        LocalDateTime now = LocalDateTime.of(2020, Month.FEBRUARY, 8, 19, 30, 40);
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,false,12,13);
        MovieTicket movieticket2 = new MovieTicket(movieScreening,false,12,14);
        MovieTicket movieticket3 = new MovieTicket(movieScreening,false,12,14);
        MovieTicket movieticket4 = new MovieTicket(movieScreening,false,12,14);
        MovieTicket movieticket5 = new MovieTicket(movieScreening,false,12,14);
        MovieTicket movieticket6 = new MovieTicket(movieScreening,false,12,14);

        //Act
        order.addSeatReservation(movieticket1);
        order.addSeatReservation(movieticket2);
        order.addSeatReservation(movieticket3);
        order.addSeatReservation(movieticket4);
        order.addSeatReservation(movieticket5);
        order.addSeatReservation(movieticket6);

        double result = order.calculatePrice();
        double amountTickets = order.retrieveTickets().size();

        //Assert
        assertEquals(70.146, result );
        assertEquals(6, amountTickets );
    }

    @Test
    public void PremiumPriceForStudentIs2(){
        Order order = new Order(1, new StudentTicket());
        LocalDateTime now = LocalDateTime.of(2020, Month.FEBRUARY, 7, 19, 30, 40);
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,true,12,13);

        //Act
        order.addSeatReservation(movieticket);

        double result = order.calculatePrice();

        //Assert
        assertEquals(14.99, result );
        assertTrue(movieticket.isPremiumTicket() );
    }

    @Test
    public void PremiumPriceFofPersonIs3(){
        Order order = new Order(1, new PersonTicket());
        LocalDateTime now = LocalDateTime.of(2020, Month.FEBRUARY, 7, 19, 30, 40);
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket = new MovieTicket(movieScreening,true,12,13);

        //Act
        order.addSeatReservation(movieticket);

        double result = order.calculatePrice();

        //Assert
        assertEquals(15.99, result );
        assertTrue(movieticket.isPremiumTicket() );
    }

    @Test
    public void CheckIfStudentOrderDoesNotAddPremiumPriceForEverySecondTicketFree(){
        Order order = new Order(1, new StudentTicket());
        LocalDateTime now = LocalDateTime.of(2020, Month.FEBRUARY, 7, 19, 30, 40);
        Movie movie = new Movie("Fifty Shades Of Bert");
        MovieScreening movieScreening = new MovieScreening(movie, now, 12.99);
        MovieTicket movieticket1 = new MovieTicket(movieScreening,true,12,13);
        MovieTicket movieticket2 = new MovieTicket(movieScreening,true,12,13);

        //Act
        order.addSeatReservation(movieticket1);
        order.addSeatReservation(movieticket2);

        double result = order.calculatePrice();

        //Assert
        assertEquals(14.99, result );
        assertEquals(2, order.retrieveTickets().size());
        assertTrue(movieticket1.isPremiumTicket());
    }
}
