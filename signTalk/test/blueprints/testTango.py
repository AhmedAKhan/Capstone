import json
import unittest
from server.main import app

base = "/tango"
class TangoTest(unittest.TestCase):
    def setUp(self):
        self.app = app.test_client()

    def tearDown(self):
        return

    def test_save_route(self):
        """ check if /save responds with 200, and saves the file"""
        obj = json.load(open('./server/samples/tango/save.json', 'r'))
        response = self.app.post(
            base+'/save',
            data=json.dumps(obj),
            content_type="application/json"
        )
        response_data = json.loads(response.get_data())
        self.assertEquals(response.status_code, 200)
        self.assertEquals(response_data['success'], True)
        self.assertEquals(response_data['responseCode'], response.status_code)

        ## check the file got saved
        ## hmm should i??

    def test_online_connect_(self):
        """ check if /online/connect works """
        res = self.app.get(base+'/online/connect')
        response_data = json.loads(res.get_data())
        self.assertEquals(res.status_code, 200)
        self.assertEquals('sessionID' in response_data, True)
        self.assertEquals(type(response_data['sessionID']), str)


    def test_online_disconnect(self):
        """ check if /online/disconnect works """
        res = self.app.get(base+'/online/connect')
        response_data = json.loads(res.get_data())
        sessionID = response_data['sessionID']

        res = self.app.get(base+'/online/disconnect', query_string=dict(sessionID=sessionID))
        response_data = json.loads(res.get_data())

        self.assertEquals(res.status_code, 200)
        self.assertEquals(response_data['success'], True)
    def test_online_disconnect_expect_err(self):
        """  check if /online/disconnect handle no session id """
        res = self.app.get(base+'/online/disconnect')
        response_data = json.loads(res.get_data())
        self.assertEquals(res.status_code, 404)
        self.assertEquals(response_data['success'], False)

    def test_online_disconnect_incorrect_sessionid(self):
        """  check if /online/disconnect handle incorrect session id"""
        res = self.app.get(base+'/online/disconnect', query_string=dict(sessionID="----"))
        response_data = json.loads(res.get_data())
        self.assertEquals(res.status_code, 404)
        self.assertEquals(response_data['success'], False)


    def test_online_rec(self):
        """  testing /online/rec works """
        res = self.app.get(base+'/online/connect')
        response_data = json.loads(res.get_data())
        print("response_data: ", response_data)
        sessionID = response_data['sessionID']

        obj = json.load(open('./server/samples/tango/online/rec.json', 'r'))
        res = self.app.post(
            base+'/online/rec',
            query_string=dict(sessionID=sessionID),
            data=json.dumps(obj),
            content_type="application/json"
        )
        response_data = json.loads(res.get_data())

        print("response_data: ", response_data)
        self.assertEquals(res.status_code, 200)
        self.assertEquals(response_data['success'], True)
        self.assertEquals('letter' in response_data, True)




## this will run the given test case when you run this file
## but a better way would be to use nose2, which will find all the test cases and run them for you
if __name__ == '__main__':
    unittest.main()
