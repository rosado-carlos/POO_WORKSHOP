package POO_WORKSHOP.OBJECTS.VEHICLES;

import POO_WORKSHOP.ENUMERATIONS.LoadType;
import POO_WORKSHOP.ENUMERATIONS.StatusVehicle;

public class HeavyLoadTruck extends Vehicle {
    private int aditionalTrailerCount;
    private double extraCapacityPerTrailer;
    public HeavyLoadTruck(String licensePlate, double maxCapacity, StatusVehicle statusVehicle, int aditionalTrailerCount, double extraCapacityPerTrailer) {
        super(licensePlate,maxCapacity,statusVehicle);
        this.aditionalTrailerCount = aditionalTrailerCount;
        this.extraCapacityPerTrailer = extraCapacityPerTrailer;
    }

    @Override
    public boolean supportsLoad(double load){
        return Double.compare(load, calculateTotalCapacity()) <= 0;
    }

    public double calculateTotalCapacity(){
        return extraCapacityPerTrailer*aditionalTrailerCount + maxCapacity;
    }

    @Override
    public String toString() {
        return "\n[" + getLicensePlate() + "] Heavy Load Truck "
                + getStatusVehicle().toString().toLowerCase();
    }

    @Override
    public boolean canHandle(LoadType load){
        return (load == LoadType.HEAVY_LOAD);
    }
}
