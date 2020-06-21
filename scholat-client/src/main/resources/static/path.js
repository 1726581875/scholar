var urlPath = {
    gateway: 'http://localhost:8001/scholat',
    user: function () {
        return this.gateway + '/user';
    },
    course: function () {
        return this.gateway + '/course'
    },
    task: function () {
        return this.gateway + '/task'
    },
    notice: function () {
        return this.gateway + '/notice'
    }

};