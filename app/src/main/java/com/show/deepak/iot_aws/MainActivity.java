package com.show.deepak.iot_aws;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int stop=0;
    List<Double> list1 = new ArrayList<>();
    List<Double> list2 = new ArrayList<>();
    List<Double> list3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new retrieve().execute();

    }
    private class retrieve extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            System.out.println(list1);
            System.out.println(list2);
            System.out.println(list3);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            Manager m = new Manager();
            CognitoCachingCredentialsProvider credentialsProvider = m.getcredentials(MainActivity.this);

            DynamoDBMapper mapper = Manager.mapper;
            AmazonDynamoDBClient ddbClient = Region.getRegion(Regions.US_WEST_2) // CRUCIAL
                    .createClient(
                            AmazonDynamoDBClient.class,
                            credentialsProvider,
                            new ClientConfiguration()
                    );
            //getter_setter g = mapper.load(getter_setter.class,)
            ScanRequest scanRequest = new ScanRequest().withTableName("asensor");


            ScanResult result = ddbClient.scan(scanRequest);

            for (Map<String, AttributeValue> item : result.getItems()){
                if(stop<10){
                    System.out.println(item);
                    System.out.println(item.get("pressure").getS() + "  " + "pressure ");
                    list1.add(Double.valueOf(item.get("pressure").getS()));
                    list2.add(Double.valueOf(item.get("time").getS()));
                    list3.add(Double.valueOf(item.get("temperature").getS()));
                }
                stop++;
            }

            return null;
        }
    }
}
