package org.example.Entities;

import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userId;


    public User(String name, String password, String hashedPassword,List<Ticket> ticketsBooked, String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked=ticketsBooked;
        this.userId = userId;
    }
    public void printTickets(){
        for (Ticket ticket : ticketsBooked) {
            System.out.println(ticket.getTicketInfo());
        }
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name1) {
        name = name1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password1) {
        password = password1;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
