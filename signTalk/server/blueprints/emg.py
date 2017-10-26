from flask import Blueprint, render_template, abort, request
import flask
import logging

emg = Blueprint('emg', __name__,
                        template_folder='templates',
                        url_prefix='/emg')

logging.info("this is from the emg blueprint file");

@emg.route('/saveData')
def saveData():
  print("This should save data")

@emg.route('/offline/rec')
def rec_offline():
  print("this should recognize shit")
  return "a"

@emg.route('/online/rec')
def rec_online():
  print("vanity url: ", vanity_url)
  vanityurl = request.args.get('vanityurl') # get url requests
  result = {"steamid":steamid, "success":True};
  return flask.jsonify(**result)

