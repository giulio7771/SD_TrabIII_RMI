package src;

import java.rmi.registry.*;

public class Main {

    public static void main(String[] args) {
        try {
            Registry registry = java.rmi.registry.LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            BerkeleyServerInterface sdrmi = new BerkeleyServer();
            registry.rebind("BerkeleyServer", sdrmi);
            System.out.println("BerkeleyServer " + sdrmi + " registrado e pronto para aceitar solicitações.");
        } catch (Exception ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
        }
    }
}
