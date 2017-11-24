"""
    This is the controller, called by the view. to run operations independent of the
    device being used.
"""


class Controller():
    def __init__(self, device):
        self.device = device
        return

    ## return a record object containing the info recording for the given duration
    def rec_start(self): pass
    def rec_stop(self): pass
    def rec_for_time(self): pass

    ## do stuff with the info
    def save(self, record): pass
    def recognize_offline(self, record): pass
    def recognize_online(self, record): pass
