'''
How the Hidden Markov Model works (to the best of my knowledge)

Hidden Markov Model (HMM) is an unsupervised learning machine
algorithm.  It outputs a probability of a state, based on the
observations it makes in a given (t) ammount of time.
In otherwords:

Input: Observation(s)
Output: Probability of ( State x | Observation y1, y2, .. yt)

So our observations has to be quantifiable.

In our problem, we have a video device attempting to recognize
hand gestures.  The observation is hand-gesture, which can be
measured or quantified by looking at the position of reference
points.  Our time can be referenced as "frames" which the
recording device captured.  And our objective is to find the
probability that, given a sequence of the observed hand-gestures,
it matches an existing hand-gesture (such as the letter K).

The data is going to be parsed by the filter and the feature.

'''

print(__doc__)

import numpy as np
from hmmlearn import hmm
np.random.seed(42)

class HMM(Model):
  def __init__(self):
    self.name = "hmm"
    self.batch_size = batch_size
    self.outputs = 2
    self.learning_rate = 0.001
    self.num_outputs = 3

    self.build_model()
    return
  def build_model(self):
    """ builds the model to train on """
    remodel = hmm.GaussianHMM(n_components=8, covariance_type="full")

  def train_model(self, data_collector, num_steps=1000, save_path=None):
    #@# TODO
    pass
  def run_train_step(self, dataset):
    """ train one step """
    ### TODO
    pass
  def print_model_info(self, dataset, step=0):
    ## TODO
    pass

  def classify(self, input_data):
    prediction = remodel.predict(input_data)
    return prediction



#begin script
def classify(probMat, transMat, meanMat, covariance):
	#8 components represent the channels?
	#Assuming covariance will be full because its unlikely it will look diagonal
	model = hmm.GaussianHMM(n_components=8, covariance_type="full")
	model.startprob_ = np.array(probMat)	#probability natrix
	model.transmat_ = np.array(transMat)	#probability matrix
	model.means_ = np.array(meanMat)		#mean matrix
	model.covars_ = np.tile(np.identity(), (covariance)) #?
	X, Z = model.sample(5)			#result

	remodel = hmm.GaussianHMM(n_components=8, covariance_type="full", n_iter=100)  #n_iter: max number of iterations
	remodel.fit(X)					#check fittness
	Z2 = remodel.predict(X)			#prediction statement

	print("remodel.monitor_:\n")
	remodel.monitor_
	print("remodel.converged_:\n")
	remodel.monitor_.converged


	#above is single sequence (no time implemented)

	X1 = [...]		#timesplit example
	X2 = [...]

	X = np.concatenate([X1, X2])
	lengths = [len(X1), len(X2)]

	hmm.GaussianHMM(n_components=3).fit(X, lengths)
