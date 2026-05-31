package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserBookingService {
    private String user;
    private List<User> usersList;
    private ObjectMapper objectMapper=new ObjectMapper();
    private static final String User_Path="../localdb/user.json";

    public UserBookingService(String user) throws IOException {
        this.user = user;
        File users=new File(User_Path);
        usersList=objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }
}
