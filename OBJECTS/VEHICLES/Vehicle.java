package POO_WORKSHOP.OBJECTS.VEHICLES;

import POO_WORKSHOP.TOOLS.Availability;
import POO_WORKSHOP.ENUMERATIONS.LoadType;
import POO_WORKSHOP.ENUMERATIONS.StatusVehicle;

public abstract class Vehicle implements Availability {
    private String licensePlate;
    protected double maxCapacity;
    private double currentLoad;
    private StatusVehicle statusVehicle;
    public Vehicle(String licensePlate, double maxCapacity, StatusVehicle statusVehicle) {
        this.licensePlate = licensePlate;
        this.maxCapacity = maxCapacity;
        this.statusVehicle = statusVehicle;
    }

    public void reserve(){
        statusVehicle = StatusVehicle.RESERVED;
    }

    public void setLoad(double load){
        this.currentLoad = load;
        transport();
    }

    public void transport(){
        statusVehicle = StatusVehicle.IN_TRANSIT;
    }

    public void unload(){
        currentLoad = 0;
        statusVehicle = StatusVehicle.AVAILABLE;
    }

    public void maintenance(){
        statusVehicle = StatusVehicle.IN_MAINTENANCE;
    }

    public double getCurrentLoad(){
        return currentLoad;
    }

    public StatusVehicle getStatusVehicle(){
        return statusVehicle;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean supportsLoad(double load){
        return Double.compare(load, maxCapacity) <= 0;
    }

    @Override
    public boolean isAvailable(){
        return statusVehicle == StatusVehicle.AVAILABLE;
    }

    public abstract boolean canHandle(LoadType load);
}
