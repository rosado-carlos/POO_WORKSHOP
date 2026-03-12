package POO_WORKSHOP.LOGIC;

import POO_WORKSHOP.ENUMERATIONS.LoadType;
import POO_WORKSHOP.ENUMERATIONS.StatusVehicle;
import POO_WORKSHOP.EXCEPTIONS.NoAvailableQualifiedDriversException;
import POO_WORKSHOP.EXCEPTIONS.NoAvailableQualifiedRoutesException;
import POO_WORKSHOP.EXCEPTIONS.NoAvailableQualifiedVehiclesException;
import POO_WORKSHOP.EXCEPTIONS.TripNotFoundException;
import POO_WORKSHOP.OBJECTS.Driver;
import POO_WORKSHOP.OBJECTS.Route;
import POO_WORKSHOP.OBJECTS.Trip;
import POO_WORKSHOP.OBJECTS.VEHICLES.HeavyLoadTruck;
import POO_WORKSHOP.OBJECTS.VEHICLES.PickupTruck;
import POO_WORKSHOP.OBJECTS.VEHICLES.RefrigeratedTruck;
import POO_WORKSHOP.OBJECTS.VEHICLES.Vehicle;
import POO_WORKSHOP.TOOLS.TransportUtils;

public class TransportManager {
    private Driver[] drivers = {
            new Driver("Alicia Ramirez", 101, true, LoadType.HEAVY_LOAD),
            new Driver("Alfonso Gomez", 102, true, LoadType.REFRIGERATED_LOAD),
            new Driver("Carlos Perez", 103, false, LoadType.LIGHT_LOAD),
            new Driver("Diana Paredes", 104, true, LoadType.HEAVY_LOAD),
            new Driver("Emilio Hernandez", 105, true, LoadType.REFRIGERATED_LOAD),
            new Driver("Fernando Espriella", 106, true, LoadType.LIGHT_LOAD),
            new Driver("Jorge Lopez", 107, true, LoadType.HEAVY_LOAD),
            new Driver("Hanibal Arevalo", 108, false, LoadType.REFRIGERATED_LOAD),
            new Driver("Ivan Jaramillo", 109, true, LoadType.LIGHT_LOAD),
            new Driver("Julia Martinez", 110, true, LoadType.HEAVY_LOAD),
            new Driver("Kevin Bedolla", 111, false, LoadType.REFRIGERATED_LOAD),
            new Driver("Laura Restrepo", 112, true, LoadType.LIGHT_LOAD),
            new Driver("Mario Palacios", 113, true, LoadType.HEAVY_LOAD),
            new Driver("Sergio Ortiz", 114, false, LoadType.REFRIGERATED_LOAD),
            new Driver("Oscar Paez", 115, true, LoadType.LIGHT_LOAD)
    };

    private Vehicle[] vehicles = {
            new PickupTruck("AAC463", 1000, StatusVehicle.AVAILABLE),
            new PickupTruck("EEA122", 1200, StatusVehicle.AVAILABLE),
            new PickupTruck("ASD923", 1100, StatusVehicle.IN_MAINTENANCE),
            new PickupTruck("SDF104", 1300, StatusVehicle.AVAILABLE),
            new RefrigeratedTruck("ASY281", 800, StatusVehicle.AVAILABLE, -5),
            new RefrigeratedTruck("GAS245", 900, StatusVehicle.IN_MAINTENANCE, -10),
            new RefrigeratedTruck("THD453", 850, StatusVehicle.AVAILABLE, -7),
            new RefrigeratedTruck("RRT864", 950, StatusVehicle.AVAILABLE, -12),
            new HeavyLoadTruck("UAF721", 2000, StatusVehicle.AVAILABLE, 2, 500),
            new HeavyLoadTruck("KHD762", 2500, StatusVehicle.AVAILABLE, 3, 600),
            new HeavyLoadTruck("HLR123", 2200, StatusVehicle.AVAILABLE, 2, 700),
            new HeavyLoadTruck("HLM384", 2800, StatusVehicle.IN_MAINTENANCE, 3, 800),
            new PickupTruck("PKK665", 1400, StatusVehicle.AVAILABLE),
            new PickupTruck("PKA226", 1250, StatusVehicle.AVAILABLE),
            new RefrigeratedTruck("RMF125", 1000, StatusVehicle.AVAILABLE, -8),
            new RefrigeratedTruck("FRM436", 1050, StatusVehicle.AVAILABLE, -6),
            new HeavyLoadTruck("FAS105", 3000, StatusVehicle.AVAILABLE, 4, 750),
            new HeavyLoadTruck("YUS356", 3200, StatusVehicle.AVAILABLE, 4, 800)
    };

    private Route[] routes = {
            new Route(1, new String[]{"Bogota", "Soacha", "Girardota"}, 5),
            new Route(2, new String[]{"Medellin", "Envigado", "Rionegro", "Bello"}, 4),
            new Route(3, new String[]{"Cali", "Palmira", "Buenaventura"}, 3),
            new Route(4, new String[]{"Barranquilla", "Soledad", "Malambo", "Puerto Colombia"}, 6),
            new Route(5, new String[]{"Cartagena", "Turbaco", "Santa Catalina"}, 4),
            new Route(6, new String[]{"Bucaramanga", "Floridablanca"}, 3),
            new Route(7, new String[]{"Pereira", "Dosquebradas", "La Virginia", "Santuario"}, 4),
            new Route(8, new String[]{"Manizales", "Chinchina", "Neiva"}, 3),
            new Route(9, new String[]{"Ibague", "Espinal", "Honda"}, 4),
            new Route(10, new String[]{"Santa Marta", "Cienaga", "Fundacion", "Sitionuevo", "El Banco"}, 5)
    };

    private Trip[] trips = new Trip[5];
    private int tripCount;

    public TransportManager(){}

    public Trip processRequest(double quantity, LoadType loadType, String city)
            throws NoAvailableQualifiedDriversException,
            NoAvailableQualifiedVehiclesException,
            NoAvailableQualifiedRoutesException {
        Driver driver = getAvailableDriver(loadType);
        if (driver == null) {
            throw new NoAvailableQualifiedDriversException("No available qualified drivers for load type: " + loadType);
        }
        Vehicle vehicle = getAvailableVehicle(loadType, quantity);
        if (vehicle == null) {
            throw new NoAvailableQualifiedVehiclesException("No available vehicles that support the requested load");
        }
        Route route = getAvailableRoute(city);
        if (route == null) {
            throw new NoAvailableQualifiedRoutesException("No available routes to destination: " + city);
        }
        vehicle.reserve();
        vehicle.setLoad(quantity);
        route.addVehicle();
        driver.assign();
        Trip trip = new Trip(quantity, loadType, city, driver, vehicle, route);
        saveTrip(trip);
        return trip;
    }

    public void finishTrip(int idTrip) throws TripNotFoundException {
        int idx = TransportUtils.findTripIndexById(trips, idTrip);
        if (idx < 0) {
            throw new TripNotFoundException("Trip not found with id: " + idTrip);
        }
        Trip trip = trips[idx];
        if (trip.isFinished()) {
            return;
        }
        trip.finishTrip();
        trip.getDriver().release();
        trip.getVehicle().unload();
        trip.getRoute().removeVehicle();
    }

    public Driver getAvailableDriver(LoadType loadType){
        for (Driver driver : this.drivers){
            if (driver.hasRequiredLicense(loadType) && driver.hasValidLicense() && driver.isAvailable()){
                return driver;
            }
        }
        return null;
    }

    public Vehicle getAvailableVehicle(LoadType loadType, double quantity) {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.isAvailable() && vehicle.supportsLoad(quantity) && vehicle.canHandle(loadType)) {
                return vehicle;
            }
        }
        return null;
    }

    public Route getAvailableRoute(String destination) {
        for (Route route : this.routes) {
            if (route.containsCity(destination) && route.hasCapacity()) {
                return route;
            }
        }
        return null;
    }

    public void saveTrip(Trip trip) {
        if (tripCount >= trips.length) {
            trips = TransportUtils.resizeTrips(trips);
        }
        trips[tripCount] = trip;
        tripCount++;
    }

    public String getTrip(int idTrip) throws TripNotFoundException {
        int idx = TransportUtils.findTripIndexById(trips, idTrip);
        if  (idx >= 0) {
            return trips[idx].toString();
        }else{
            throw new TripNotFoundException("Trip not found with id: " + idTrip);
        }
    }

}
