package domain;
import java.util.ArrayList;

public class Order
{
    private int orderNr;
    private boolean isStudentOrder;

    private ArrayList<MovieTicket> tickets;

    public Order(int orderNr, boolean isStudentOrder)
    {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;

        tickets = new ArrayList<>();
    }

    public int getOrderNr()
    {
        return orderNr;
    }

    void addSeatReservation(MovieTicket ticket)
    {
        tickets.add(ticket);
    }

    double calculatePrice()
    {
       boolean checkStudentOrder = this.isStudentOrder;
        //NEW METHOD Second ticket free

        double orderPrice1 = 0.00;

        for(int i = 1; i <= tickets.size(); i++) {
            MovieTicket selectedTicket1 = tickets.get(i-1);
            //Indien student:
            if (checkStudentOrder && i % 2 != 0) {
                    orderPrice1 += selectedTicket1.getPrice() + (selectedTicket1.isPremiumTicket() ? 2: 0);
            }
            if (!checkStudentOrder) {
                boolean ticketFree = false;
                switch (selectedTicket1.getScreeningDate().getDayOfWeek().name()) {
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
                if (ticketFree && i % 2 != 0) {
                    orderPrice1 += (selectedTicket1.getPrice() + (selectedTicket1.isPremiumTicket() ? 3: 0));
                }

                boolean groupDiscount = false;
                switch (selectedTicket1.getScreeningDate().getDayOfWeek().name()) {
                    case ("SATURDAY"):
                        if(tickets.size() >= 6) {
                            groupDiscount = true;
                        }
                    case ("SUNDAY"):
                        if(tickets.size() >= 6) {
                            groupDiscount = true;
                        }
                    default:
                        break;
                }

                if (groupDiscount) {
                    orderPrice1 = orderPrice1 * 0.90;
                }
            }
        }
        return orderPrice1;

        //Complexiteit oud = 22
    }


    public void export(TicketExportFormat exportFormat)
    {
        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json


    }
}
