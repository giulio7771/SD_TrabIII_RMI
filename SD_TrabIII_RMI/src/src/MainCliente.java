
import java.rmi.registry.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainCliente {

    public static void main(String[] args) {
        List<String> ipsServer = new ArrayList<>();
        List<Integer> timeList = new ArrayList<>();
        try {
            for (String ip : ipsServer) {
                Registry registry = LocateRegistry.getRegistry(ip);
                BerkeleyServerInterface bs = (BerkeleyServerInterface) registry.lookup("BerkeleyServer");
                timeList.add(bs.getTime(LocalDateTime.now()));
            }

            Integer average = averageTime(timeList);
            for (int i = 0; i < ipsServer.size(); i++) {
                Registry registry = LocateRegistry.getRegistry(ipsServer.get(i));
                BerkeleyServerInterface bs = (BerkeleyServerInterface) registry.lookup("BerkeleyServer");
                bs.setTime((average - timeList.get(i)) * -1);
                
                timeList.add(bs.getTime(LocalDateTime.now()));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static Integer averageTime(List<Integer> args) {
        Integer sum = 0;
        for (Integer i : args) {
            sum += i;
        }
        return sum / args.size();
    }
}
