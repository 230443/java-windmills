package be.webtechie.gpio;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

public class LedController {
    private static final int PIN_LED = 22; // PIN 15 = BCM 22

    Context pi4j = Pi4J.newAutoContext();

    private DigitalOutput led;

    private static LedController instance = null; 

    public static LedController getInstance()
    {
        if (instance == null)
            instance = new LedController();
 
        return instance;
    }

    private LedController() {
        var ledConfig = DigitalOutput.newConfigBuilder(pi4j)
                .id("led")
                .name("LED Flasher")
                .address(PIN_LED)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output");
        led = pi4j.create(ledConfig);
    }

    public void swithLed() {
        if (led.isOff()) {
            led.on();
        } else {
            led.off();
        }
    }
}
