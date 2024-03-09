

public class Pais {
    
    public static final int NORTE = 1;
    public static final int SUL = 0;
    
    private String name;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private int hemisphere;
    
    public Pais(String name){
        this.name = name;
        this.confirmed = 0;
        this.deaths = 0;
        this.recovered = 0;
        this.active = 0;
        this.setHemisphere(name);
        
    }

    public String getName() {
        return name;
    }

    public int getConfirmed() {
        return confirmed;
    }
    
    public void addConfirmed(int confirmed){
        this.confirmed = this.confirmed + confirmed;
    }

    public int getDeaths() {
        return deaths;
    }
    
    public void addDeaths(int deaths){
        this.deaths = this.deaths + deaths;
    }

    public int getRecovered() {
        return recovered;
    }
    
    public void addRecovered(int recovered){
        this.recovered = this.recovered + recovered;
    }

    public int getActive() {
        return active;
    }
    
    public void addActive(int active){
        this.active = this.active + active;
    }

    public int getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(String name) {
        
    }
    
    
}
