from skimage import data, color, exposure


def calculate(image):
  fd, hog_image = hog(
    image, orientations=8, pixels_per_cell=(32, 32),
    cells_per_block=(1, 1), visualize=True)
  return [0]



