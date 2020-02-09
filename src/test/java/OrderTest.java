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
        Order orderSAT = new Order(1, new PersonTicket());
        Order orderSUN = new Order(1, new PersonTicket());

        LocalDateTime Saturday = LocalDateTime.of(2020, Month.FEBRUARY, 8, 19, 30, 40);
        LocalDateTime Sunday = LocalDateTime.of(2020, Month.FEBRUARY, 9, 19, 30, 40);

        Movie movie = new Movie("Fifty Shades Of Bert");

        MovieScreening movieScreeningSAT = new MovieScreening(movie, Saturday, 12.99);
        MovieScreening movieScreeningSUN = new MovieScreening(movie, Sunday, 12.99);

        MovieTicket movieTicketSAT1 = new MovieTicket(movieScreeningSAT,false,12,13);
        MovieTicket movieTicketSAT2 = new MovieTicket(movieScreeningSAT,false,12,13);
        MovieTicket movieTicketSAT3 = new MovieTicket(movieScreeningSAT,false,12,13);
        MovieTicket movieTicketSAT4 = new MovieTicket(movieScreeningSAT,false,12,13);
        MovieTicket movieTicketSAT5 = new MovieTicket(movieScreeningSAT,false,12,13);
        MovieTicket movieTicketSAT6 = new MovieTicket(movieScreeningSAT,false,12,13);

        MovieTicket movieTicketSUN1 = new MovieTicket(movieScreeningSUN,false,12,13);
        MovieTicket movieTicketSUN2 = new MovieTicket(movieScreeningSUN,false,12,13);
        MovieTicket movieTicketSUN3 = new MovieTicket(movieScreeningSUN,false,12,13);
        MovieTicket movieTicketSUN4 = new MovieTicket(movieScreeningSUN,false,12,13);
        MovieTicket movieTicketSUN5 = new MovieTicket(movieScreeningSUN,false,12,13);
        MovieTicket movieTicketSUN6 = new MovieTicket(movieScreeningSUN,false,12,13);


        //Act
        orderSAT.addSeatReservation(movieTicketSAT1);
        orderSAT.addSeatReservation(movieTicketSAT2);
        orderSAT.addSeatReservation(movieTicketSAT3);
        orderSAT.addSeatReservation(movieTicketSAT4);
        orderSAT.addSeatReservation(movieTicketSAT5);
        orderSAT.addSeatReservation(movieTicketSAT6);

        orderSUN.addSeatReservation(movieTicketSUN1);
        orderSUN.addSeatReservation(movieTicketSUN2);
        orderSUN.addSeatReservation(movieTicketSUN3);
        orderSUN.addSeatReservation(movieTicketSUN4);
        orderSUN.addSeatReservation(movieTicketSUN5);
        orderSUN.addSeatReservation(movieTicketSUN6);

        double resultSAT = orderSAT.calculatePrice();
        double amountTicketsSAT = orderSAT.retrieveTickets().size();

        double resultSUN = orderSAT.calculatePrice();
        double amountTicketsSUN = orderSAT.retrieveTickets().size();

        //Assert
        assertEquals(70.146, resultSAT );
        assertEquals(6, amountTicketsSAT );
        assertEquals(70.146, resultSUN );
        assertEquals(6, amountTicketsSUN );
    }

    @Test
    public void CheckIfNotStudentGetsSecondTicketFreeOnCorrectDays(){
        Order orderMonday = new Order(1, new PersonTicket());
        Order orderTuesday = new Order(2, new PersonTicket());
        Order orderWednesday = new Order(3, new PersonTicket());
        Order orderThursday = new Order(4, new PersonTicket());
        Order orderFriday = new Order(5, new PersonTicket());

        LocalDateTime DateMonday = LocalDateTime.of(2020, Month.FEBRUARY, 3, 19, 30, 40);
        LocalDateTime DateTuesday = LocalDateTime.of(2020, Month.FEBRUARY, 4, 19, 30, 40);
        LocalDateTime DateWednesday = LocalDateTime.of(2020, Month.FEBRUARY, 5, 19, 30, 40);
        LocalDateTime DateThursday = LocalDateTime.of(2020, Month.FEBRUARY, 6, 19, 30, 40);
        LocalDateTime DateFriday = LocalDateTime.of(2020, Month.FEBRUARY, 7, 19, 30, 40);

        Movie movie = new Movie("Fifty Shades Of Bert");

        MovieScreening movieScreeningMonday = new MovieScreening(movie, DateMonday, 12.99);
        MovieScreening movieScreeningTuesday = new MovieScreening(movie, DateTuesday, 12.99);
        MovieScreening movieScreeningWednesday = new MovieScreening(movie, DateWednesday, 12.99);
        MovieScreening movieScreeningThursday= new MovieScreening(movie, DateThursday, 12.99);
        MovieScreening movieScreeningFriday= new MovieScreening(movie, DateFriday, 12.99);

        MovieTicket movieTicketMo1 = new MovieTicket(movieScreeningMonday,false,12,13);
        MovieTicket movieTicketMo2 = new MovieTicket(movieScreeningMonday,false,12,14);

        MovieTicket movieTicketTu1 = new MovieTicket(movieScreeningTuesday,false,12,13);
        MovieTicket movieTicketTu2 = new MovieTicket(movieScreeningTuesday,false,12,14);

        MovieTicket movieTicketWe1 = new MovieTicket(movieScreeningWednesday,false,12,13);
        MovieTicket movieTicketWe2 = new MovieTicket(movieScreeningWednesday,false,12,14);

        MovieTicket movieTicketTh1 = new MovieTicket(movieScreeningThursday,false,12,13);
        MovieTicket movieTicketTh2 = new MovieTicket(movieScreeningThursday,false,12,14);

        MovieTicket movieTicketFr1 = new MovieTicket(movieScreeningFriday,false,12,14);
        MovieTicket movieTicketFr2 = new MovieTicket(movieScreeningFriday,false,12,14);

        //Act
        orderMonday.addSeatReservation(movieTicketMo1);
        orderMonday.addSeatReservation(movieTicketMo2);

        orderTuesday.addSeatReservation(movieTicketTu1);
        orderTuesday.addSeatReservation(movieTicketTu2);

        orderWednesday.addSeatReservation(movieTicketWe1);
        orderWednesday.addSeatReservation(movieTicketWe2);

        orderThursday.addSeatReservation(movieTicketTh1);
        orderThursday.addSeatReservation(movieTicketTh2);

        orderFriday.addSeatReservation(movieTicketFr1);
        orderFriday.addSeatReservation(movieTicketFr2);

        double resultMo = orderMonday.calculatePrice();
        double resultTu = orderTuesday.calculatePrice();
        double resultWe = orderWednesday.calculatePrice();
        double resultTh = orderThursday.calculatePrice();
        double resultFr = orderFriday.calculatePrice();

        //Assert
        assertEquals(12.99, resultMo );
        assertEquals(12.99, resultTu );
        assertEquals(12.99, resultWe );
        assertEquals(12.99, resultTh );
        assertEquals(25.98, resultFr );

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
