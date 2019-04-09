
import java.rmi.registry.*;

public class Main {

    public static void main(String[] args) {
        try {
            BerkeleyServerInterface sdrmi = new BerkeleyServer();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("BerkeleyServer", sdrmi);
            System.out.println("BerkeleyServer " + sdrmi + " registrado e pronto para aceitar solicitações.");
        } catch (Exception ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
        }
    }
}
