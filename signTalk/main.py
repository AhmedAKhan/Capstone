"""
    This module will control the overall flow of the program,
    Takes in the input (leap, emg) and an output (gui, command-line) and
    will gather informatino from the necessary source and display it in the appropriate manor

"""
import client.input_all as input_all
import client.output_all as output_all
import queue.Queue

def main(inputDevice, outputDevice="cl"):
    """  """
    my_input = input_all[inputDevice]
    output = output_all[outputDevice]
    sharedMemoryQueue = queue.Queue()

    my_input.setup()
    output.start(my_input, sharedMemoryQueue)



