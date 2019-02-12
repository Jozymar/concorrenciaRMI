package br.edu.ifpb;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IdentityImpl extends UnicastRemoteObject implements Identity {

    int contador = 0;

    protected IdentityImpl() throws RemoteException {
        super();
    }

    public synchronized int getIdentity(String INSTANCE_APP) throws IOException {
        contador++;
        return contador;
    }
}
