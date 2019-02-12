package br.edu.ifpb;

import java.io.IOException;
import java.rmi.Remote;

public interface Identity extends Remote {

    int getIdentity(String INSTANCE_APP) throws IOException;
}
