package POO_WORKSHOP;

public class Driver implements Availability {
    private String name;
    private int idEmployee;
    private boolean available = true;
    private boolean licenseValid;
    private LoadType licenseType;

    public Driver(String name, int idEmployee, boolean licenseValid, LoadType licenseType) {
        this.name = name;
        this.idEmployee = idEmployee;
        this.licenseValid = licenseValid;
        this.licenseType = licenseType;
    }

    public String getName() {
        return name;
    }

    public boolean hasValidLicense(){
        return licenseValid;
    }

    @Override
    public boolean isAvailable(){
        return available;
    }

    public boolean hasRequiredLicense(LoadType licenseType){
        return licenseType == this.licenseType;
    }

    public void assign(){
        this.available = false;
    }

    public void release(){
        this.available = true;
    }

    @Override
    public String toString(){
        return "\nThe driver " + this.name + " identified by " + this.idEmployee + " ID number is "
                + (this.available ? "available" : "not available");
    }
}
