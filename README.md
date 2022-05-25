# Java Windmills Project

**AUTHORS**: Daniel Baraniak & Igor Stefański\
**DATE**:   May 2022

2021/2022 Academic Year\
Hogeschool PXL

## Mockups

![alt text](https://github.com/230443/java-windmills/blob/main/mockups/mockup1.jpg)

## Configuration

App can be tested without RPI by toggling comments in [hardware/**init**.py](hardware/__init__.py).

## Installation

### Prerequisites

Python requirements:

- `flask`
- `Flask-SQLAlchemy`
- [`mysqlclient`](https://pypi.org/project/mysqlclient/) - depending on system this module may require additional dependencies

Additional requirements for RaspberryPi:

- `buildhat`
- `RPi.GPIO`

## Running flask app

```sh
export FLASK_APP=<app>
flask run
```
