import logging
import uuid
from .. import utils as utils
from ...alg import classify


sessions = {}
save_time = int(utils.getConfigValue('online', 'save_time'))
print("save_time: " , save_time, " type: ", type(save_time))


def _default_session():
  """ creates a default empty session when a user calls connect """
  return {
    "frames":[],
    "frame_intervals":[],
    "frame_counts":set(),
    "latest_frame":0,
    "total_duration":0
  }
def create():
  """ creates a new session, and generates a unique key for the user """
  while(True):
    my_id = str(uuid.uuid4())
    if(my_id not in sessions):
      logging.info("starting a session with id: " + str(my_id))
      sessions[my_id] = _default_session()
      return my_id
  raise Exception("reached end of create function")


def close(sessionID):
  """ closes the session with sessionID """
  logging.info("closing the session with id: " + sessionID)
  if(sessionID not in sessions):
    return {
      "success": False,
      "message": "the sessionID " + sessionID + " does not exist",
      "responseCode":404
    }
  sessions.pop(sessionID, None)
  return None


def data(sessionID, data):
  """ sessionID has recoreded new information data, adjust session info """
  ## store the data that was passed in, store the last save_time secs
  if(sessionID not in sessions):
    return {
      "success":False,
      "message":"sessionID is not a valid session, current sessions are " + str(sessions.keys()),
      "responseCode": 404
    }, 404
  session = sessions[sessionID]
  res = _add_data(sessionID, data)
  responseCode = 200
  if(not res): responseCode = 203 ## missing frame

  logging.info("total_duration: " , session['total_duration'])
  while (session['total_duration'] - session['frame_intervals'][0] > save_time):
    _remove_data(sessionID)

  ## run the alg
  result = _run_alg(sessionID)
  if(type(result) == Exception):
    return {"success":False, "message":str(result), "responseCode":501}, 501

  response = {
    "success":True,
    "message":"",
    "responseCode":200,
    "letter":result
  }
  return response, responseCode

def _add_data(sessionID, data):
  """ adds data to the session data object """
  logging.info("add_data 1. adding the new data to session")
  session = sessions[sessionID]
  if(data['frame_id'] > session['latest_frame']):
    logging.info("add_data 2. adding data to the end")
    session['latest_frame'] = data['frame_id']
    session['frames'].append(data['frames'])
    session['frame_intervals'].append(data['interval'])
    session['total_duration'] += data['interval']
    session['frame_counts'].add(data['frame_id'])
    print("added to session, session: ", session)
    return True

  ## missed frame data
  logging.info("add_data 2. found a missing frame")
  offset = session['latest_frame'] - data['frame_id']
  session['frames'].insert(data['frames'], len(session['frames']) - offset - 1)
  session['frames_intervals'].insert(data['interval'], len(session['frames']) - offset - 1)
  session['total_duration'] += data['interval']
  session['frame_counts'].add(data['frame_id'])
  return False
def _remove_data(sessionID):
  """ removes data from the session data object """
  logging.info("removing data at the end")
  session = sessions[sessionID]
  session['frame_counts'].remove(session['latest_frame'] - len(session['frames']))
  del session['frames'][0]
  interval = session['frame_intervals'][0]
  session['total_duration'] -= interval
  return

def _run_alg(sessionID):
  """ This function calls the algorithm to figure out the letter of the last values """
  session = sessions[sessionID]
  ## flatten the frames list
  data = [item for sublist in session['frames'] for item in sublist]
  result = recognize(data, offline=True)
  return result
