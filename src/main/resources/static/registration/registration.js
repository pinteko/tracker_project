angular.module('tracker-front').controller('registrationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.newUser = null;

    $scope.createUser = function () {
        console.log($scope.newUser);
        $http.post(contextPath + 'registration', $scope.newUser)
            .then(function (response) {
                window.location.href = contextPath + 'habit.html';
            });
    };
});