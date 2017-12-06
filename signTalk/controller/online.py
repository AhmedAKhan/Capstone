
import threading
import time

from ..alg import algorithm as alg

class Sliding_Window(threading.Thread):
    def __init__(self, **options):
        self.duration = options.get('duration', 3)
        self.running = False
        self.delay_time = 10
        self.data_win = []

    def recognize_start(self, response, queue):
        """ run the algorithn on live data coming through the queue,
            put result in response, starts the recognition on a new thread
        """
        self.running = True
        self.data_win = []
        self.start()
        return
    def recognize_stop(self, response, queue):
        """ this will stop the thread to handle sign language recognition """
        self.running = False
        return

    def _update_data(self):
        """ updates the queue, if its max removes the last item """
        if(len(self.data_win) >= self.required_data):
            del self.data_win[0]

        if(self.queue.empty()): return
        self.data_win.append(self.queue.get())
    def run(self):
        """ thread process to run """
        while self.running:
            self.update_data()
            if(self.queue.empty() or self.data_win < self.required_data):
                time.delay(self.delay_time)
                continue

            res = alg.recognize(self.data_win)
            if(res != None): self.response['text'] = res
