package POO_WORKSHOP;

import java.util.Arrays;

public class Route {
    private int idRoute;
    private String[] cities;
    private int maxVehicles;
    private int currentNumberOfVehicles = 0;

    public Route(int idRoute, String[] cities, int maxVehicles) {
        this.idRoute = idRoute;
        this.cities = cities;
        this.maxVehicles = maxVehicles;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public boolean containsCity(String city){
        for (String c : cities){
            if (c.equals(city)){
                return  true;
            }
        }
        return false;
    }

    public boolean hasCapacity(){
        if (currentNumberOfVehicles < maxVehicles){
            return true;
        }
        return false;
    }

    public void addVehicle(){
        currentNumberOfVehicles++;
    }

    public void removeVehicle(){
        currentNumberOfVehicles--;
    }

    @Override
    public String toString(){
        return "\nThe Route identify by the " + idRoute + " ID number has the next cities in the road: \n"
                + Arrays.toString(cities) + "\nAnd a maximum capacity of " + maxVehicles + " vehicles";
    }
}
