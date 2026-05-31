package org.example.Entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNo;
    private Date departTime;
    private Date arrivedTime;
    private List<List<Integer>> seats;
    private Map<String,Date> stationTime;
    private List<String> stations;

    public Train(String trainId, String trainNo, Date departTime, Date arrivedTime, List<List<Integer>> seats, Map<String, Date> stationTime, List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.departTime = departTime;
        this.arrivedTime = arrivedTime;
        this.seats = seats;
        this.stationTime = stationTime;
        this.stations = stations;
    }

    public Train() {
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Date arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String, Date> getStationTime() {
        return stationTime;
    }

    public void setStationTime(Map<String, Date> stationTime) {
        this.stationTime = stationTime;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
}
