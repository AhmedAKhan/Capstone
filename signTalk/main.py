"""
    This function will initialize the controller, GUI, and Input device,
    and connect them together.
"""

from controller import Controller
from client.device import all_devices
from client.views import all_views

def start(view_name, device_name):
    """ takes the name of the view and device you wand and starts the app """
    device = all_devices[device_name]()
    controller = Controller(device)
    view = all_views[view_name](controller)

start("CLI", "EMG")
