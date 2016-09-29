/**
 * Data catch service
 * **/
angular.module('cwApp')
        .factory('cacheService', ['$cacheFactory',
            function ($cacheFactory) {
                var cache = $cacheFactory('store');
                var cacheService = {
                    get: function (key) {
                        return cache.get(key);
                    },
                    put: function (key, data) {
                        cache.put(key, data);
                    }
                };
      

                return cacheService;
            }]);

