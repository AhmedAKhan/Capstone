

********
Pipeline
********

This is the flow of how data moves through the app until it concludes with the recognition.


Firmware --> Data --> Preprocess --> Data_splitter --> Model --> Output

**Firmware**
The firmware is the part of the code that exists on the hardware to funciton. 

**Data**
The data folder will have scripts that will convert the data taken from the hardware
either as a stream or through the SD card.

**Preprocess**
This will be the part of the code that will transform the data into a method that can be given into the model. 
It will find useful features about the data, extract it and return those features.

**Data splitter**
This will take the features and decide if the given motion corresponds to a hand motion 
or unstructured arm movement. This will take in the stream of features and return the 
sets of features that corresponds to a sign. 

**Model**
This will be the code that is responsible for sign language recognition. 
It will take in the features and return the category it belongs to



