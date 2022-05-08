import random
from time import sleep


def get_avg_frequency():
    f = random.random() * 5
    return f


def main():
    try:
        # Loop until users quits with CTRL-C
        global times
        f = 0
        while True:
            speed = int(f * 20)
            print(f"Frequency: {f:.2f} Hz, Speed: {speed}")
            sleep(2)
            f = get_avg_frequency()

    except KeyboardInterrupt:
        print("\nExiting...")


if __name__ == "__main__":
    main()
