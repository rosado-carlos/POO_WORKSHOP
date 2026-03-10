package POO_WORKSHOP;

import java.util.Arrays;

public final class TransportUtils {
    private TransportUtils(){}

    public static int findTripIndexById(Trip[] trips, int tripId){
        for (int i = 0; i < trips.length; i++) {
            if(trips[i] != null) {
                if (trips[i].getIdTrip() == tripId) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static Trip[] resizeTrips(Trip[] trips){
        return Arrays.copyOf(trips, trips.length*2);
    }
}
