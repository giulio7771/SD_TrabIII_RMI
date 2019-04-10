
import java.rmi.*;
import java.rmi.server.*;
import java.time.LocalDateTime;
import java.util.Random;

public class BerkeleyServer extends UnicastRemoteObject implements BerkeleyServerInterface {
    boolean controleSinal = false;
    public BerkeleyServer() throws RemoteException {
    }

    LocalDateTime currentTime = LocalDateTime.now();
    Integer acrescimos;

    @Override
    public Integer getTime(LocalDateTime clientTime) throws RemoteException {
        Random r = new Random();
        acrescimos = r.nextInt(30);
        if(!controleSinal){
            acrescimos = acrescimos * -1;
        }
        controleSinal = !controleSinal;
        Integer ret = convertTimeToInt(clientTime) - convertTimeToInt(LocalDateTime.now()) + acrescimos;
        System.out.println("Get Time: "+ret);
        return ret;
    }

    private Integer convertTimeToInt(LocalDateTime time) {
        return time.getHour() * 60 + time.getMinute();
    }
    private LocalDateTime getTime(){
        return currentTime;
    }

    @Override
    public void setTime(Integer time) throws RemoteException {
        this.currentTime = this.currentTime.plusMinutes(time + acrescimos);
        System.out.println("Server set time: "+getTime());
    }
}
