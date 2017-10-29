import logging
import uuid
import server.utils as utils
from alg.algorithm import recognize


sessions = {}
save_time = utils.getConfigValue('online', 'save_time')


def _default_session():
  return {
    "frames":[],
    "frame_intervals":[],
    "frame_counts":set(),
    "latest_frame":0,
    "total_duration":0
  }
def create():
  while(True):
    my_id = str(uuid.uuid4())
    if(my_id not in sessions):
      logging.info("starting a session with id: " + str(my_id))
      sessions[my_id] = _default_session()
      return my_id
  raise Exception("reached end of create function")


def close(sessionID):
  logging.info("closing the session with id: " + sessionID)
  sessions.pop(sessionID, None)
  return


def data(sessionID, data):
  ## store the data that was passed in, store the last save_time secs
  if(sessionID not in sessions):
    return {
      "success":False,
      "message":"sessionID is not a valid session, current sessions are " + str(sessions.keys()),
      "responseCode": 404
    }
  session = sessions[sessionID]
  res = _add_data(sessionID, data)
  responseCode = 200
  if(not res): responseCode = 203

  logging.info("total_duration: " + session['total_duration'])
  while (session['total_duration'] - session['frame_intervals'][0] > save_time):
    _remove_data(sessionID)

  ## run the alg
  result = _run_alg(sessionID)
  return result, responseCode
def _add_data(sessionID, data):
  logging.info("add_data 1. adding the new data to session")
  session = sessions[sessionID]
  if(data['frame_id'] > session['latest_frame']):
    logging.info("add_data 2. adding data to the end")
    session['latest_frame'] = data['frame_id']
    session['frames'].append(data['frames'])
    session['frame_intervals'].append(data['interval'])
    session['total_duration'] += data['interval']
    session['frame_counts'].add(data['frame_id'])
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
  logging.info("removing data at the end")
  session = sessions[sessionID]
  session['frame_counts'].remove(session['latest_frame'] - len(session['frames']))
  del session['frames'][0]
  interval = session['frame_intervals'][0]
  session['total_duration'] -= interval
  return

def _run_alg(sessionID):
  session = sessions[sessionID]
  ## flatten the frames list
  data = [item for sublist in session['frames'] for item in sublist]
  result = recognize(data, offline=True)
  return result
