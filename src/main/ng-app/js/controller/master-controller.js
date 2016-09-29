/* 
 * Master controller
 */
angular.module('cwApp')
        .controller('masterController',['$scope','restService',function($scope,restService){
                  
                  $scope.hideErrorMessage = function(){
                     $scope.errorMessage = null;
                     console.log("clicked ");
                  };
          
                  $scope.$on('unauthorized',function(event,data){
                      $scope.errorMessage = data.message;
                  });
                  
                  
                  $scope.companyName = "AT&T";
        }]);

