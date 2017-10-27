
import classify.HMM as HMM

classifier_all = {
  "hmm": HMM
}

def classify(model_type, data):
  if(type(model_type) == list): model_type = model_type[0]
  if(model_type not in classifier_all):
    raise Exception("The classify model_type " + str(model_type) + " is not a valid option")

  return classifier_all[model_type].classify(data)



