package com.pipefy.factory;

import com.pipefy.config.LocalConfig;
import com.pipefy.config.TestConfig;

public class CapabilitiesFactory {

    public static TestConfig getConfig(String environment) {
        switch (environment) {
            case "local" : return new LocalConfig();
            default : throw new RuntimeException("Invalid environment: " + environment);
        }
    }

}
