package br.edu.ifpb;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App1 {
    private static String INSTANCE_APP;
    private static int CAPACITY_QUEUES;

    public static void main(String[] args) throws InterruptedException, IOException, NotBoundException {
        INSTANCE_APP = args[0];
        CAPACITY_QUEUES = Integer.valueOf(args[1]);

        final UserDao userDao = new UserDao();

        Registry registry = LocateRegistry.getRegistry();
        Identity identity = (Identity) registry.lookup("Identity");

        int contador = identity.getIdentity(INSTANCE_APP);
        final int limite = 1000;
        final long t0 = System.currentTimeMillis();
        final BlockingQueue<Integer> queueInsert = new ArrayBlockingQueue<Integer>(CAPACITY_QUEUES);
        final BlockingQueue<Integer> queueUpdate = new ArrayBlockingQueue<Integer>(CAPACITY_QUEUES);
        final BlockingQueue<Integer> queueDelete = new ArrayBlockingQueue<Integer>(CAPACITY_QUEUES);

        while(contador <= limite){

            queueInsert.put(contador);

            Runnable runnableInsert = new Runnable() {
                public void run() {
                    try {
                        Integer take = queueInsert.take();
                        User user = new User(take,"João");
                        userDao.insert(user);
                        queueUpdate.put(take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable runnableUpdate = new Runnable() {
                public void run() {
                    try {
                        Integer take = queueUpdate.take();
                        userDao.update(take);
                        queueDelete.put(take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable runnableDelete = new Runnable() {
                public void run() {

                    try {
                        Integer take = queueDelete.take();
                        userDao.delete(take);
                        if (take == limite) {
                            imprimirTempo(t0);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread threadInsert = new Thread(runnableInsert);
            Thread threadUpdate = new Thread(runnableUpdate);
            Thread threadDelete = new Thread(runnableDelete);
            threadInsert.start();
            threadUpdate.start();
            threadDelete.start();

            contador = identity.getIdentity(INSTANCE_APP);

        }
    }

    private static void imprimirTempo(long inicio){
        long t1 = System.currentTimeMillis();
        long tempoTotal = t1 - inicio;
        System.out.println("Durou: " + tempoTotal);
    }
}
