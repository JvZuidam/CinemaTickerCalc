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
       double pricePerSeatGet = selectedTicket.getPrice();
       boolean checkStudentOrder = this.isStudentOrder;

       boolean secondTicketFree = false;

       //Normaale totale prijs
        double amountTickets = tickets.size();
        double orderPrice = amountTickets * pricePerSeatGet;

        //Haal Datum op
        LocalDateTime screeningDate = selectedTicket.getScreeningDate();
        String screenDateName = screeningDate.getDayOfWeek().toString();

        //2e kaartje gratis
            //Indien je studentn bent en je hebt meer dan twee kaartjes, krijg het tweede kaartje gratis
            if(checkStudentOrder && tickets.size() >= 2){
                orderPrice = orderPrice - pricePerSeatGet;
                secondTicketFree = true;
            }
            //Indien je geen studentt bent en de filmdag niet op vrijdag/zaterdag/zondag is, krijg het tweede kaartje gratis
            if(!checkStudentOrder && !screenDateName.equals("FRIDAY") && !screenDateName.equals("SATURDAY") && !screenDateName.equals("SUNDAY")){
                orderPrice = orderPrice - pricePerSeatGet;
                secondTicketFree = true;
            }


        //10% Korting Krijgen indien je niet een student bent EN je hebt meer dan 6 kaartjes
        if (!checkStudentOrder && amountTickets >= 6){
                //Geef 10% korting op het totale bedrag
                orderPrice = orderPrice *0.90;
        }

        //Bekijk voor elke ticket of de ticket een premium ticket is
        double totalPremiumPrice = 0.00;
        for (int i = 0; i < amountTickets; i++){
            //Indien je een student bent en je kaartje is een premium kaartje en het gaat niet om je tweede kaartje, extra kosten is 2 euro
            if(checkStudentOrder && this.tickets.get(i).isPremiumTicket() && i != 1){
                orderPrice = orderPrice + 2.00;
            }
            //Indien je niet een student bent en je kaartje is een premium kaart
            if(!checkStudentOrder && this.tickets.get(i).isPremiumTicket() && tickets.get(i).getPrice() != 0.00 ){
                totalPremiumPrice = totalPremiumPrice + 3.00;
            }
        }

        //Indien je niet een student bent en je kaartje is een premium ticket en je hebt je tweede kaartje gratis gekregen haal 1 extra kosten eraf
        if(!checkStudentOrder && this.tickets.get(1).isPremiumTicket() && secondTicketFree){
            totalPremiumPrice = totalPremiumPrice - 3.00;
        }


        //Indien het geen student is en de 10% korting heeft gekregen, voeg 10% korting toe aan de extra kosten
        if(!checkStudentOrder && amountTickets >= 6){
            totalPremiumPrice = totalPremiumPrice*0.90;
            orderPrice = orderPrice + totalPremiumPrice;
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
