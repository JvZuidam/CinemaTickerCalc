package domain;
import java.util.ArrayList;

public class Order
{
    private int orderNr;
    private boolean isStudentOrder;

    private IAudienceTicket orderAudience;

    private ArrayList<MovieTicket> tickets;

    public Order(int orderNr, IAudienceTicket orderAudience)
    {
        this.orderNr = orderNr;
        this.orderAudience = orderAudience;

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

    public ArrayList<MovieTicket> retrieveTickets(){
        return this.tickets;
    }

   public double calculatePrice() {
        return orderAudience.calculatePrice(this);
   }


    public void export(TicketExportFormat exportFormat)
    {
        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json


    }
}
