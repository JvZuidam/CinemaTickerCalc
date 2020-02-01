package domain;

import java.time.LocalDate;
import java.time.Month;
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

    public void addSeatReservation(MovieTicket ticket)
    {
        tickets.add(ticket);
    }

    public void calculatePrice()
    {
       MovieTicket selectedTicket = this.tickets.get(0);
       double PricePerSeatGet = selectedTicket.getPrice();
       boolean CheckPremium = selectedTicket.isPremiumTicket();
       boolean CheckStudentOrder = this.isStudentOrder;


       //2e Kaartje gratis
        if(tickets.size() >= 2){
            //get second ticket
            MovieTicket secondticket = tickets.get(1);

            //Ik kreeg niet de tijd van de screening te pakken daarom de tijd gepakt van bestellen. WIJZIG DIT
            String DayOfOrder = LocalDate.now().getDayOfWeek().toString();

            //student
            if(CheckStudentOrder == true){
                double newprice = 0.00;
                //pak prijs van ticket en wijzig prijs naar 0

            }
            //geen student
            else{
                if(DayOfOrder != "FRIDAY" ||DayOfOrder != "SATURDAY" ||DayOfOrder != "SUNDAY" ){
                    double newprice = 0.00;
                    //pak prijs van ticket en wijzig prijs naar 0;

                }else{
                    if(tickets.size() >= 6){
                        double TotaalAmount = 0.00;
                        for (int i = 1; i <= tickets.size(); i++){
                            MovieTicket SelectedTicket = tickets.get(i);
                            double Price = SelectedTicket.getPrice();
                            TotaalAmount = TotaalAmount + Price;
                        }
                        TotaalAmount = TotaalAmount*0.9;

                        //verdeel de prijs over de tickets zodat iedereen een neiuwe amount krijgt
                    }
                }
            }

        }

        //

       //PrijsPremium
        if(CheckPremium == true && CheckStudentOrder == true){
            
            PricePerSeatGet = PricePerSeatGet + 2.00;
        }
        if(CheckPremium == true && CheckStudentOrder == false){
            PricePerSeatGet = PricePerSeatGet + 3.00;
        }


        return ;
    }

    public void export(TicketExportFormat exportFormat)
    {
        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json
    }
}
