
import java.rmi.*;
import java.rmi.server.*;
import java.time.LocalDateTime;

public class BerkeleyServer extends UnicastRemoteObject implements BerkeleyServerInterface {

    public BerkeleyServer() throws RemoteException {
    }
    
    LocalDateTime currentTime = LocalDateTime.now();

    @Override
    public Integer getTime(LocalDateTime clientTime) throws RemoteException {
        return convertTimeToInt(clientTime) - convertTimeToInt(LocalDateTime.now());
    }
    
    private Integer convertTimeToInt(LocalDateTime time){
        return time.getHour() * 60 + time.getMinute();
    }
    @Override
    public void setTime(Integer time) throws RemoteException {
        this.currentTime.plusMinutes(time);
    }
}
