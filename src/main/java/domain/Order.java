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

        tickets = new ArrayList<MovieTicket>();
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

        //Haal Datum op
        LocalDateTime ScreeningDate = selectedTicket.GetScreeningDate();
        String ScreenDateName = ScreeningDate.getDayOfWeek().toString();

        //2e kaartje gratis
            //Indien je studentn bent en je hebt meer dan twee kaartjes, krijg het tweede kaartje gratis
            if(CheckStudentOrder && tickets.size() >= 2){
                orderPrice = orderPrice - PricePerSeatGet;
                SecondTicketFree = true;
            }
            //Indien je geen studentt bent en de filmdag niet op vrijdag/zaterdag/zondag is, krijg het tweede kaartje gratis
            if(!CheckStudentOrder && !ScreenDateName.equals("FRIDAY") && !ScreenDateName.equals("SATURDAY") && !ScreenDateName.equals("SUNDAY")){
                orderPrice = orderPrice - PricePerSeatGet;
                SecondTicketFree = true;
            }


        //10% Korting Krijgen indien je niet een student bent EN je hebt meer dan 6 kaartjes
        if (!CheckStudentOrder && AmountTickets >= 6){
                //Geef 10% korting op het totale bedrag
                orderPrice = orderPrice *0.90;
        }

        //Bekijk voor elke ticket of de ticket een premium ticket is
        double TotalPremiumPrice = 0.00;
        for (int i = 0; i < AmountTickets; i++){
            //Indien je een student bent en je kaartje is een premium kaartje en het gaat niet om je tweede kaartje, extra kosten is 2 euro
            if(CheckStudentOrder && this.tickets.get(i).isPremiumTicket() && i != 1){
                orderPrice = orderPrice + 2.00;
            }
            //Indien je niet een student bent en je kaartje is een premium kaart
            if(!CheckStudentOrder && this.tickets.get(i).isPremiumTicket() && tickets.get(i).getPrice() != 0.00 ){
                TotalPremiumPrice = TotalPremiumPrice + 3.00;
            }
        }

        //Indien je niet een student bent en je kaartje is een premium ticket en je hebt je tweede kaartje gratis gekregen haal 1 extra kosten eraf
        if(!CheckStudentOrder && this.tickets.get(1).isPremiumTicket() && SecondTicketFree){
            TotalPremiumPrice = TotalPremiumPrice - 3.00;
        }


        //Indien het geen student is en de 10% korting heeft gekregen, voeg 10% korting toe aan de extra kosten
        if(!CheckStudentOrder && AmountTickets >= 6){
            TotalPremiumPrice = TotalPremiumPrice*0.90;
            orderPrice = orderPrice + TotalPremiumPrice;
        }
        System.out.println("Prijs voor order " + this.orderNr+ " = € " + orderPrice);

        //Complexiteit 19
    }


    public void export(TicketExportFormat exportFormat)
    {
        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json

    }
}
