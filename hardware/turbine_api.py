import datetime
from flask import Flask, Response
from threading import Timer
from . import Turbine
import json
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)
app.config["SQLALCHEMY_DATABASE_URI"] = "mysql://user:password@0.0.0.0:3306/windmillsdb"
db = SQLAlchemy(app)

turbine = Turbine(27, "A", update_interval=3)


@app.route("/status")
def get_status():
    status = {
        "isActive": turbine.is_running,
        "windSpeed": turbine.wind_speed,
        "turbineSpeed": turbine.turbine_speed,
    }
    return Response(json.dumps(status), mimetype="application/json")


@app.route("/turn_on")
def turn_on():
    turbine.turn_on()
    status = {
        "isActive": turbine.is_running,
    }
    return Response(json.dumps(status), mimetype="application/json")


@app.route("/turn_off")
def turn_off():
    turbine.turn_off()
    status = {
        "isActive": turbine.is_running,
    }
    return Response(json.dumps(status), mimetype="application/json")


class Status(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    date_time = db.Column(db.DateTime, nullable=False, default=datetime.datetime.utcnow)
    is_active = db.Column(db.String(80), nullable=False)
    turbine_speed = db.Column(db.Numeric)
    wind_speed = db.Column(db.Numeric)


def update_speed():
    global timer

    turbine.update_speed()
    db.session.add(
        Status(
            is_active=turbine.is_running,
            turbine_speed=turbine.turbine_speed,
            wind_speed=turbine.wind_speed,
        )
    )
    db.session.commit()

    timer = Timer(turbine.update_interval, update_speed)
    timer.start()


timer = Timer(turbine.update_interval, update_speed)
timer.start()


if __name__ == "__main__":
    app.run()
