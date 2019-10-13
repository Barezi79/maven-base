import java.io.IOException;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Pokemons {
	
	private final OkHttpClient httpClient = new OkHttpClient();

	public static void main(String[] args) throws Exception {
		
		       Pokemons obj = new Pokemons();

		        JSONObject object = obj.sendGet("https://pokeapi.co/api/v2/berry/");

		        int count = object.getInt("count");
		        int maxSize = -1;
		        int minTime = Integer.MAX_VALUE;
		        int growthTime;
		        int objectSize;
		        String biggestBerryInTheLeastTime = "";
		        String berryName = "";

		        for (int i = 1; i <= count ; i++) {
		            object = obj.sendGet("https://pokeapi.co/api/v2/berry/" + i + "/");
		            growthTime = object.getInt("growth_time");
		            objectSize = object.getInt("size");
		            berryName = object.getString("name");


		            if(growthTime <= minTime && objectSize >= maxSize ){
		                minTime = growthTime;
		                maxSize = objectSize;
		                biggestBerryInTheLeastTime = berryName;
		            }
		        }
		        System.out.println("Maximum size berry in the least amount of time" + "(" + minTime + ") " + "is " +  biggestBerryInTheLeastTime + " with the size of " + maxSize + "mm");
 	

		    }
		    

		    private JSONObject sendGet(String url) throws Exception {

		        Request request = new Request.Builder()
		                .url(url)
		                .build();

		        try (Response response = httpClient.newCall(request).execute()) {

		            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		            return new JSONObject(response.body().string());
		        }

		    }

	}


