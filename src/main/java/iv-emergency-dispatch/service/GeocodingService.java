package ivemergencydispatch.service;

import org.springframework.stereotype.Service;
import com.google.gson.*;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.OverQueryLimitException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AddressType;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;

@Service
public class GeocodingService {
  GeoApiContext context = new GeoApiContext.Builder()
    .apiKey("AIzaSyCUnfYzJHtkHXoJTSomF2yJsVgK5P5kXgg")
    .build();

    // returns lat lng coordinates of address
    public String getCoords(String address) throws Exception{
      GeocodingApiRequest req = GeocodingApi.newRequest(context).address(address.concat(", Isla Vista CA"));

      try{
        GeocodingResult[] result = req.await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String lat = gson.toJson(result[0].geometry.location.lat);
        String lng = gson.toJson(result[0].geometry.location.lng);
        // System.out.println(gson.toJson(result[0].geometry.location));
        // System.out.println("[" + lat + ", " + lng + "]");
        return("[" + lat + ", " + lng + "]");
      } catch (Exception e) {
        req.awaitIgnoreError();
        return "";
      }
    }
}