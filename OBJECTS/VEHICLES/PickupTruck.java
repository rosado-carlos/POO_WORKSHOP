package POO_WORKSHOP.OBJECTS.VEHICLES;

import POO_WORKSHOP.ENUMERATIONS.LoadType;
import POO_WORKSHOP.ENUMERATIONS.StatusVehicle;

public class PickupTruck extends Vehicle {
    public PickupTruck(String licensePlate, double maxCapacity, StatusVehicle statusVehicle) {
        super(licensePlate,maxCapacity,statusVehicle);
    }

    @Override
    public String toString() {
        return "\n[" + getLicensePlate() + "] Pickup Truck "
                + getStatusVehicle().toString().toLowerCase();
    }

    @Override
    public boolean canHandle(LoadType load){
        return (load == LoadType.LIGHT_LOAD);
    }
}
