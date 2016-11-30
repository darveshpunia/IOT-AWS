package com.show.deepak.iot_aws;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.mobileconnectors.cognito.DefaultSyncCallback;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;

/**
 * Created by Darvesh on 30-Nov-16.
 */

public class Manager {

    CognitoCachingCredentialsProvider credentialsProvider=null;
    CognitoSyncManager client=null;
    AmazonS3Client amazonS3Client;
    TransferUtility transferUtility;
    AmazonDynamoDBClient ddbClient;
    static DynamoDBMapper mapper;
    public CognitoCachingCredentialsProvider getcredentials(Context context){

        credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                "us-west-2:19d66f55-5cca-478c-a2f9-70413ceae271", // Identity Pool ID
                Regions.US_WEST_2  // Region
        );
        client = new CognitoSyncManager(
                context,
                Regions.US_WEST_2,
                credentialsProvider);

       // mapper = new DynamoDBMapper(ddbClient);

//        Dataset dataset = client.openOrCreateDataset("asensor");
//        String value = dataset.get("myKey");
//        dataset.put("myKey", "my value");
//        dataset.synchronize(new DefaultSyncCallback());
        return  credentialsProvider;
    }




}
