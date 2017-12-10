import configparser


config = configparser.ConfigParser()
config.read('config.cfg')


def getConfigObject():
    """ gets the config object to get global optional adjustable parameters,
        returns a dict
    """
    return config


def getConfigValue(category, param):
    """ returns a value for the optional parameter, (category and paramater) """
    print("category: " + category + " param: " + param)
    return config[category][param]
