package POO_WORKSHOP;

import java.time.LocalDate;

public class Trip {
    private int idTrip;
    private static int idTripCurrent;
    private double loadQuantity;
    private LoadType loadType;
    private String destinationCity;
    private Driver driver;
    private Vehicle vehicle;
    private Route route;
    private boolean finished;

    static {
        idTripCurrent = 0;
    }

    public Trip(double loadQuantity, LoadType loadType, String destinationCity, Driver driver, Vehicle vehicle, Route route) {
        this.loadQuantity = loadQuantity;
        this.loadType = loadType;
        this.destinationCity = destinationCity;
        this.driver = driver;
        this.vehicle = vehicle;
        this.route = route;
        this.idTrip =  idTripCurrent;
        idTripCurrent++;
    }

    public void finishTrip() {
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getIdTrip(){
        return idTrip;
    }

    public Driver getDriver(){
        return driver;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public Route getRoute(){
        return route;
    }

    @Override
    public String toString() {
        return "\nThe Trip identify by the " + idTrip + " ID has the follow specifications:"
                + "\nPackage Type:" + loadType.toString() + ".\nWeight: " +  loadQuantity
                + " kg.\nDestination: " + destinationCity + ".\nAssigned Driver: " + driver.getName()
                + ".\nAssigned Vehicle: " + vehicle.getLicensePlate()
                + "\nAssigned Route: " + route.getIdRoute() + ".\nDeparture Date: " + LocalDate.now().toString()
                + (vehicle instanceof RefrigeratedTruck ?  "\nTravel Temperature:" + ((RefrigeratedTruck) vehicle).getTravelTemperature() : "");
    }






}
