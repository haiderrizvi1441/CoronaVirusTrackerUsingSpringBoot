package io.HaiderRizviLtd.coronacasestracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.HaiderRizviLtd.coronacasestracker.models.LocationStats;


// this is used to make call and fetch data from the URL
@Service
public class coronaVirusDataService {

    private static String Virus_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }
    
    
    public void setAllStats(List<LocationStats> allStats) {
        this.allStats = allStats;
    }


    // this will make the http call
    @PostConstruct
    // to schedule update as data is being updated daily
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client =HttpClient.newHttpClient();

        // creating the request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(Virus_URL)).build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString()); 
        
        StringReader csvBodyReader = new StringReader(httpResponse.body());
       
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        for (CSVRecord record: records){
            
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State")); 
            locationStat.setCountry(record.get("Country/Region"));
            // goal is to get the last column for the latest case . hence record size -1
            locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));  
            System.out.println(locationStat);
            newStats.add(locationStat); 
        
        }
        this.allStats = newStats;
    }
    
}
