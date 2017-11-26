
from .classify.main import classify, setup_classifier
from .feature.extract import extract as feature_extract
from .models import models_all

print("inside the algorithm part ")

def setup(model_names):
  ## setup the files
  classifier_names = []
  for name in model_names:
    classifier_names += models_all[name]['classifier']
  setup_classifier(classifier_names)
  return

def recognize(data, offline=True, model_type="tango"):
  model = models_all[model_type]
  # print("models_all: ", models_all)
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
# setup(["tango", "emg"])
