
flask
=====

documenting using the yaml file

.. openapi:: specs/openapi.yaml

documenting using normal comments 

.. autoflask:: server.main:app
   :undoc-static:

custom documentation written on the file

.. http:post:: /tango/save

   The request will take the data sent and save it to be used for training later

   **Example request**:

   .. sourcecode:: http

      POST /tango/save HTTP/1.1
      Host: example.com
      Accept: application/json, text/javascript
      Content-Type: application/json

      {
          "frames": [
              [
                  [
                      [10,10,10,10],
                      [10,20,10,10],
                      [10,20,10,10],
                  ],[
                      [10,10,10,10],
                      [10,10,20,10],
                      [10,10,20,10],
                  ]
              ]
          ]
          "resolution":{
              "width": 100,
              "height": 100
          },
          "letter": "c"
      }

   **Example response**:

   .. sourcecode:: http

      HTTP/1.1 200 OK
      Vary: Accept
      Content-Type: application/json
      
      {
          "messages": "",
          "responseCode": "200",
          "success": true
      }

   :statuscode 200: no error
   :statuscode 404: post request is missing post data.
   :statuscode 412: post request has invalid data
   :statuscode 500: Internal server error


