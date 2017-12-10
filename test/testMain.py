from flask_testing import TestCase
import unittest
from flask import Flask
# from server.extensions import app
from server.main import app

# class MyTest(TestCase):
class MyTest(unittest.TestCase):
    def setUp(self):
        self.app = app.test_client()

    def tearDown(self):
        return

    def test_test_route(self):
        """ check if /test responds with 200"""
        response = self.app.get('/test')
        self.assertEquals(response.status_code, 200)


## this will run the given test case when you run this file
## but a better way would be to use nose2, which will find all the test cases and run them for you
if __name__ == '__main__':
    unittest.main()
