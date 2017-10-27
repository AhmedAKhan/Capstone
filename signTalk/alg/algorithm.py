
from classify.main import classify
from feature.extract import extract as feature_extract
from models import models_all

def recognize(data, offline=True, model_type="tango"):
  model = models_all[model_type]
  result = []
  result = feature_extract(model["feature"], data)
  # if(not offline): result = filters(model["filters"], result)
  result = classify(model["classifier"], result)
  return result

def train(dataset, model_type="tango"):
  return ""

def test(dataset, model_type="emg"):
  return ""
