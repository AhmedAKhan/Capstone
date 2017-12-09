from flask import Blueprint, request
import json
import flask
import logging
import os

from ..controller import validate

test = Blueprint('test', __name__,
  template_folder='templates',
  url_prefix='/test')

@test.route('/<path:route>', methods=['POST'])
def test_route(route):
  """ handles the test route
    :param str: route is the url after the base, This file will validate that the
    input is as expected, and then return an example response
  """
  print("starting /test/ route: ", route)
  if(route != None):
    data = request.json
    formatErrors = validate.json_route(data, route="/"+route)
    if(formatErrors != None):
      return flask.jsonify(**formatErrors)

  print("route is equal to " + route)
  path = "./samples/" + route +"_resp.json"
  if(not os.path.exists(path)): path = "./server/samples/"+route+"_resp.json"
  if(not os.path.exists(path)): path = "./SignTalk/server/samples/"+route+"_resp.json"
  if(not os.path.exists(path)):
    result = {
      "success":False,
      "message":"route does not exist " + route + " path: " + path,
      "responseCode": 404
    }
    return flask.jsonify(**result), 404

  print("going to open the path: ", path)
  with open(path) as data_file:
    result = json.load(data_file)
    return flask.jsonify(**result)
