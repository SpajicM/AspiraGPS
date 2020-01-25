package hr.marin.aspiragps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;

class Route {

    private int timeSeconds;
    private double distance;
    private Date timestamp;

    public int getTimeSeconds() {
        return timeSeconds;
    }

    public double getDistance() {
        return distance;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Route(int timeSeconds, double distance, Date timestamp) {
        this.timeSeconds = timeSeconds;
        this.distance = distance;
        this.timestamp = timestamp;
    }


    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.US, "%.2f", distance) + " m" + " - " + Formatter.getDateTimeString(timestamp);
    }
}
