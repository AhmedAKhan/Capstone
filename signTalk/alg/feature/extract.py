
from . import hog
# from . import hand_detection

feature_all = {
  # hand_detection": hand_detection,
  "hog": hog
}

def extract(feature_types, data):
  result = []
  for feature_name in feature_types:
    if(feature_name not in feature_all):
      raise Exception("The feature type " + feature_name + " is not a valid option")

    features_collected = feature_all[feature_name].calculate(data)
    result.append(features_collected)

  return result



