package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.Entities.User;
import org.example.Util.UserServiceUtil;
import org.example.Entities.*;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {
    private User user;
    private List<User> usersList;
    private ObjectMapper objectMapper=new ObjectMapper();
    private static final String User_Path="../localdb/user.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(User_Path);
        usersList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public UserBookingService() {
    }

    public Boolean loginUser(){
            Optional<User> foundUser = usersList.stream().filter(user1 -> {
                return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
            }).findFirst();
            return foundUser.isPresent();
        }
    public Boolean signUp(User user1){
        try{
            usersList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File users=new File(User_Path);
        objectMapper.writeValue(users,usersList);
    }
    //fetching tickets
    public void fetchingTicket(){
        Optional<User> userFetch=usersList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if (userFetch.isPresent()){
            userFetch.get().printTickets();
        }
    }
    //cancel the ticket
    public Boolean cancellingTicket(String tickedId){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the ticketId to cancel:");
        tickedId= sc.next();
        if(tickedId==null  ||tickedId.isEmpty()){
            System.out.println("ticketId cannot be null or empty");
                return Boolean.FALSE;
        }
        String finalTicketId=tickedId;
        Boolean removed=user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId));
        String finalTicketID1=tickedId;
        user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketID1));
        if(removed){
            System.out.println("ticket with id"+tickedId+"is cancelled");
            return Boolean.TRUE;
        }else{
            System.out.println("no ticketid found in user data ");
            return Boolean.FALSE;
        }
        //getting train info
        public List<Train> getTrains(String source,String destination){
            Scanner s=new Scanner(System.in);
            System.out.println("enter the source station");
            source=s.next();
            System.out.println("enter the destination station:");
            destination=s.next();
        }
        
    }


}
