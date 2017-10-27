
default_tango = "tango_1"
default_emg = "emg_1"

models_all = {
  "tango_1":{
    "feature":[],
    "classifier":["hmm"]
  },
  "emg_1":{
    "feature":[],
    "classifier":["svm"]
  }
}

models_all["tango"] = models_all[default_tango]
models_all["emg"] = models_all[default_emg]
