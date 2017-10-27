from flask import Blueprint, request
import flask
import logging

emg = Blueprint('emg', __name__,
                        template_folder='templates',
                        url_prefix='/emg')

logging.info("this is from the emg blueprint file");

@emg.route('/save')
def saveData():
  print("This should save data")
  return "save response"

@emg.route('/offline/rec')
def rec_offline():
  print("this should recognize shit")
  return "rec_offline response"

@emg.route('/online/rec')
def rec_online():
  vanityurl = request.args.get('vanityurl') # get url requests
  result = {"steamid":123123, "success":True};
  return flask.jsonify(**result)
