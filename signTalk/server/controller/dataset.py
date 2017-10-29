from datetime import datetime
import os
import json

def save(data, letter="unknown"):
  """
    :data flask_save_data: this is the data to save
    :letter string: The letter the data corresponds to
  """
  path = "./dataset/"+letter
  if(not os.path.isdir(path)):
    os.makedirs(path)

  path += "/" + datetime.now().isoformat()
  with open(path, 'w') as outfile:
    json.dump(data, outfile)
  return True


def getDataset():
  ## TODO
  return

def viewDataset():
  ## TODO
  return
