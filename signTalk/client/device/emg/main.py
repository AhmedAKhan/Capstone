import threading
import socket
import sys

class EmgData():
    def __init__(self):
        self.queue = None
        self.listener = Listener()
        self.options = self.getDefaultOptions()
        self.name = "emg"
    def getDefaultOptions(self):
        return {}
    def start(self, newQueue, newOptions=None):
        self.queue = newQueue
        self.listener.queue = self.queue
        self.listener.running = True
        if(newOptions != None): self.options = newOptions
        self.listener.connect()
        self.listener.start()
    def stop(self):
        self.listener.running = True


class Listener(threading.Thread):
    def __init__(self, **opts):
        self.host = opts.get('host', "192.168.43.50")
        self.port = opts.get('port', 9999)
        self.queue = None
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.running = True
        threading.Thread.__init__(self)

    def connect(self):
        print("connecting ..")
        self.sock.connect((self.host, self.port))
        print("done")
        return True;

    def disconnect(self):
        self.sock.close();

    def sendData(self, data):
        if(data):
            self.sock.sendall(data.encode())
    def run(self):
        while self.running:
            print("listening for data . . . . ")
            data = self.sock.recv(1024)
            print("data: ", data)
            self.queue.put(data)


if __name__ == '__main__':
    import queue
    import time
    mem = queue.Queue()
    stream = EmgData()
    stream.start(mem)

    time.sleep(10)
    stream.stop()
    print("mem size: ", mem.qsize())

