"""
  this is the emg model, it is the representation of the
  data that the parser module returns and is used by the split, and feature modules
"""

class EMG_Model():
    """
    this is the emg model, it is the representation of the
    data that the parser module returns and is used by the split, and feature modules
    """
    def __init__(self, num_channels, data):
        self.num_channels = num_channels;
        self.data = data;
        return

    def get_data(self):
        """ this function will return the data to be used

        """
        return self.data

