package hr.marin.aspiragps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Formatter {
    // display utils
    static String getDistanceString(double distance) {
        return String.format(Locale.US, "%.2f", distance) + " m";
    }

    static String getAvgSpeedString(double distance, int timeSeconds) {
        double avgSpeed = distance / timeSeconds * 3.6;

        return String.format(Locale.US, "%.2f", avgSpeed) + " km/h";
    }

    static String getTimeString(int timeSeconds) {
        int hours = timeSeconds / 3600;
        int minutes = (timeSeconds % 3600) / 60;
        int secondsLeft = timeSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, secondsLeft);
    }

    static String getDateTimeString(Date timestamp) {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return simpleDate.format(timestamp);
    }
}
