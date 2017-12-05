from flask import Blueprint, render_template, abort, request
import flask
import logging
from .utils import getSuccessResponse

from alg.algorithm import recognize
from ..controller.dataset import save
from ..controller import validate
from ..controller import online as session

tango = Blueprint('tango', __name__,
  template_folder='templates',
  url_prefix='/tango')

logging.info("this is from the tango file");

@tango.route('/save', methods=['POST'])
def saveData():
  """ flask route function, post request take in a sample example and will save it """
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
  """ This is a post request, take an example data and will return
      the letter it corresponds with
  """
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
    responseCode=200,
    sessionID=sessionID
  )

@tango.route('/online/disconnect')
def rec_online_disconnect():
  """ This will take in a session id and close that session"""
  sessionID = request.args.get('sessionID')
  ## validate
  if(sessionID == None): return flask.jsonify(
      success=False,
      responseCode=404,
      message="sessionID not passed in as url parameter"
    ), 404

  ## disconect from user
  result = session.close(sessionID)
  if(result != None): return flask.jsonify(**result), result['responseCode']

  ## send response
  return getSuccessResponse()

@tango.route('/online/rec', methods=['POST'])
def rec_online():
  """ This function will take in the data for a given interval,
      based on storing the last intervals. It will return if the
      last n seconds was a valid letter
  """
  data = request.json
  sessionID = request.args.get('sessionID')

  ## validate input
  formatErrors = validate.json_route(data, route=request.path)
  if(formatErrors != None):
    return flask.jsonify(**formatErrors)

  ## run alg
  result = session.data(sessionID, data)
  print("result: ", result)
  if(result != None): return flask.jsonify(**result[0]), result[1]

  ## return response
  return flask.jsonify(success=False, responseCode=506, message="Feature not implemented :(")


