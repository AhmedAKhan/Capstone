

**********
preprocess
**********

Purpose
=======

This module is reponsible for taking in the data and converting it to the 
type used by the model. This data will be inputed as a ENGData object 
(refernece emg object ?!?!?). In the preprocessing step there are 2 main phases.
The first phase is the filter phase. The second phase is the feature extraction 
phase. 


Filter
======

The data is given through as a EMGData object. This phase will remove noise from
the inputed data making recognition more robust. 
Methods to reduce noise in data

* Fast Fourier Transformation (FFT)
* ...


Feature Extraction
==================

This phase will be to convert the data given from EMGData and convert it to 
a format that will be inputed to our algorithm. This phase will return a 
1 dimensional np array to be inputed to the algorithm. These features are 
going to be extracted for each channel and will then be placed by the model
The features extracted in this step are as follows

* Mean absolute value
* modified mean absolute value
* simple square integral
* root mean square
* log detector
* average amplitude change
* maximum fractal length
* min
* max
* standard deviation

Additional improvements can be added by implementing 

* principal component analysis
* wavelet transformation




