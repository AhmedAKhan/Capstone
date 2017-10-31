import flask

def getSuccessResponse():
  """ returns a flask empty success object"""
  return flask.jsonify(
    success=True,
    responseCode=200,
    message=""
  )


