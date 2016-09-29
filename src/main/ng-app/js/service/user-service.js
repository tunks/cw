/* 
 *User service
 */

angular.module('cwApp')
        .service('userService', ['cacheService',function(cacheService) {
    var service = this,
        currentUser = null;
    service.setCurrentUser = function(user) {
        currentUser = user;
        cacheService.set('user', user);
        return currentUser;
    };
    service.getCurrentUser = function() {
        if (!currentUser) {
            currentUser = cacheService.get('user');
        }
        return currentUser;
    };
}]);