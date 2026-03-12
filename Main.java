package POO_WORKSHOP;

import POO_WORKSHOP.ENUMERATIONS.LoadType;
import POO_WORKSHOP.EXCEPTIONS.NoAvailableQualifiedDriversException;
import POO_WORKSHOP.EXCEPTIONS.NoAvailableQualifiedRoutesException;
import POO_WORKSHOP.EXCEPTIONS.NoAvailableQualifiedVehiclesException;
import POO_WORKSHOP.EXCEPTIONS.TripNotFoundException;
import POO_WORKSHOP.LOGIC.TransportManager;
import POO_WORKSHOP.OBJECTS.Trip;

public class Main {
    public static void main(String[] args) {
        TransportManager manager = new TransportManager();

        //1) Succesful Case
        try {
            Trip trip = manager.processRequest(900, LoadType.HEAVY_LOAD, "Bogota");
            System.out.println("Trip created");
            System.out.println(trip.toString());

            System.out.println("\nSearch by ID");
            System.out.println(manager.getTrip(trip.getIdTrip()));

            manager.finishTrip(trip.getIdTrip());
            System.out.println("\nFinished trip correctly");

        } catch (NoAvailableQualifiedDriversException |
                 NoAvailableQualifiedVehiclesException |
                 NoAvailableQualifiedRoutesException |
                 TripNotFoundException e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Request Processed.");
        }

        //2) TripNotFoundException Case
        try {
            int idInvalid = 999;
            System.out.println(manager.getTrip(idInvalid));
        } catch (TripNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("****");
        }

        // 3) NoAvailableQualifiedVehiclesException Case
        try {
            manager.processRequest(2000, LoadType.REFRIGERATED_LOAD, "Manizales");
        } catch (NoAvailableQualifiedDriversException |
                 NoAvailableQualifiedVehiclesException |
                 NoAvailableQualifiedRoutesException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Request Processed.");
        }

        // 4) NoAvailableQualifiedDriversException Case
        try {
            manager.processRequest(700, LoadType.REFRIGERATED_LOAD, "Manizales");
            manager.processRequest(600, LoadType.REFRIGERATED_LOAD, "Manizales");
            manager.processRequest(200, LoadType.REFRIGERATED_LOAD, "Manizales");
        } catch (NoAvailableQualifiedDriversException |
                 NoAvailableQualifiedVehiclesException |
                 NoAvailableQualifiedRoutesException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Request Processed.");
        }

        // 5) NoAvailableQualifiedRoutesException Case
        try {
            manager.processRequest(500, LoadType.HEAVY_LOAD, "Leticia");
        } catch (NoAvailableQualifiedDriversException |
                 NoAvailableQualifiedVehiclesException |
                 NoAvailableQualifiedRoutesException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Request Processed.");
        }
    }
}

