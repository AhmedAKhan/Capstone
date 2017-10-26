import logging
from flask import Flask, send_from_directory, redirect

import os
cwd = os.getcwd()
print("cwd: " + cwd)
print("asdasdasd")

import extensions
from extensions import app

app.config.from_object(__name__)

# Load default config and override config from an environment variable
app.config.update(dict(
    DEBUG='debug'
))
# to load from a file
# app.config.from_envvar('env', silent=True)


# -------------------- add blueprints ------------------------------
from blueprints.blueprints import all_blueprints

## all the urls with the prefix /rev will be handles by the recommend blueprint
for blueprint in all_blueprints:
    app.register_blueprint(blueprint)

# -------------------- add blueprints  ------------------------------


@app.route('/test')
def testPage():
  return "test response was good :)"

# this is the main route
@app.route('/')
def mainPage():
  return redirect('/static/index.html');


## makes it so that when you run this script it starts flask, but it is recommmened to start
## the server using the flask cli
if __name__ == "__main__":
    print("starting the server app.run()");
    app.debug = True;
    app.run(port=4000)
