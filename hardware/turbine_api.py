from flask import Flask
from threading import Timer
from . import Turbine
import json
import logging

app = Flask(__name__)

turbine = Turbine(27, 'A', update_interval=3)


@app.route("/status")
def get_status():
    status = {
        "is_running": turbine.is_running,
        "wind_speed": turbine.wind_speed,
        "turbine_speed": turbine.turbine_speed
    }
    return json.dumps(status)


@app.route("/turn_on")
def turn_on():
    turbine.turn_on()
    status = {
        "is_running": turbine.is_running,
    }
    return json.dumps(status)


@app.route("/turn_off")
def turn_off():
    turbine.turn_off()
    status = {
        "is_running": turbine.is_running,
    }
    return json.dumps(status)


def update_speed():
    global timer

    turbine.update_speed()

    timer = Timer(turbine.update_interval, update_speed)
    timer.start()


timer = Timer(turbine.update_interval, update_speed)
timer.start()


if __name__ == "__main__":
    app.run()
