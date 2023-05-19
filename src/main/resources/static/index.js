(function () {
    angular
        .module('tracker-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/habits', {
                templateUrl: 'habits/habit.html',
                controller: 'habitController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            // .when('/students', {
            //     templateUrl: 'students.html',
            //     controller: 'studentsController'
            // })
            // .when('/orders', {
            //     templateUrl: 'myOrders/orders.html',
            //     controller: 'ordersController'
            // })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.springWebUser) {
            try {
                let jwt = $localStorage.springWebUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.springWebUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            if ($localStorage.springWebUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
            }
        }
        // if (!$localStorage.springWebGuestCartId) {
        //     $http.get('http://localhost:8189/app/cart/generate')
        //         .then(function successCallback(response) {
        //             $localStorage.springWebGuestCartId = response.data.value;
        //         });
        // }
    }
})();

angular.module('tracker-front').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    // $http.get('http://localhost:8189/app/habit/' + $localStorage.springWebGuestCartId + '/merge')
                    //     .then(function successCallback(response) {
                    //     });

                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };
});