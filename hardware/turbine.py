import logging
from buildhat import Motor
from . import Anemometer


class Turbine:
    def __init__(
        self,
        hall_pin: int,
        motor_pin: str,
        speed_factor: float = 15,
        update_interval: float = 5,
    ):
        self.anemometer = Anemometer(hall_pin, keep_last_seconds=update_interval)
        self.motor = Motor(motor_pin)
        self.update_interval = update_interval
        self.__is_running = True
        self.turbine_speed = 0
        self.wind_speed = 0
        self.speed_factor = speed_factor

    @property
    def is_running(self):
        return self.__is_running

    def update_speed(self):
        self.wind_speed = self.anemometer.get_wind_speed()
        self.turbine_speed = int(self.wind_speed * self.speed_factor)
        logging.info(f"Speed: {self.turbine_speed}")

        if not self.is_running:
            return

        if self.turbine_speed == 0:
            self.motor.stop()
        else:
            self.motor.start(self.turbine_speed)

    def turn_on(self):
        self.__is_running = True
        self.update_speed()

    def turn_off(self):
        self.__is_running = False
        self.motor.stop()
