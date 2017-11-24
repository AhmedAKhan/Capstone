"""
    This is the controller, called by the view. to run operations independent of the
    device being used.
"""

import queue
import time
import pickle

import online

import alg.algorithm as alg
from online import Sliding_Window


class Controller():
    def __init__(self, device):
        self.device = device
        self.queue = None
        self.recording = False

        alg.setup(device.name)
        return

    ## return a record object containing the info recording for the given duration
    def rec_start(self):
        if(self.recording): return
        self.recording = True
        self.queue = queue.Queue()
        device.start(self.queue)
        return
    def rec_stop(self):
        if(not self.recording): return
        self.recording = False
        device.stop()
        return self.queue
    def rec_for_time(self, duration):
        if(self.recording): return
        self.recording = True
        device.start(self.queue)
        time.sleep(duration)
        device.stop(self.queue)
        self.recording = False
        return self.queue


    ## do stuff with the info
    def save(self, record, save_path=None):
        """ saves the record """
        save_path = self.get_file_path(save_path)
        save_file = open(save_path, 'wb')
        pickle.dump(record, save_file)
    def load(self, path):
        file_path = self.get_file_path(path)
        read_file = open(file_path, 'rb')
        record = pickle.load(read_file)
        return record
    def _get_file_path(self, path):
        folder = "./dataset/"## TODO use user config file
        file_name = "temp"
        return folder + file_name

    def setup_alg(model_name):
        """ this function will setup the algorithm and all its components for use """
        alg.setup(model)

    def _dump_queue(self, queue):
        data = []
        for d in iter(queue.get,'STOP'):
            data.append(d)
            return data
    def recognize_offline(self, record):
        data = self._dump_queue(record)
        return alg.recognize(data, offline=True)
    def recognize_online(self, record):
        data = self._dump_queue(record)
        return alg.recognize(data, offline=False)

    def recognize_online_live(self, response):
        """ this will start recording and running the result through the algorithm,
            when the result is found, response['value'] will be given the result
        """
        self.rec_start()
        self.sw = Sliding_Window()
        self.sw.recognize_live(response, self.queue, alg.recognize)
    def recognize_online_stop(self):
        """ stop the live recognition """
        self.sw.stop()
        self.sw = None
