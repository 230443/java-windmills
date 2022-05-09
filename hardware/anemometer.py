#!/usr/bin/python3

import time
import RPi.GPIO as GPIO

import logging
import threading


class Anemometer:
    def __init__(self, pin, keep_last_seconds=5):
        self.pin = pin
        self.times = []
        self.lock = threading.Lock()
        self.keep_last_seconds = keep_last_seconds

        logging.info(f"Setup GPIO pin as input on GPIO {self.pin}")

        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.pin, GPIO.IN, pull_up_down=GPIO.PUD_UP)
        GPIO.add_event_detect(self.pin,
                              GPIO.FALLING,
                              callback=self.sensorCallback,
                              bouncetime=100,
                              )

    def get_frequency(self):
        # Get the last few seconds of times

        limit = time.time() - self.keep_last_seconds
        split = len(self.times)

        self.lock.acquire()

        split = len(self.times)

        # Remove all times that are older than keep_last_seconds
        for i in range(len(self.times) - 1, -1, -1):
            logging.debug(f"i:{i}")
            if self.times[i] < limit:
                logging.debug(f"=============:{self.times[i]}")
                split = i+1
                break

        logging.debug(f"plit:{split}")
        last_times = self.times[split:]
        self.times = last_times

        self.lock.release()

        logging.debug(f"last_times:{last_times}")

        # Get the partial difference between times
        if len(last_times) <= 1:
            return 0

        times_diff = []

        for i in range(len(last_times) - 1):
            times_diff.append(last_times[i + 1] - last_times[i])

        # Get average frequency from last few seconds
        f = 1 / (sum(times_diff) / len(times_diff))

        return f

    def sensorCallback(self, channel):
        # Called if sensor output changes
        timestamp = time.time()

        self.lock.acquire()
        self.times.append(timestamp)
        self.lock.release()

        logging.debug(f"time_detected:{timestamp}")


def main():
    # Wrap main content in a try block so we can
    # catch the user pressing CTRL-C and run the
    # GPIO cleanup function. This will also prevent
    # the user seeing lots of unnecessary error
    # messages.

    a = Anemometer(pin=27)
    try:
        while True:
            time.sleep(1)
            f = a.get_frequency()
            print(f"Frequency: {f:.2f} Hz")

    except KeyboardInterrupt:
        # Reset GPIO settings
        GPIO.cleanup()


if __name__ == "__main__":
    logging.basicConfig(level=logging.DEBUG)
    main()
