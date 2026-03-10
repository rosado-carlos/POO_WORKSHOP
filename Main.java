package POO_WORKSHOP;

public class Main {
    public static void main(String[] args) {
        //1.Recuerda preguntar si estan bien las funciones, dado que se eliminaron las de debug.
        //2.Que tal el TransportManager.
        //3.Recuerda preguntar tambien por el alcance, por que se quito muchas cosas que solucionaban
        //situaciones por que no eran el problema del ejercicio.
        //4.Hay problema por usar el operador terniario? que se uso en driver.
        //5.Pregunta tambien por el getIdTrip ya que la vdd no era necesario, puro visaje, pues lo de las letras.
        //6.Que tan bien esta lo de los getters, ya que solo le puse a los que iba a usar por fuera, conectado
        //con la primera pregunta pues lo puse mientras testeaba y ya luego los quite.
        //7.Pregunta por el localdate por que es innecesario y tambien es puro visaje.
        //8.Pregunta si deberia obligatoriamente separar el transportManager del proccessRequest
        //...
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

