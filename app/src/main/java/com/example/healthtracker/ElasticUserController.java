package com.example.healthtracker;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;


/**
 * ElasticUserController enables a user to communicate with the ElasticSearch database for the purposes of storing and accessing users.
 *
 * @author Tyler Watson
 * @version 1.0
 * @since 2018-10-30
 */
public class ElasticUserController {
    private static JestDroidClient client;
    private static String server = "http://cmput301.softwareprocess.es:8080";
    private static String Index = "cmput301f18t13test";

    /*
    // TODO we need a function which gets users from elastic search
    public static class GetUser extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<User> users = new ArrayList<User>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 3, \n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"message\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(query)
                    .addIndex("testing")
                    .addType("user")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<User> foundusers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundusers);
                }
                else {
                    Log.i("Error", "The search query failed to find any users that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return users;
        }
    }*/
    // verify the settings
    public static void verifySettings() {
        if (client == null) {
            // if the client is not yet created, build the client factory, establish connection to the DB, and finally set the client and its factory
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder(server);
            DroidClientConfig config = builder.build();
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    // add new users to elastic search database
    public static class AddPatient extends AsyncTask<Patient, Void, Void> {
        @Override
        protected Void doInBackground(Patient... patients) {
            verifySettings();
            Patient patient = patients[0];

            //TODO change when finished testing
            Index index = new Index.Builder(patient)
                    .index(Index)
                    .type("Patient")
                    .id(patient.getUserID()).build();

            try {
                // where is the client?
                DocumentResult result = client.execute(index);

                if (!result.isSucceeded()) {
                    Log.i("Error", "Elasticsearch was not able to add the user");
                    throw new ConnectionError();
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed to build and add the patient");
            }
            return null;
        }
    }

    public static class AddCareProvider extends AsyncTask<CareProvider, Void, Void> {

        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            verifySettings();
            CareProvider careProvider = careProviders[0];

            Index index = new Index.Builder(careProvider)
                    .index(Index)
                    .type("CareProvider")
                    .id(careProvider.getUserID()).build();

            try {
                // where is the client?
                DocumentResult result = client.execute(index);
                if (!result.isSucceeded()) {
                    Log.i("Error", "Elasticsearch was not able to add the user");
                    throw new ConnectionError();
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed to build and add the CareProvider");
            }

            return null;
        }
    }

    public static class GetPatient extends AsyncTask<String, Void, Patient> {
        @Override
        protected Patient doInBackground(String... id) {
            verifySettings();
            Patient patient = null;
            Get get = new Get.Builder(Index, id[0])
                    .type("Patient")
                    .build();
            try {
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    patient = result.getSourceAsObject(Patient.class);
                } else {
                    Log.i("error", "Search query failed to find any thing =/");
                    throw new ConnectionError();
                }
            } catch (Exception e) {
                Log.i("Error", "Could not access the server to get the patient");
            }
            return patient;
        }
    }

    public static class GetCareProvider extends AsyncTask<String, Void, CareProvider> {
        @Override
        protected CareProvider doInBackground(String... id) {
            verifySettings();
            CareProvider careProvider = null;
            Get get = new Get.Builder(Index, id[0])
                    .type("CareProvider")
                    .build();
            try {
                JestResult result = client.execute(get);
                if (result.isSucceeded()) {
                    careProvider = result.getSourceAsObject(CareProvider.class);
                } else {
                    Log.i("error", "Search query failed to find any thing =/");
                    throw new ConnectionError();
                }
            } catch (Exception e) {
                Log.i("Error", "Could not access the server to get the patient");
            }
            return careProvider;
        }
    }

}
