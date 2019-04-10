
import java.rmi.registry.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainCliente {

    public static void main(String[] args) {
        List<String> ipsServer = new ArrayList<>();
        List<Integer> timeList = new ArrayList<>();
        ipsServer.add("");
        ipsServer.add("");

        try {
            BerkeleyServer bs1 = new BerkeleyServer();
            BerkeleyServer bs2 = new BerkeleyServer();
            timeList.add(bs1.getTime(LocalDateTime.now()));
            timeList.add(bs2.getTime(LocalDateTime.now()));

            /*for (String ip : ipsServer) {
                Registry registry = LocateRegistry.getRegistry(ip);
                BerkeleyServerInterface bs = (BerkeleyServerInterface) registry.lookup("BerkeleyServer");
                timeList.add(bs.getTime(LocalDateTime.now()));
            
            }*/
            Integer average = averageTime(timeList);
            System.out.println("Average: " + average);
            for (int i = 0; i < ipsServer.size(); i++) {
                //   Registry registry = LocateRegistry.getRegistry(ipsServer.get(i));
                // BerkeleyServerInterface bs = (BerkeleyServerInterface) registry.lookup("BerkeleyServer");

                //bs1.setTime(average + (timeList.get(i) * -1));

                //timeList.add(bs.getTime(LocalDateTime.now()));
            }
            Integer avg1 = (average + (timeList.get(0) * -1));
            Integer avg2 = (average + (timeList.get(1) * -1));
            System.out.println("Desvio:"+avg1);
            System.out.println("Desvio:"+avg2);
            bs1.setTime(avg1);
            bs2.setTime(avg2);
            System.out.println("Finish");
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
