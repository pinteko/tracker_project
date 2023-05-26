angular.module('tracker-front').controller('habitController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.loadHabits = function () {
        $http({
            url: contextPath + 'habits',
            method: 'GET'
        }).then(function (response) {
            $scope.habitList = response.data;
        });
    };

    // $scope.addToMeHabit = function (habitId) {
    //     $http.put(contextPath + 'habits/' + habitId + '/' + $localStorage.springWebUser.username)
    //         .then(function (response) {
    //             window.location.href = contextPath + '#!/myHabits';
    //         });
    // };

    $scope.addToMeHabit = function (name){
        $http({
            url: contextPath + 'habits/add',
            method: 'PUT',
            params: {
                name: name,
                username: $localStorage.springWebUser.username
            }
        }).then(function (response){
            window.location.href = contextPath + '#!/myHabits';
        });
    };

    $scope.loadHabits();
});