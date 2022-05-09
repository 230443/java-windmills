#!/usr/bin/python3

import logging
from buildhat import Motor
from anemometer import Anemometer
import time


class Turbine:
    def __init__(self, hall_pin: int, motor_pin: str, speed_factor: float = 15, update_interval: float = 5):
        self.anemometer = Anemometer(
            hall_pin, keep_last_seconds=update_interval)
        self.motor = Motor(motor_pin)
        self.update_interval = update_interval
        self.is_running = False
        self.speed = 0
        self.speed_factor = speed_factor

    def update_speed(self):
        f = self.anemometer.get_frequency()
        self.speed = int(f * self.speed_factor)

        logging.info(f"Speed: {self.speed}")
        if self.speed == 0:
            self.motor.stop()
        else:
            self.motor.start(self.speed)


def main():
    turbine = Turbine(27, 'A')
    while True:
        turbine.update_speed()
        time.sleep(turbine.update_interval)


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO)
    main()
