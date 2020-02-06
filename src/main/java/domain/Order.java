package domain;

import java.time.LocalDateTime;
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

    void calculatePrice()
    {
       MovieTicket selectedTicket = this.tickets.get(0);
       double PricePerSeatGet = selectedTicket.getPrice();
       boolean CheckStudentOrder = this.isStudentOrder;

       boolean SecondTicketFree = false;

       //Normaale totale prijs
        double AmountTickets = tickets.size();
        double orderPrice = AmountTickets * PricePerSeatGet;

        //2e kaartje gratis
            if(CheckStudentOrder && tickets.size() >= 2){
                //2e kaartje gratis dus van de totale prijs, haal 1 ticket prijs eraf
                orderPrice = orderPrice - PricePerSeatGet;
                SecondTicketFree = true;
            }else{
                //Valt de filmdag op ma/di/wo/do?
                MovieTicket SelectedTicket = tickets.get(0);

                LocalDateTime ScreeningDate = SelectedTicket.getScreeningDate();
                String ScreenDateName = ScreeningDate.getDayOfWeek().toString();
                //Indien de datum NIET op vrijdag, zaterdag of zondag valt, is het tweede kaartje gratis en moet er een ticket prijs van het totale bedrag afgetrokkken worden
                orderPrice = orderPrice - PricePerSeatGet;
            }


        //10% Korting Krijgen indien je niet een student bent EN je hebt meer dan 6 kaartjes
        if (!CheckStudentOrder && AmountTickets >= 6){
                //Geef 10% korting op het totale bedrag
                orderPrice = orderPrice *0.90;
        }

        //Bekijk voor elke ticket of de ticket een premium ticket is
        double TotalPremiumPrice = 0.00;
        for (int i = 0; i < AmountTickets; i++){
            //Is de order voor studenten?
            if(CheckStudentOrder && this.tickets.get(i).isPremiumTicket() && i != 1){
                orderPrice = orderPrice + 2.00;
            }else{
                TotalPremiumPrice = TotalPremiumPrice +3.00;
            }
        }
        //Indien het geen student is en de 10% korting heeft gekregen, voeg 10% korting toe aan de extra kosten
        if(!CheckStudentOrder && AmountTickets >= 6){
            TotalPremiumPrice = TotalPremiumPrice*0.90;
            orderPrice = orderPrice + TotalPremiumPrice;
        }
        System.out.println("Prijs voor order " + this.orderNr+ " = € " + orderPrice);
    }


    public void export(TicketExportFormat exportFormat)
    {
        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json

    }
}
