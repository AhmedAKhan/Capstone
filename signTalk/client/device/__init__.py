
from .leap.main import LeapData
from .emg.main import EmgData

all_devices = {
  "EMG": EmgData,
  "LEAP": LeapData
}
