package domain;

import java.util.ArrayList;

public class PersonTicket implements IAudienceTicket {
    @Override
    public double calculatePrice(Order order) {
        double orderPrice = 0.00;
        ArrayList<MovieTicket> orderTickets = order.retrieveTickets();

        for(int i = 1; i <= orderTickets.size(); i++) {
            MovieTicket selectedTicket1 = orderTickets.get(i - 1);
            //tweede kaartje gratis
            if(i % 2 != 0 && !secondTicketFree(orderTickets.get(i -1))){
                orderPrice += totalPriceOrder(orderTickets.get(i-1));
            }
        }

        if(ticketDiscountGroup(orderTickets)){
            orderPrice = orderPrice * 0.90;
        }

        return orderPrice;
    }

    public boolean secondTicketFree(MovieTicket ticket){
        boolean ticketFree = false;
        switch (ticket.getScreeningDate().getDayOfWeek().name()) {
            case ("MONDAY"):
                ticketFree = true;
                break;
            case ("TUESDAY"):
                ticketFree = true;
                break;
            case ("WEDNESDAY"):
                ticketFree = true;
                break;
            case ("THURSDAY"):
                ticketFree = true;
                break;
            default:
                break;
        }
        return ticketFree;
    }

    public boolean ticketDiscountGroup(ArrayList<MovieTicket> orderTickets){
        boolean discountGroup = false;
        for (MovieTicket selectedTicket : orderTickets){
            switch (selectedTicket.getScreeningDate().getDayOfWeek().name()) {
                case ("SATURDAY"):
                    if(orderTickets.size() >= 6) {
                        discountGroup = true;
                    }
                case ("SUNDAY"):
                    if(orderTickets.size() >= 6) {
                        discountGroup = true;
                    }
                default:
                    break;
            }
        }
        return discountGroup;
    }

    public double totalPriceOrder(MovieTicket selectedTicket){
        return (selectedTicket.getPrice() + (selectedTicket.isPremiumTicket() ? 3: 0));

    }
}
