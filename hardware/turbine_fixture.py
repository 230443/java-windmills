import random


class Turbine:
    def __init__(
        self,
        hall_pin: int,
        motor_pin: str,
        speed_factor: float = 15,
        update_interval: float = 5,
    ):
        self.speed_factor = speed_factor
        self.update_interval = update_interval
        self.turbine_speed = 0
        self.wind_speed = 0
        self.is_running = True

    def update_speed(self):
        # set random wind speed
        self.wind_speed = random.random() * 6
        if self.is_running:
            self.turbine_speed = int(self.wind_speed * self.speed_factor)

    def turn_on(self):
        self.is_running = True

    def turn_off(self):
        self.is_running = False
        self.turbine_speed = 0
