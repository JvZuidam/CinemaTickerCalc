package domain;
import java.util.List;

public class StudentTicket implements IAudienceTicket {
    @Override
    public double calculatePrice(Order order) {
        double orderPrice = 0.00;
        List<MovieTicket> orderTickets = order.retrieveTickets();

        for(int i = 1; i <= orderTickets.size(); i++) {
            //tweede kaartje gratis
            if (i % 2 != 0) {
                orderPrice += totalPriceOrder(orderTickets.get(i-1));

            }
        }
        return orderPrice;
    }

    public double totalPriceOrder(MovieTicket selectedTicket){
        return (selectedTicket.getPrice() + (selectedTicket.isPremiumTicket() ? 2: 0));

    }
}
