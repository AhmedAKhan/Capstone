from flask import Blueprint, render_template, abort, request
import flask
import logging

tango = Blueprint('tango', __name__,
  template_folder='templates',
  url_prefix='/tango')

logging.info("this is from the tango file");

@tango.route('/save')
def saveData():
  print("This should save data")
  return "saved the data"

@tango.route('/offline/rec')
def rec_offline():
  print("this should recognize shit")
  return "a"

@tango.route('/online/rec')
def rec_online():
  vanityurl = request.args.get('vanityurl') # get url requests
  result = {"steamid":9898, "success":True};
  return flask.jsonify(**result)


