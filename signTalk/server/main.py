## to allow the server starter code to be in the server folder
import os
import sys
#sys.path.append("..")
sys.path.insert(0, os.path.abspath('..'))

import logging
from flask import Flask, send_from_directory, redirect, jsonify
from .extensions import app

app.config.from_object(__name__)

# Load default config and override config from an environment variable
app.config.update(dict(
    DEBUG='debug'
))
# to load from a file
# app.config.from_envvar('env', silent=True)


# -------------------- add blueprints ------------------------------
from .blueprints.blueprints import all_blueprints

## all the urls with the prefix /rev will be handles by the recommend blueprint
for blueprint in all_blueprints:
    app.register_blueprint(blueprint)

# -------------------- add blueprints  ------------------------------

@app.route('/test')
def testPage():
  """ This is used for testing, just returns a string """
  return jsonify("test response was good :)")

# this is the main route
@app.route('/')
def mainPage():
  """ this is the main route if someone tries to access it """
  return "this is the main page /"


## makes it so that when you run this script it starts flask, but it is recommmened to start
## the server using the flask cli
if __name__ == "__main__":
    print("starting the server app.run()");
    app.debug = True;
    app.run(port=4000)

