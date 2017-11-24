"""
  This module when started will stream the data taken from the leap to
  a queue which can then be processed by the main thread
"""

import os, sys, inspect
src_dir = os.path.dirname(inspect.getfile(inspect.currentframe()))
lib_dir = os.path.abspath(os.path.join(src_dir, '.'))
sys.path.insert(0, lib_dir)
import Leap
import Leap, sys, threading, time
from Leap import CircleGesture, KeyTapGesture, ScreenTapGesture, SwipeGesture

class LeapData():
  def __init__(self):
    """ this function will setup the leap controller """
    self.listener = Listener()
    self.controller = Leap.Controller()
    self.queue = None
    self.options = self.getDefaultOptions()
    self.name = "leap"
  def getDefaultOptions(self):
    return {
      "images":True,
      "hand":True
    }
  def start(self, newQueue, newOptions=None):
    self.queue = newQueue
    self.controller.add_listener(self.listener)
    self.listener.queue = self.queue
    if(newOptions != None): self.options = newOptions
    pass
  def stop(self):
    self.controller.remove_listener(self.listener)
    self.queue = None
    self.listener.queue = None

class Listener(Leap.Listener):
  finger_names = ['Thumb', 'Index', 'Middle', 'Ring', 'Pinky']
  state_names = ['STATE_INVALID', 'STATE_START', 'STATE_UPDATE', 'STATE_END']
  bone_names = ['Metacarpal', 'Proximal', 'Intermediate', 'Distal']

  def on_init(self, controller):
    print("Initialized")
    self.queue = queue

  def on_connect(self, controller):
    print( "Connected")
  def on_exit(self, controller):
    ##print( "Exited")
    pass

  def _convertImageObj(self, left_image, right_image):
    """ takes in an image object returns the format that should be passed"""
    return {
      "left":{
        "x":left_image.ray_scale_x,
        "y":left_image.ray_scale_y
      },
      "right":{
        "x":right_image.ray_scale_x,
        "y":right_image.ray_scale_y
      }
    }

  def _on_images(self, controller):
    """ gets the image retrieved from the leap """
    print("Images available")
    # images = controller.images
    # left_image = images[0]
    # right_image = images[1]
    # data = self.convertImageObj(left_image, right_image)
    # data['type'] = 'image'

    if(self.options['images']): self.queue.put(data)

  def on_disconnect(self, controller):
    # Note: not dispatched when running in a debugger.
    print( "Disconnected")

  def on_frame(self, controller):
    # Get the most recent frame and report some basic information
    if(self.queue == None): return
    frame = controller.frame()

    data = {}
    print( "Frame id: %d, timestamp: %d, hands: %d, fingers: %d, tools: %d, gestures: %d" %(
      frame.id, frame.timestamp, len(frame.hands), len(frame.fingers), len(frame.tools), len(frame.gestures())))

    # Get hand skeleton
    if(len(frame.hands) == 0): return
    for i in range(len(frame.hands)):
      hand = frame.hands[i]
      handType = "left" if hand.is_left else "right"
      data[handType] = self.parse_hand(hand)
    self.queue.put(data)


  def parse_hand(self, hand):
    handType = "left" if hand.is_left else "right"
    print( "  %s, id %d, position: %s" % (handType, hand.id, hand.palm_position))
    data = {}

    # Get the hand's normal vector and direction
    data['palm_normal'] = hand.palm_normal
    data['palm_vel'] = hand.palm_velocity

    data['direction'] = hand.direction
    data['direction'] = hand.direction

    # Get arm bone
    data["arm"] = self._parse_arm(hand.arm)

    # Get fingers
    if(len(hand.fingers) == 0): return data
    for i in range(len(hand.fingers)):
      finger = hand.fingers[i]
      fingerName = self.finger_names[finger.type]
      data[fingerName] = self._parse_finger(finger)
    return data
  def _parse_finger(self, finger):
    data = {}
    data['id'] = finger.id
    data['length'] = finger.length
    data['width'] = finger.width
    data['bones'] = {}

    # Get bones
    for b in range(0, 4):
      bone = finger.bone(b)
      data['bones'][bone.type] = self._parse_bone(bone)
    return data
  def _parse_bone(self, bone):
    data = {}
    data['type'] = bone.type
    data['name'] = self.bone_names[bone.type]
    data['prev_joint'] = bone.prev_joint
    data['next_joint'] = bone.next_joint
    data['direction'] = bone.direction
    return data
  def _parse_arm(self, arm):
    data = {}
    data['width'] = arm.width
    data['wrist_position'] = arm.wrist_position
    data['elbow_position'] = arm.elbow_position
    data['direction'] = arm.direction
    data['basis'] = Leap.Matrix(arm.basis)
    return data

"""
import queue
import time
mem = queue.Queue()
leapStream = LeapData()
leapStream.start(mem)

time.sleep(10)
leapStream.stop()
print("mem size: ", mem.qsize())
"""
