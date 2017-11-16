"""
    This module will control the overall flow of the program,
    Takes in the input (leap, emg) and an output (gui, command-line) and
    will gather informatino from the necessary source and display it in the appropriate manor

"""
import client.leap
import client.emg
input_all = {
    "tango": client.leap,
    "emg": client.emg
}

import client.gui
import client.cl
output_all = {
    "gui": client.gui,
    "cl": client.cl
}

import queue.Queue

def main(inputDevice, outputDevice="cl"):
    """  """
    my_input = input_all[inputDevice]
    output = output_all[outputDevice]
    sharedMemoryQueue = queue.Queue()

    my_input.setup()
    output.start(my_input, sharedMemoryQueue)



