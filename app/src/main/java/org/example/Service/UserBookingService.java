package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.Entities.User;
import org.example.Util.UserServiceUtil;
import org.example.Entities.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {
    private User user;
    private List<User> usersList;
    private final ObjectMapper objectMapper=new ObjectMapper();
    private static final String User_Path="app/src/main/java/org/example/localdb/user.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUserListFromFile();

    }


    public UserBookingService() throws IOException {
    loadUserListFromFile();
    }
    public void loadUserListFromFile() throws IOException{
        usersList=objectMapper.readValue(new File(User_Path), new TypeReference<>() {
        });
        System.out.println("Users loaded: " + usersList.size());
    }

//    public Boolean loginUser(String username,String password){
//            Optional<User> foundUser = usersList.stream().filter(user1 -> user1.getName().equals(username) && UserServiceUtil.checkPassword(password, user1.getHashedPassword())).findFirst();
//    return foundUser.isPresent();
//    }
public Boolean loginUser(String userName, String password) {
    System.out.println("Users count ="+usersList.size());
    Optional<User> foundUser = usersList.stream()
            .filter(user ->
                    user.getName().equals(userName) &&
                            UserServiceUtil.checkPassword(
                                    password,
                                    user.getHashedPassword()
                            ))
            .findFirst();

    if(foundUser.isPresent()){
        this.user = foundUser.get(); // logged-in user
        return Boolean.TRUE;
    }

    return Boolean.FALSE;
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
        Optional<User> userFetch=usersList.stream().filter(user1 -> user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword())).findFirst();
        userFetch.ifPresent(User::printTickets);
    }
    //cancel the ticket
    public Boolean cancellingTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the ticketId to cancel:");
        String finalTicketId = sc.next();
        if (finalTicketId == null || finalTicketId.isEmpty()) {
            System.out.println("ticketId cannot be null or empty");
            return Boolean.FALSE;
        }
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId));
        user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("ticket with id" + finalTicketId + "is cancelled");
            return Boolean.TRUE;
        } else {
            System.out.println("no ticketId found in user data ");
            return Boolean.FALSE;
        }
    }
        //getting train info
        public List<Train> getTrain(String source,String destination){
            try {
                TrainService trainService=new TrainService();
                return trainService.searchtrain(source,destination);
            }catch (IOException ex){
                return new ArrayList<>();
            }
        }
        public List<List<Integer>> fetchSeats(Train train){
            return train.getSeats();
    }
    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }


}
