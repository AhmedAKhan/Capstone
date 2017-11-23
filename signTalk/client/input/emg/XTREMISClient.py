import socket
import sys

class XTREMISClient(object):
    def __init__(self, host="192.168.11.125",  port=9999):
        self.port = port;
        self.host = host;
        # Create a socket (SOCK_STREAM means a TCP socket)
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)


    def connect(self):
        self.sock.connect((self.host, self.port))
        return True;

    def disconnect(self):
        self.sock.close();

    def sendData(self, data):
        if(data):
            self.sock.sendall(data.encode())
        # Receive acknowledgement data from the server and shut down

    def waitForAck(self, textobj):
        received = self.sock.recv(1024)
        textobj.insert(INSERT, "" + received.decode() + "\n");
        #return received.decode();
