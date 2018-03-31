import java.util.*;
import java.io.*;
public class calculate {
    public static double currentLatitude = 0.0D;
    public static double currentLongitude = 0.0D;
    static class ImageInfo implements Comparable<ImageInfo> {
        public String uri;
        public double latitude, longitude;
        public ImageInfo (String uri, double latitude, double longitude) {
            this.uri = uri;
            this.latitude = latitude;
            this.longitude = longitude;
        }
        @Override
        public int compareTo (ImageInfo x) {
            double thisDistance = getDistance(currentLatitude, this.latitude, currentLongitude, this.longitude);
            double xDistance = getDistance(currentLatitude, x.latitude, currentLongitude, x.longitude);
            return (int) (thisDistance - xDistance);
        }
    }
    static class ImageInfoList implements Comparable<ImageInfoList> {
        public ArrayList<ImageInfo> imageInfoList;
        public long dateTime;
        public ImageInfoList (ArrayList<ImageInfo> imageInfoList, long dateTime) {
            this.imageInfoList = imageInfoList;
            this.dateTime = dateTime;
        }
        @Override
        public int compareTo (ImageInfoList x) {
            return (int) (x.dateTime - this.dateTime);
        }
    }
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

    private static double convertDMSToDD (String str1, String str2, String str3) {
        String degreeString[] = str1.split("/");
        String minuteString[] = str2.split("/");
        String secondString[] = str3.split("/");
        double degree = Double.parseDouble(degreeString[0]) / Double.parseDouble(degreeString[1]);
        double minute = Double.parseDouble(minuteString[0]) / Double.parseDouble(minuteString[1]) / 60;
        double second = Double.parseDouble(secondString[0]) / Double.parseDouble(secondString[1]) / 3600;
        return degree + minute + second;
    }

    private static byte[] objectToBytes (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos); 
            oos.writeObject(obj);
            oos.flush(); 
            oos.close(); 
            bos.close();
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            //TODO: Handle the exception
        }
        return bytes;
    }
    
    private static Object bytesToObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
        } catch (IOException ex) {
            //TODO: Handle the exception
        } catch (ClassNotFoundException ex) {
            //TODO: Handle the exception
        }
        return obj;
    }
    public static ArrayList<ImageInfoList> getImageListByDate (ArrayList<ImageInfoList> tempLists, Date startDate, Date endDate) {
        ArrayList<ImageInfoList> imageInfoLists = new ArrayList<>();
        long gte = startDate.getTime();
        long lte = endDate.getTime();
        for (ImageInfoList element : tempLists) {
            if (gte <= element.dateTime && element.dateTime <= lte) imageInfoLists.add(element);
        }
        return imageInfoLists;
    }
}