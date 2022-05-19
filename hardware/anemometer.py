#!/usr/bin/python3

import time
import RPi.GPIO as GPIO

import logging
import threading

WIND_SPEED_FACTOR = 1


class Anemometer:
    def __init__(self, pin, keep_last_seconds=5):
        self.pin = pin
        self.times = []
        self.lock = threading.Lock()
        self.keep_last_seconds = keep_last_seconds

        logging.info(f"Setup GPIO pin as input on GPIO {self.pin}")

        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.pin, GPIO.IN, pull_up_down=GPIO.PUD_UP)
        GPIO.add_event_detect(
            self.pin,
            GPIO.FALLING,
            callback=self._sensor_callback,
            bouncetime=100,
        )

    def get_frequency(self):
        # Get the last few seconds of times
        limit = time.time() - self.keep_last_seconds

        self.lock.acquire()

        split = len(self.times)
        for i in range(len(self.times) - 1, -1, -1):
            if self.times[i] > limit:
                split = i
            else:
                break

        last_times = self.times[split:]

        # Remove all times that are older than keep_last_seconds
        self.times = last_times

        self.lock.release()

        # Get the partial difference between times
        if len(last_times) <= 1:
            return 0

        times_diff = []

        for i in range(len(last_times) - 1):
            times_diff.append(last_times[i + 1] - last_times[i])

        # Get average frequency from last few seconds
        f = 1 / (sum(times_diff) / len(times_diff))

        logging.info(f"Frequency: {f:.2f} Hz")
        return f

    def get_wind_speed(self):
        return self.get_frequency() * WIND_SPEED_FACTOR

    def _sensor_callback(self, channel):
        # Called if sensor output changes
        timestamp = time.time()

        self.lock.acquire()
        self.times.append(timestamp)
        self.lock.release()

        logging.debug(f"time_detected:{timestamp}")

    def __del__(self):
        GPIO.cleanup()


def main():
    a = Anemometer(pin=27)
    try:
        while True:
            time.sleep(1)
            f = a.get_frequency()
            print(f"Frequency: {f:.2f} Hz")

    except KeyboardInterrupt:
        GPIO.cleanup()
        print("\nExiting program.")


if __name__ == "__main__":
    logging.basicConfig(level=logging.DEBUG)
    main()
