import sys
sys.path.append("..")

"""
  This model should be able to train any model, test any model
  -> should be able to take arguments specifying, model to train/test,
  -> whether to train or test
  -> save and load path if needed
  -> batch size
  -> num steps to train
  -> can use the config file for the model BUT any of the parameters should can
    be overwritten by a command line option

"""

from signTalk.alg.classify.main import classifier_all

def train_model(model_name):
  model = classifier_all[model_name]
  model._setup_training()

  ## TODO get dataset
  dataset = None

  model.train_model(dataset)


def main():
  ## TODO get arguments, apply them properly
  ##


if __name__ == "__main__":
  main()
