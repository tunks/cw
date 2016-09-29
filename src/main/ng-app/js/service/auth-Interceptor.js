/* 
 * Web service interceptor
 */
angular.module('cwApp')
        .factory('authInterceptor', ['$rootScope','userService',
               function ($rootScope,userService) {
                var service = {};

                service.register = function (config) {
                    console.log("Request ",config);
                    var currentUser = userService.getCurrentUser(),
                         access_token = currentUser ? currentUser.access_token : null;
                    if (access_token) {
                        config.headers.authorization = access_token;
                    }
                    return config;
                };
                
                service.requestError = function(rejection){
                    console.log("Request Error ",rejection);
                };
                
                service.responseError = function (response) {
                    console.log("Response Error ",response);
                    if (response.status === 401) {
                        $rootScope.$broadcast('unauthorized',response.data);
                    }
                    return response;
                };
                return service;
            }]);


