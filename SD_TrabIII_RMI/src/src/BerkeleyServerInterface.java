
import java.rmi.*;
import java.time.LocalDateTime;

public interface BerkeleyServerInterface extends Remote {

    public Integer getTime(LocalDateTime time) throws RemoteException;
    public void setTime(Integer time) throws RemoteException;
}
