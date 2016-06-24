
module.exports = {
    vibrate: function (successCallback, pattern, repeat) {
        if (repeat === null || repeat === undefined)
            repeat = -1;

        if (pattern === undefined) {
            pattern = [0, 200];
        }
        else if (typeof pattern === 'number') {
            pattern = [0, pattern];
        }
        else if (typeof pattern === 'string' && !isNaN(pattern)) {
            pattern = [0, parseInt(pattern)];
        }
        else if (!Array.isArray(pattern)) {
            throw new Error('invalid pattern');
        }

        cordova.exec(successCallback, null, "Vibrator", "vibrate", [pattern, repeat]);
    },
    cancel: function (successCallback) {
        cordova.exec(successCallback, null, "Vibrator", "cancel", []);
    },
    hasVibrator: function (successCallback) {
        cordova.exec(successCallback, null, "Vibrator", "hasVibrator", []);
    }
};
