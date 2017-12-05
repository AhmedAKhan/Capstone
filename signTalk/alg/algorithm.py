
from .classify.main import classify, setup_classifier
from .feature.extract import extract as feature_extract
from .models import models_all

print("inside the algorithm part ")

def setup(models):
  ## setup the files
  setup_classifier(models)
  return

def recognize(data, offline=True, model_type="tango"):
  model = models_all[model_type]
  result = []
  result = feature_extract(model["feature"], data)
  # if(not offline): result = filters(model["filters"], result)
  result = classify(model["classifier"], result)
  return result

def train(dataset, model_type="tango"):
  ## TODO
  return ""

def test(dataset, model_type="emg"):
  ## TODO
  return ""

### setup the models when the file loads
setup(["tango", "emg"])
