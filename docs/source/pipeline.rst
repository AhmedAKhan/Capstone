

********
Pipeline
********

This is the flow of how data moves through the app until it concludes with the recognition.


Firmware --> Data --> Parser -->  Split --> Feature --> Filter --> Classifier --> Output

**Firmware**
The firmware is the part of the code that exists on the hardware to funciton. 
It is responsible for streaming the captured hand motion data on an sd card
or sending it through wifi

**Data**
The data folder will have scripts that will convert the data taken from the hardware
either as a stream or through the SD card. It will pass the data to the parser


**Parser**
This will take the given object from data and create an emg_model of this data.
The emg_model object will hold the object in ways easier to program

**Split**
This will take an emg array and return another emg array where each emg object is 
of size "width". This will allow the classifier to only need to deal with input the 
same size

**Feature**
Thi module will take in the data and return the features extracted from this model.
The model takes in a list of values of type enum 'feature.type'. and return an object
feature_model with those features

**Classifier**
This will be the code that is responsible for sign language recognition. 
It will take in the features and return the category it belongs to

**Output**
Currently this will just print to the console of the current word that is being
displayed


