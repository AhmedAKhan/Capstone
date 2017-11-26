
from . import HMM
from .svm import SVM

classifier_all = {
  "hmm": HMM,
  "svm":SVM
}

classifiers_loaded = {}

def setup_classifier(models):
  load_model = None ## get option from config file
  for model_name in models:
    print("model_name: ", model_name)
    model = classifier_all[model_name]()
    model.build_model()
    if(load_model != None): model.load_model(load_path)
    else: model._setup_training()
    classifiers_loaded[model_name] = model
  return

def classify(model_type, data):
  if(type(model_type) == list): model_type = model_type[0]
  if(model_type not in classifier_all):
    raise Exception("The classify model_type " + str(model_type) + " is not a valid option")

  return classifier_loaded[model_type].classify(data)
