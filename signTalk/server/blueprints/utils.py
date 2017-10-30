import flask

def getSuccessResponse():
  return flask.jsonify(
    success=True,
    responseCode=200,
    message=""
  )


