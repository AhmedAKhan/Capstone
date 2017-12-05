
import tensorflow as tf
import numpy as np
from .model import Model
from sklearn import datasets
import matplotlib.pyplot as plt

loss_vec = []
batch_accuracy = []

# Load the data
# iris.data = [(Sepal Length, Sepal Width, Petal Length, Petal Width)]
iris = datasets.load_iris()
x_vals = np.array([[x[0], x[3]] for x in iris.data])
y_vals1 = np.array([1 if y==0 else -1 for y in iris.target])
y_vals2 = np.array([1 if y==1 else -1 for y in iris.target])
y_vals3 = np.array([1 if y==2 else -1 for y in iris.target])
y_vals = np.array([y_vals1, y_vals2, y_vals3])
class1_x = [x[0] for i,x in enumerate(x_vals) if iris.target[i]==0]
class1_y = [x[1] for i,x in enumerate(x_vals) if iris.target[i]==0]
class2_x = [x[0] for i,x in enumerate(x_vals) if iris.target[i]==1]
class2_y = [x[1] for i,x in enumerate(x_vals) if iris.target[i]==1]
class3_x = [x[0] for i,x in enumerate(x_vals) if iris.target[i]==2]
class3_y = [x[1] for i,x in enumerate(x_vals) if iris.target[i]==2]


batch_size = 50
rand_index = np.random.choice(len(x_vals), size=batch_size)
rand_x = x_vals[rand_index]
rand_y = y_vals[:,rand_index]

class SVM(Model):
  def __init__(self):
    self.name = "svm"
    self.batch_size = batch_size
    self.outputs = 2
    self.learning_rate = 0.001
    self.num_outputs = 3

    self.build_model()
    return
  def build_model(self):
    """ builds the model to train on """
    self._create_variables()
    self._create_placeholders()

    self._build_RBF_kernel()

    ## compute svm loss
    first_term = tf.reduce_sum(self.weights)
    b_vec_cross = tf.matmul(tf.transpose(self.weights), self.weights)
    y_target_cross = self.reshape_matmul(self.y_target)

    second_term = tf.reduce_sum(tf.multiply(self.my_kernel, tf.multiply(b_vec_cross, y_target_cross)), [1,2])
    self.loss = tf.reduce_sum(tf.negative(tf.subtract(first_term, second_term)))
    # self.loss = tf.negative(tf.subtract(first_term, second_term))

    # Gaussian (RBF) prediction kernel
    rA = tf.reshape(tf.reduce_sum(tf.square(self.x_data), 1),[-1,1])
    rB = tf.reshape(tf.reduce_sum(tf.square(self.prediction_grid), 1),[-1,1])
    pred_sq_dist = tf.add(tf.subtract(rA, tf.multiply(2., tf.matmul(self.x_data, tf.transpose(self.prediction_grid)))), tf.transpose(rB))
    pred_kernel = tf.exp(tf.multiply(self.gamma, tf.abs(pred_sq_dist)))

    # predict the output
    prediction_output = tf.matmul(tf.multiply(self.y_target,self.weights), pred_kernel)
    self.prediction = tf.arg_max(prediction_output-tf.expand_dims(tf.reduce_mean(prediction_output,1), 1), 0)
    self.accuracy = tf.reduce_mean(tf.cast(tf.equal(self.prediction, tf.argmax(self.y_target,0)), tf.float32))

    ## train
    my_opt = tf.train.GradientDescentOptimizer(self.learning_rate)
    self.train_step = my_opt.minimize(self.loss)

  def _build_RBF_kernel(self):
    """ this will build the radial bias function kernel """
    self.gamma = tf.constant(-10.0)
    dist = tf.reduce_sum(tf.square(self.x_data), 1)
    dist = tf.reshape(dist, [-1,1])
    sq_dists = tf.multiply(2., tf.matmul(self.x_data, tf.transpose(self.x_data)))
    # sq_dists = tf.add(tf.subtract(dist, tf.multiply(2., tf.matmul(self.x_data, tf.transpose(self.x_data)))), tf.transpose(dist))
    self.my_kernel = tf.exp(tf.multiply(self.gamma, tf.abs(sq_dists)))

  def train_model(self, data_collector, num_steps=1000, save_path=None):
    # Training loop
    for i in range(num_steps):
      self.run_train_step(data_collector)
      self.print_model_info(data_collector, step=i)

  def run_train_step(self, dataset):
    """ train one step """
    feed_dict = self._create_feed_dict(dataset)
    self.tf_sess.run(self.train_step, feed_dict=feed_dict)
  def print_model_info(self, dataset, step=0):
    feed_dict = self._create_feed_dict(dataset)
    temp_loss = self.tf_sess.run(self.loss, feed_dict=feed_dict)
    loss_vec.append(temp_loss)

    acc_temp = self.tf_sess.run(self.accuracy, feed_dict=feed_dict)
    batch_accuracy.append(acc_temp)

    if (step+1)%250==0:
      print('Step #' + str(step+1))
      print('Loss = ' + str(temp_loss))
  def _create_feed_dict(self, dataset):
    """ creates the dictionary """
    return {
      # self.x_data: rand_x,
      # self.y_target: rand_y,
      # self.prediction_grid:rand_x

      self.x_data: dataset.x,
      self.y_target: dataset.y,
      self.prediction_grid: dataset.x
    }
  def _create_placeholders(self):
    """ create the placeholders  """
    num_parameters = 2
    self.x_data = tf.placeholder(shape=[None, num_parameters], dtype=tf.float32)
    self.y_target = tf.placeholder(shape=[self.num_outputs, None], dtype=tf.float32)
    self.prediction_grid = tf.placeholder(shape=[None, num_parameters], dtype=tf.float32)
  def _create_variables(self):
    """ create trainable variables """
    self.weights = tf.Variable(tf.random_normal(shape=[self.num_outputs, self.batch_size]))

  def reshape_matmul(self, mat):
    """ helper function to reshape matrix """
    v1 = tf.expand_dims(mat, 1)
    v2 = tf.reshape(v1, [3, batch_size, 1])
    return(tf.matmul(v2, v1))

  def classify(self, input_data):
    feed_dict = {self.x_data: input_data}
    [prediction] = self.tf_sess(self.prediction, feed_dict)
    return prediction

def gen_plot(model):
  # Create a mesh to plot points in
  x_min, x_max = x_vals[:, 0].min() - 1, x_vals[:, 0].max() + 1
  y_min, y_max = x_vals[:, 1].min() - 1, x_vals[:, 1].max() + 1
  xx, yy = np.meshgrid(
    np.arange(x_min, x_max, 0.02),
    np.arange(y_min, y_max, 0.02))
  grid_points = np.c_[xx.ravel(), yy.ravel()]
  # feed_dict = model.create_feed_dict()
  feed_dict = {model.x_data: rand_x, model.y_target: rand_y, model.prediction_grid: grid_points}
  grid_predictions = model.tf_sess.run(model.prediction, feed_dict=feed_dict)
  grid_predictions = grid_predictions.reshape(xx.shape)

  ## make the plot
  plt.contourf(xx, yy, grid_predictions, cmap=plt.cm.Paired, alpha=0.8)
  plt.plot(class1_x, class1_y, 'ro', label='I. setosa')
  plt.plot(class2_x, class2_y, 'kx', label='I. versicolor')
  plt.plot(class3_x, class3_y, 'gv', label='I. virginica')
  plt.title('Gaussian SVM Results on Iris Data')
  plt.xlabel('Pedal Length')
  plt.ylabel('Sepal Width')
  plt.legend(loc='lower right')
  plt.ylim([-0.5, 3.0])
  plt.xlim([3.5, 8.5])
  plt.show()

  # Plot batch accuracy
  plt.plot(batch_accuracy, 'k-', label='Accuracy')
  plt.title('Batch Accuracy')
  plt.xlabel('Generation')
  plt.ylabel('Accuracy')
  plt.legend(loc='lower right')
  plt.show()

  # Plot loss over time
  plt.plot(loss_vec, 'k-')
  plt.title('Loss per Generation')
  plt.xlabel('Generation')
  plt.ylabel('Loss')
  plt.show()


"""
from classify import SVM
svm = SVM.SVM()
svm.build_model()
svm._setup_training()
svm.train_model(None)

SVM.gen_plot(svm)
"""
