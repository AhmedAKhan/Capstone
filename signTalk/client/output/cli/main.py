"""
  this script is letting you work with our script without a gui, it just uses
  the command line. You can send it commands and it will directly print the results
  to console
"""


def printIntro():
    print("Starting program .... ")
    print("instructions may be given with the command help")

commands = {
    "help":
}

def processInput(command):
    command = command.lstrip()
    command = command.lower()
    commands[command]()

def main():
    printIntro()

    print("before loop")
    while True:
        command = input("command: ")
        processInput(command)


def start(input_source, queue, save=False):
    printIntro()
    main()



main()
