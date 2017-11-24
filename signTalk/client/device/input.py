"""
This is for the input module, This module is responsible for getting data
from hardware. It is currently setup to work with 'leap', 'emg'

This file can
  -> save data to files
  -> to rec offline
  -> to rec online
"""

from . import hardware_all

class Input():
    def __init__(self, input_type):
        """ initializes input object
            :param input_type: 'EMG', 'LEAP'
        """
        self.input_type = input_type
        self.stream = hardware_all[self.input_type]



