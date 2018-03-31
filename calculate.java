import java.util.*;
public class calculate {
    public static void main (String args[]) {
        System.out.println(getDistance(37.488767, 37.488201, 127.065692, 127.065573));
    }
    private static double R = 6378100;
    private static double getDistance (double latitude1, double latitude2, double longitude1, double longitude2) {
        double dLat = deg2rad(latitude2 - latitude1);
        double dLon = deg2rad(longitude2 - longitude1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return Math.abs(d);
    }
    
    private static double deg2rad (double deg) {
        return deg * (Math.PI / 180);
    }
}