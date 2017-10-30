"""
All Flask blueprints for the entire application.

All blueprints for all views go here.
They shall be imported by ----
"""

#from flask import Blueprint
from .tango import tango
from .emg import emg
from .test import test


all_blueprints = (tango, emg, test)
