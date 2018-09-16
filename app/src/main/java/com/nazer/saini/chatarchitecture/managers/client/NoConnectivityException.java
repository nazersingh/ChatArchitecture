package com.nazer.saini.chatarchitecture.managers.client;

import java.io.IOException;



public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}