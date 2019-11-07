package com.mike.oop.http;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Server s = new Server(8080);
        try {
            s.StartServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
