package br.edu.ifpb;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IdentityServer {

    IdentityServer() throws RemoteException {

        Identity identity = new IdentityImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("Identity", identity);
    }

    public static void main(String[] args) throws RemoteException {
        new IdentityServer();
    }
}
