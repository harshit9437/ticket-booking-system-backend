package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entities.Train;
import org.example.Entities.User;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.core.type.TypeReference;

public class TrainService {
    private List<Train> trainList;
    ObjectMapper objectMapper=new ObjectMapper();
    private static final String train_Path="../localdb/train.json";

    public TrainService() throws IOException {
        File train = new File(train_Path);
        trainList = objectMapper.readValue(train, new TypeReference<List<Train>>() {
        });
    }

    //searching the train
    public List<Train> searchtrain(String source,String destination){
        return trainList.stream().filter(train -> validTrain(train,source,destination)).collect(Collectors.toList());
    }
    //adding the train
    public void addTrain(Train newTrain) {
        // Check if a train with the same trainId already exists
        Optional<Train> existingTrain = trainList.stream()
                .filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
                .findFirst();

        if (existingTrain.isPresent()) {
            // If a train with the same trainId exists, update it instead of adding a new one
            updateTrain(newTrain);
        } else {
            // Otherwise, add the new train to the list
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }

    public void updateTrain(Train updatedTrain) {
        // Find the index of the train with the same trainId
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId()))
                .findFirst();

        if (index.isPresent()) {
            // If found, replace the existing train with the updated one
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFile();
        } else {
            // If not found, treat it as adding a new train
            addTrain(updatedTrain);
        }
    }

    private void saveTrainListToFile() {
        try {
            objectMapper.writeValue(new File(train_Path), trainList);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your application's requirements
        }
    }




    //valid train
public boolean validTrain(Train train,String source,String destionation){
        List<String> stationOrder=train.getStations();
        int sourceIndex=stationOrder.indexOf(source.toLowerCase());
        int destionationIndex=stationOrder.indexOf((destionation.toLowerCase()));
        return sourceIndex!=-1 && destionationIndex!=-1 && sourceIndex<destionationIndex;
}

  }
