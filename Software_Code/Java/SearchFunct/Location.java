public class Location
{
    String address;
    double latitude;
    double longitude;
    public Location(){
        
    }
    
    public void setAddress(String x){
        address = x;
    }
    
    public void setCoords(double x, double y){
        latitude = x;
        longitude = x;
    }

    public double getLatitude(){
        return latitude;
    }
    
    public double getLongitude(){
        return longitude;
    }
}
