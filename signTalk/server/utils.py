import configparser


config = configparser.ConfigParser()
config.read('config.cfg')


def getConfigObject():
    return config


def getConfigValue(category, param):
    return config[category][param]
