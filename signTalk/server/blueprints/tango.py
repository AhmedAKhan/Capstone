from flask import Blueprint, render_template, abort, request
import flask
import logging

tango = Blueprint('tango', __name__,
                        template_folder='templates',
                        url_prefix='/tango')

logging.info("this is from the tango file");

@tango.route('/saveData')
def saveData():
  print("This should save data")

@tango.route('/offline/rec')
def rec_offline():
  print("this should recognize shit")
  return "a"

@tango.route('/online/rec')
def rec_online():
  print("vanity url: ", vanity_url)
  vanityurl = request.args.get('vanityurl') # get url requests
  result = {"steamid":steamid, "success":True};
  return flask.jsonify(**result)

