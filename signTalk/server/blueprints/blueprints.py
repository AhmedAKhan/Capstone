"""
All Flask blueprints for the entire application.

All blueprints for all views go here.
They shall be imported by ----
"""

#from flask import Blueprint
from blueprints.tango import tango
from blueprints.emg import emg
from blueprints.test import test


all_blueprints = (tango, emg, test)
