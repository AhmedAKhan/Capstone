import tensorflow as tf
import numpy as np

class Model():
  def __init__(self, name):
    """ will create the model object for you """
    self.name = name

    ## placeholder and non trained variables
    self.tf_sess = None
    self.tf_saver = None
    self.merged_summaries = None
    self.tf_summary_writer = None
    return
  def load_model(self, model_path):
    """ Load a trained model from disk
      :param model_path: this is the path to where the model is stored
    """
    self.build_model()
    self.tf_sess = tf.Session()

    init_op = tf.global_variables_initializer()
    self.tf_sess.run(init_op)

    self.tf_saver = tf.train.Saver()
    self.tf_saver.restore(self.tf_sess, model_path)
    return
  def save_model(self, file_name=None, full_path=None, step=None):
    """ save the model in the location of model_path
      :param file_name: a string representing the name of the file to save it,
                          will save it in the folder src/trained/rbm/
      :param full_path: stores the model in this exact path
      :param step int: this is the number of steps the model has been trained for
        if model_path is not defined it will save it in ../trained/rbm/ with the
        file name rbm-step-step.model
    """
    self.tf_saver = tf.train.Saver()
    path = None;
    default_folder_path = "src/trained/"+self.name+"/"
    if(file_name is not None): path = default_folder_path + file_name
    elif (full_path is not None): path = full_path
    elif(step is not None): path = default_folder_path + self.name + "-step-"+str(step)+".model"
    else: path = default_folder_path + self.name + "-step.model"

    print("going to save the model in path: ", path)
    self.tf_saver.save(self.tf_sess, path)
    return
  def _setup_training(self):
    """ sets up training by creating the session and file writer """
    init_op = tf.global_variables_initializer()
    self.tf_sess = tf.Session()
    self.tf_sess.run(init_op)
    self.file_writer = tf.summary.FileWriter('out/summary/'+self.name+'/', self.tf_sess.graph)
    # self.file_writer = tf.summary.FileWriter('../summary/'+self.name+'/', self.tf_sess.graph)
    return


