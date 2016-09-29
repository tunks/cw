'use strict';
/**
 * Route configuration for the ngReport module.
 */
angular.module('cwApp')
       .config(['$stateProvider', '$urlRouterProvider','$httpProvider',
    function($stateProvider, $urlRouterProvider,$httpProvider) {
        // For unmatched routes
        $urlRouterProvider.otherwise('/');

        // Application routes
        $stateProvider
            .state('index', {
                views: {
                    'filters': {templateUrl: 'templates/header.html'},
                    'content': {templateUrl: 'templates/home.html'}
                },
                url: '/'
            })
            .state('login',{
                views: {
                    'filters': {
                       templateUrl: 'templates/login.html',
                       controller: 'registrationController'
                    },
                    'content': {
                          templateUrl: 'templates/signup.html',
                          controller: "registrationController"
                    }
                },
                url: '/login'
            });
  
       //insert http web service interceptors
        $httpProvider.interceptors.push('authInterceptor');
    }
]);