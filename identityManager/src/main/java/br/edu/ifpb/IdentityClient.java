//package br.edu.ifpb;
//
//import java.io.IOException;
//import java.rmi.NotBoundException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//
//public class IdentityClient {
//
//    public static void main(String[] args) throws IOException, NotBoundException {
//
//        Registry registry = LocateRegistry.getRegistry();
//        Identity identity = (Identity) registry.lookup("Identity");
//        System.out.println("Identity: " + identity.getIdentity("01"));
//    }
//}
