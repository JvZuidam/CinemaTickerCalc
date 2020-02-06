package domain;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
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
       boolean checkStudentOrder = this.isStudentOrder;
        //NEW METHOD Second ticket free

        double orderPrice1 = 0.00;

        for(int i = 1; i <= tickets.size(); i++) {
            MovieTicket selectedTicket1 = tickets.get(i-1);
            //Indien student:
            if (checkStudentOrder) {
                if (i % 2 != 0) {
                    orderPrice1 += selectedTicket1.getPrice() + (selectedTicket1.isPremiumTicket() ? 2: 0);
                }

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
                    case ("FRIDAY"):
                    case ("SATURDAY"):
                    case ("SUNDAY"):
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
        System.out.println("Order Price: €" + orderPrice1);
    }


    public void export(TicketExportFormat exportFormat)
    {
        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json

        JSONArray ticketArray = new JSONArray();

        for (MovieTicket ticket : tickets) {
            JSONObject ticketObject = new JSONObject();
            ticketObject.put("ticket", ticket.toString());
            ticketArray.add(ticketObject);
        }
        try (FileWriter file = new FileWriter("Order_" + orderNr + ".json")) {
            file.write(ticketArray.toJSONString());
            file.flush();
            System.out.println(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
