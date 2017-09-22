
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
    pip3 install green ## optional

Usage
=====

to run the testers in the root of the project run the command the signTalk can be replaced with signTalkRGB to run those testers

.. code-block:: bash

  nosetests -v --with-coverage --cover-package=signTalk \
              --cover-inclusive --cover-erase tests
  green tests -r # alternerative


You will get an output with all the test cases which passed and failed,
and then a coverage of the percentage of the code that has been tested

** TODO give an example output ** 

Introduction
============

explain testing

integration tests + unit tests
ummm TODO






.. import signTalk.main


asdasd .. autofunction:: signTalk.main.add
