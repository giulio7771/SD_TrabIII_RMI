
import java.rmi.*;
import java.rmi.server.*;
import java.time.LocalDateTime;
import java.util.Random;

public class BerkeleyServer extends UnicastRemoteObject implements BerkeleyServerInterface {
    public BerkeleyServer() throws RemoteException {
    
    }

    LocalDateTime currentTime;
    Integer acrescimos;

    @Override
    public Integer getTime(LocalDateTime clientTime) throws RemoteException {
        Random r = new Random();
        acrescimos = r.nextInt(30);
        Integer ret = convertTimeToInt(clientTime) - convertTimeToInt(getTime()) + acrescimos;
        currentTime = getTime().plusMinutes(acrescimos);
        System.out.println("Get Time: "+ret);
        return ret;
    }

    private Integer convertTimeToInt(LocalDateTime time) {
        return time.getHour() * 60 + time.getMinute();
    }
    private LocalDateTime getTime(){
        return currentTime = LocalDateTime.now();
    }

    @Override
    public void setTime(Integer time) throws RemoteException {
        System.out.println("Server old time: "+this.currentTime);
        this.currentTime = this.currentTime.plusMinutes(time);
        System.out.println("Server new time: "+getTime());
    }
}
