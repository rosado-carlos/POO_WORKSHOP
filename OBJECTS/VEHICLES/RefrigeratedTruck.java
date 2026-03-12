package POO_WORKSHOP.OBJECTS.VEHICLES;

import POO_WORKSHOP.ENUMERATIONS.LoadType;
import POO_WORKSHOP.ENUMERATIONS.StatusVehicle;

public class RefrigeratedTruck extends Vehicle {
    private int travelTemperature;
    public RefrigeratedTruck(String licensePlate, double maxCapacity, StatusVehicle statusVehicle, int travelTemperature) {
        super(licensePlate,maxCapacity,statusVehicle);
        this.travelTemperature = travelTemperature;
    }

    public int getTravelTemperature() {
        return travelTemperature;
    }

    public void setTravelTemperature(int travelTemperature) {
        this.travelTemperature = travelTemperature;
    }

    @Override
    public String toString() {
        return "\n[" + getLicensePlate() + "] Refrigerated Truck "
                + getStatusVehicle().toString().toLowerCase();
    }

    @Override
    public boolean canHandle(LoadType load){
        return (load == LoadType.REFRIGERATED_LOAD);
    }
}
