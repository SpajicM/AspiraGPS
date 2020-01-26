package hr.marin.aspiragps;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

class RouteStore {
    private static final String ROUTES_KEY = "routes";

    public static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates";

    static void addRoute(Route route, Context context) {
        ArrayList<Route> routes = getRoutes(context);

        // ne postoji array, inicijaliziraj novi
        if(routes == null) {
            routes = new ArrayList<>();
        }

        routes.add(route);

        Gson gson = new Gson();
        String jsonString = gson.toJson(routes);

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(ROUTES_KEY, jsonString)
                .apply();
    }

    static void removeRoute(int index, Context context) {
        ArrayList<Route> routes = getRoutes(context);

        routes.remove(index);

        Gson gson = new Gson();
        String jsonString = gson.toJson(routes);

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(ROUTES_KEY, jsonString)
                .apply();
    }

    static ArrayList<Route> getRoutes(Context context) {
        String routesString = PreferenceManager.getDefaultSharedPreferences(context).getString(ROUTES_KEY, "");

        Type listOfMyClassObject = new TypeToken<ArrayList<Route>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(routesString, listOfMyClassObject);
    }

    /**
     * Returns true if requesting location updates, otherwise returns false.
     *
     * @param context The {@link Context}.
     */
    static boolean requestingLocationUpdates(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    /**
     * Stores the location updates state in SharedPreferences.
     * @param requestingLocationUpdates The location updates state.
     */
    static void setRequestingLocationUpdates(Context context, boolean requestingLocationUpdates) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                .apply();
    }

}
