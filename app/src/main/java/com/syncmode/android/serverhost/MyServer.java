package com.syncmode.android.serverhost;

import java.io.IOException;


//sdsd

/**
 * Created by andrei on 7/30/15.
 */
public class MyServer extends NanoHTTPD {
    private final static int PORT = 2303;

    public MyServer() throws IOException {
        super(PORT);
        start();
        System.out.println( "\nAcesse no browser http://localhost:2303/ \n" );
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h3>MESSIAS PINHEIRO</h3>\n";
        msg += "<p>We serve " + session.getUri() + " !</p>";
        return newFixedLengthResponse( msg + "</body></html>\n" );
    }
}
