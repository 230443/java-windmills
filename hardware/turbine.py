#!/usr/bin/python
# --------------------------------------
#    ___  ___  _ ____
#   / _ \/ _ \(_) __/__  __ __
#  / , _/ ___/ /\ \/ _ \/ // /
# /_/|_/_/  /_/___/ .__/\_, /
#                /_/   /___/
#
#       Hall Effect Sensor
#
# This script tests the sensor on GPIO27.
#
# Author : Matt Hawkins
# Date   : 08/05/2018
#
# https://www.raspberrypi-spy.co.uk/
#
# --------------------------------------

# Import required libraries
import time
import datetime
import RPi.GPIO as GPIO
from buildhat import Motor


def sensorCallback(channel):
    # Called if sensor output changes
    timestamp = time.time()
    stamp = datetime.datetime.fromtimestamp(timestamp).strftime('%H:%M:%S:%f')
    if GPIO.input(channel):
        # No magnet
        # print("Sensor HIGH " + stamp)
        pass
    else:
        # Magnet
        print("Sensor LOW " + stamp)
        times.append(timestamp)


def get_avg_frequency(times):
    # Get partial difference between times
    if len(times) <= 1:
        return 0
    times_diff = []
    for i in range(len(times) - 1):
        times_diff.append(times[i + 1] - times[i])
    # Get average
    avg = sum(times_diff) / len(times_diff)
    # Convert to frequency
    return 1 / avg


def main():
    # Wrap main content in a try block so we can
    # catch the user pressing CTRL-C and run the
    # GPIO cleanup function. This will also prevent
    # the user seeing lots of unnecessary error
    # messages.

    # Get initial reading
    sensorCallback(HALL_PIN)

    try:
        # Loop until users quits with CTRL-C
        global times
        f = 0
        while True:
            speed = int(f * 20)
            print(f"Frequency: {f:.2f} Hz, Speed: {speed}")
            motor_a.run_for_seconds(3, speed=speed)
            f = get_avg_frequency(times=times)
            times = []

    except KeyboardInterrupt:
        # Reset GPIO settings
        GPIO.cleanup()


# Tell GPIO library to use GPIO references
GPIO.setmode(GPIO.BCM)

HALL_PIN = 27

print(f"Setup GPIO pin as input on GPIO {HALL_PIN}")

# Set Switch GPIO as input
# Pull high by default
GPIO.setup(HALL_PIN, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.add_event_detect(HALL_PIN, GPIO.BOTH,
                      callback=sensorCallback, bouncetime=100)

times = []
motor_a = Motor('A')


if __name__ == "__main__":
    main()
