package br.edu.ifpb;

import java.io.IOException;
import java.nio.channels.FileLock;
import java.rmi.Remote;

public interface Identity extends Remote {

    int getIdentity(String INSTANCE_APP, int contador, int lastIdLocked) throws IOException;
}
