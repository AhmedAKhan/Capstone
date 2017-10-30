import configparser


config = configparser.ConfigParser()
config.read('config.cfg')


def getConfigObject():
    return config


def getConfigValue(category, param):
    print("category: " + category + " param: " + param)
    return config[category][param]
