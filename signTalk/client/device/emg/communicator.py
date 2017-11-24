import XTREMISClient as xc
import time
from tkinter import *
import tkinter.scrolledtext as tkst
import threading

def waitForAck( xtremis, textobj ):
    prev_resp = ' ';
    num_newline = 0;
    while(True):
        received = xtremis.sock.recv(1024)
        resp = str(received, errors='ignore')

        textobj.insert(INSERT, resp);
        textobj.see(END);
        prev_resp = resp;

def initializeGUI(xtremis):
    master = Tk()

    Label(master, text="Command:").grid(row=0);

    global e1,e3
    e1 = Entry(master, width=92);
    e3 = tkst.ScrolledText(wrap=WORD)
    #e3.configure(state="disabled");

    e1.grid(row=0,column=1,sticky=W);
    e3.grid(columnspan=30,sticky=W);
    master.bind('<Return>', ReturnKeyHandler);

    Button(master, text='Send', command=onClickSend).grid(row=0,column=29,sticky=W)

    t = threading.Thread(target=waitForAck, args=(xtremis, e3));
    t.daemon = True;
    t.start();

    mainloop()
    return e1, e3;


def clear_text(entry):
    entry.delete(0, 'end')

def onClickSend():
    global e1,e3
    text = e1.get();
    xtremis.sendData(text);
    e3text = "Sent: " + str(text) + "\n";
    e3.insert(INSERT, e3text);

    # Send data
    if(text):
        xtremis.sendData(text);

        # Wait for acknowledgement
        #ack = xtremis.waitForAck();
        #e3.insert(INSERT, "" + ack + "\n");

        clear_text(e1);

def ReturnKeyHandler(event):
    global e1,e3
    text = e1.get();
    e3text = "Sent: "+ str(text) + "\n";
    e3.insert(INSERT, e3text);

    # Send data
    if(text):
        xtremis.sendData(text);

        # Wait for acknowledgement
        #ack = xtremis.waitForAck();
        #e3.insert(INSERT, ack + "\n");

        clear_text(e1);

if __name__.endswith('__main__'):
    # xtremis = xc.XTREMISClient("192.168.11.125",  9999);
    xtremis = xc.XTREMISClient("192.168.43.49",  9999);
    xtremis.connect();

    initializeGUI(xtremis);
#
#    xtremis.sendData('<');
#    time.sleep(2);
#    xtremis.sendData('b');
