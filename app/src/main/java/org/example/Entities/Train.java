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

}
