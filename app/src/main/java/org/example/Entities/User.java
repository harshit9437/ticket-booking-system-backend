package org.example.Entities;

import java.util.List;

public class User {
    private String Name;
    private String Password;
    private String hashedPassword;
    private String userId;
    private List<Ticket> ticketsBooked;

    public User(String name, String password, String hashedPassword, String userId) {
        Name = name;
        Password = password;
        this.hashedPassword = hashedPassword;
        this.userId = userId;
    }
    public void printTickets(){
        for (int i = 0; i<ticketsBooked.size(); i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
