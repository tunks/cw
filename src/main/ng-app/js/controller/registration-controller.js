/* 
 *Registration controller
 */

angular.module('cwApp')
        .controller('registrationController',['$scope','authService',function($scope,authService){
                $scope.userModel = {};
        
                 $scope.registerUser = function(){
                    console.log("submit user ",$scope.userModel);  
                    authService.register($scope.userModel);
                 };

          }]);

