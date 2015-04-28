package com.service.validate.demo_app;

import com.pages.demo_app.Serve;
import org.apache.log4j.Logger;

/**
 * Created by asih on 09/03/2015.
 */
public class ServeValidator {

    private static final ServeValidator INSTANCE = new ServeValidator();
    private static final Logger logger = Logger.getLogger(ChatValidator.class);
    private Serve serve = new Serve(true, true);

    private ServeValidator(){
    }

    public static ServeValidator getInstance(){
        return INSTANCE;
    }

}
