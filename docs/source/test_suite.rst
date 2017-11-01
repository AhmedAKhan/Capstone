
**********
Test Suite
**********

Setup
=====

To get the testers to run the module coverage and nose should be installed. 
optionally rednose can be installed to get the colored output

.. code-block:: bash
    
    pip3 install nose2
    pip3 install coverage

Usage
=====

to run the testers in the root of the project run the command the signTalk can be replaced with signTalkRGB to run those testers

.. code-block:: bash

  nose2 -v -t "./signTalk/" -s ./test


You will get an output with all the test cases which passed and failed,
and then a coverage of the percentage of the code that has been tested

** TODO give an example output ** 

Introduction
============

explain testing

integration tests + unit tests
ummm TODO


