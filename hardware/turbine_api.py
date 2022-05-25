import datetime
import time
import threading
from flask import Flask, Response, request
from werkzeug import exceptions
from . import Turbine
import json
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)

SAVE_TO_DB_PERIOD = 5  # Save every nth update to DB
app.config["SQLALCHEMY_DATABASE_URI"] = "mysql://user:password@0.0.0.0:3308/windmillsdb"
turbine = Turbine(27, "A", update_interval=3)


# Endpoints


@app.route("/status", methods=["POST", "GET"])
def get_status():
    if request.method == "POST":
        data = request.get_json()
        try:
            if data["isActive"] is True:
                turbine.turn_on()
            else:
                turbine.turn_off()
        except KeyError:
            raise exceptions.BadRequest("Missing isActive key")

    status = {
        "isActive": turbine.is_running,
        "windSpeed": turbine.wind_speed,
        "turbineSpeed": turbine.turbine_speed,
    }

    return Response(json.dumps(status), mimetype="application/json")


# Database

db = SQLAlchemy(app)


class Status(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    date_time = db.Column(db.DateTime, nullable=False, default=datetime.datetime.utcnow)
    is_active = db.Column(db.String(80), nullable=False)
    turbine_speed = db.Column(db.Numeric)
    wind_speed = db.Column(db.Numeric)


def save_status_to_db():
    db.session.add(
        Status(
            is_active=turbine.is_running,
            turbine_speed=turbine.turbine_speed,
            wind_speed=turbine.wind_speed,
        )
    )
    db.session.commit()


# Update speed in the background


def update_speed():
    counter = 0

    while True:
        turbine.update_speed()

        if counter % SAVE_TO_DB_PERIOD == 0:
            save_status_to_db()
            counter = 0

        counter += 1
        time.sleep(turbine.update_interval)


turbine_thread = threading.Thread(target=update_speed, daemon=True)
turbine_thread.start()

if __name__ == "__main__":
    app.run()
