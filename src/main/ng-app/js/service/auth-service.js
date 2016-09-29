/* 
 * Authentication interceptor
 */
angular.module('cwApp')
        .factory('authService', ['restService','userService',
               function (restScope,userService) {
                var service = {},
                    registerUrl = "/open/authenticate/login";

                service.register = function (user,callback,error) {
                    restScope.post(registerUrl,user)
                             .then( callback|| function(){
                                 
                                },error || function(){
                                    
                                });
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


