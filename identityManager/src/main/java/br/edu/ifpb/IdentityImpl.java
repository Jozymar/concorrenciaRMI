package br.edu.ifpb;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IdentityImpl extends UnicastRemoteObject implements Identity {

    final UserDao userDao = new UserDao();

    protected IdentityImpl() throws RemoteException {
        super();
    }

    public int getIdentity(String INSTANCE_APP, int contador, int lastIdLocked) throws IOException {
        //RESERVAR NOVO ID
        contador = lastIdLocked + 1;
        userDao.lockId(contador, INSTANCE_APP);
        return contador;
    }
}
