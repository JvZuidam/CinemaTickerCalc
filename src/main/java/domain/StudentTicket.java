package domain;

import java.util.ArrayList;

public class StudentTicket implements IAudienceTicket {
    @Override
    public double calculatePrice(Order order) {
        double orderPrice = 0.00;
        ArrayList<MovieTicket> orderTickets = order.retrieveTickets();

        for(int i = 1; i <= orderTickets.size(); i++) {
            MovieTicket selectedTicket1 = orderTickets.get(i - 1);
            //tweede kaartje gratis
            if (i % 2 != 0) {
                orderPrice += totalPriceOrder(orderTickets.get(i-1));

            }
        }
        return orderPrice;
    }

    public double totalPriceOrder(MovieTicket selectedTicket){
        return (selectedTicket.getPrice() + (selectedTicket.isPremiumTicket() ? 3: 0));

    }
}
