from skimage import data, color, exposure
from skimage.feature import hog



def calculate_hog_grayscale(image, orientations=8, pixels_per_cell=(32,32), cells_per_block=(1,1), visualise=False):
  """ This will calculate the histogram of gradients on the given image
    :param image: is a 3d numpy array representing rgb values for a 2d image
  """
  fd, hog_image = hog(
    image,
    orientations=8,
    pixels_per_cell=pixels_per_cell,
    cells_per_block=cells_per_block,
    visualize=visualise, feature_vector=True)
  return fd, hog_image

def visualise_hog(image):
  image = color.rgb2gray(image)
  fd, hog_image = calculate_hog(image)

  fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(8, 4), sharex=True, sharey=True)

  ax1.axis('off')
  ax1.imshow(image, cmap=plt.cm.gray)
  ax1.set_title('Input image')
  ax1.set_adjustable('box-forced')

  # Rescale histogram for better display
  hog_image_rescaled = exposure.rescale_intensity(hog_image, in_range=(0, 0.02))

  ax2.axis('off')
  ax2.imshow(hog_image_rescaled, cmap=plt.cm.gray)
  ax2.set_title('Histogram of Oriented Gradients')
  ax1.set_adjustable('box-forced')
  plt.show()

