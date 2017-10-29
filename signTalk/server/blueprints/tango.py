from flask import Blueprint, render_template, abort, request
import flask
import logging
from .utils import getSuccessResponse

from alg.algorithm import recognize
from controller.dataset import save
import controller.validate as validate
import controller.online as session

tango = Blueprint('tango', __name__,
  template_folder='templates',
  url_prefix='/tango')

logging.info("this is from the tango file");

@tango.route('/save', methods=['POST'])
def saveData():
  data = request.json

  ## validate input
  formatErrors = validate.json_route(data, route=request.path)
  if(formatErrors != None):
    return flask.jsonify(**formatErrors)
  letter = data.get('letter',"unknown")

  ## save the data
  success = save(data, letter)

  print("send a success as a response")
  result = getSuccessResponse()
  if(not success):
    result = flask.jsonify(
      success=False,
      responseCode=500,
      message="internal server error")
  return result

@tango.route('/offline/rec', methods=['POST'])
def rec_offline():
  data = request.json

  ## validate input
  formatErrors = validate.json_route(data, route=request.path)
  if(formatErrors != None):
    return flask.jsonify(**formatErrors)

  ## run algorithm
  result = recognize(data, offline=True)

  ## return result
  if(result == None):
    return getSuccessResponse()
  return result


@tango.route('/online/connect')
def rec_online_connect():
  sessionID = session.create()
  ## return result
  return flask.jsonify(
    success=True,
    message="",
    responseCode=0,
    sessionID=sessionID
  )

@tango.route('/online/disconnect')
def rec_online_disconnect():
  sessionID = request.args.get('sessionID')
  ## validate
  if(sessionID == None): return flask.jsonify(
      success=False,
      responseCode=404,
      message="sessionID not passed in as url parameter"
    )

  ## disconect from user
  session.close(sessionID)

  ## send response
  return getSuccessResponse()

@tango.route('/online/rec', methods=['POST'])
def rec_online():
  data = request.json

  ## validate input
  formatErrors = validate.json_route(data, route=request.path)
  if(formatErrors != None):
    return flask.jsonify(**formatErrors)

  ## run alg
  result = session.data(data['sessionID'], data)
  if(result != None): return flask.jsonify(**result)

  ## return response
  return flask.jsonify(success=False, responseCode=506, message="Feature not implemented :(")


