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
    def sed_data(self):
        self.listener.sendData("b")
    def sed_stop(self):
        self.listener.sendData("s")
    def stop(self):
        self.listener.running = False


class Listener(threading.Thread):
    def __init__(self, **opts):
        self.host = opts.get('host', "192.168.43.50")
        self.port = opts.get('port', 9999)
        self.queue = None
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.running = True
        self.cur_data = {"raw":""}
        self.sending_command = False
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
    def parse_data(self, raw_data):
        raw_data = raw_data.decode('ascii')
        raw_data = raw_data.strip()
        data = None
        if(len(raw_data) >= 10): return data
        if(raw_data == '65'):
            self.sending_command = True
            self.cur_data = {"raw":""}
            return data
        if(self.sending_command):
            if( raw_data == '192'):
                data = self.cur_data
                self.sending_command = False
            else: self.cur_data['raw'] += str(raw_data) + ','

        return data
    def run(self):
        while self.running:
            raw_data = self.sock.recv(4096)
            data = self.parse_data(raw_data)
            print("listening for data . . . . ", data, " raw: ", raw_data)
            if(data != None): self.queue.put(data)


if __name__ == '__main__':
    import queue
    import time
    mem = queue.Queue()
    stream = EmgData()
    print("startig the stream")
    stream.start(mem)
    time.sleep(10)
    print("sedig the data to start streami`g")
    # stream.sed_data()

    time.sleep(10)
    print("sedig to stop streami`g")
    stream.sed_stop()
    time.sleep(10)
    print("mem size: ", mem.qsize())
    stream.stop()

