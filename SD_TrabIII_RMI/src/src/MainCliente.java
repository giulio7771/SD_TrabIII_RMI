package src;

import java.rmi.registry.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainCliente {

    public static void main(String[] args) {
        List<Integer> timeList = new ArrayList<>();
        List<BerkeleyServerInterface> serverList = new ArrayList<BerkeleyServerInterface>();
        LocalDateTime currentTime = LocalDateTime.now();

        try {
            for (String ip : args) {
                Registry registry = LocateRegistry.getRegistry(ip.split(":")[0], Integer.parseInt(ip.split(":")[1]));
                BerkeleyServerInterface bs = (BerkeleyServerInterface) registry.lookup("BerkeleyServer");
                timeList.add(bs.getTime(currentTime));
                serverList.add(bs);
            }

            System.out.println(args.length + " servidores contactados");

            Integer average = averageTime(timeList);
            System.out.println("Media: " + average);

            for (int i = 0; i < args.length; i++) {
                BerkeleyServerInterface bs = serverList.get(i);
                bs.setTime(average + (timeList.get(i) * -1));
            }

            System.out.println("Fim!");
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
