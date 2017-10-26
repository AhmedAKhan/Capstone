"""Flask and other extensions instantiated here.

To avoid circular imports with views and create_app(), extensions are instantiated here.
They will be initialized
(calling init_app()) in main.py.
"""

from flask import Flask
# from flask_pymongo import PyMongo

# -------------------- logger stuff ------------------------------
import logging

## set all the custom logging information needed
logging.basicConfig(filename='main.log',level=logging.DEBUG)

# define a Handler which writes INFO messages or higher to the sys.stderr
console = logging.StreamHandler()
console.setLevel(logging.INFO)
# set a format which is simpler for console use
formatter = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
# tell the handler to use this format
console.setFormatter(formatter)
# add the handler to the root logger
logging.getLogger('').addHandler(console)
# logging.debug('This message should go to the log file')
# logging.info('So should this')
# logging.warning('And this, too')
# -------------------- logger stuff ------------------------------


## create the main app for flask
app = Flask(__name__, static_url_path='/static')



