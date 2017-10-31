import json
import os

def json_route(data, route):
  print("current working directory: " + os.getcwd())
  path = "./samples/" + route + ".json"
  if(not os.path.exists(path)): path = "./server/samples/"+route+".json"
  if(not os.path.exists(path)): path = "./SignTalk/server/samples/"+route+".json"
  with open(path) as data_file:
    sample = json.load(data_file)
    return compare_jsons(data, sample)


def compare_jsons(data, sample, path=""):
  if(type(data) == list):
    num = 0
    for element in data:
      temp = compare_jsons(element, sample[0], path+"["+str(num)+"]")
      if(temp != None): return temp
      num += 1

  if(type(data) == dict):
    for key in sample.keys():
      if(key not in data):
        res = {
          "success":False,
          "responseCode":404,
          "message":""
        }
        res["message"] = "key '" + path + key + "' is a required field but was not given"
        return res
      temp = compare_jsons(data[key], sample[key], path+"."+key)
      if(temp != None): return temp

  if(type(data) != type(sample)):
    res = {
      "success":False,
      "responseCode":404,
      "message":""
    }
    res["message"] = "invalid datatype for key: " + path
    res["message"] += ", expected: " + str(type(sample))
    res["message"] += " actual: " + str(type(data))
    return res
